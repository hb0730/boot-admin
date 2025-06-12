package com.hb0730.mybatis.core.interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.hb0730.zoom.base.pool.StrPool;
import com.hb0730.zoom.base.utils.ClassUtil;
import com.hb0730.zoom.base.utils.ReflectUtil;
import com.hb0730.zoom.mybatis.core.annotation.FieldEncrypt;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 加密拦截器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
public class MyBatisEncryptInterceptor extends AbstractMyBatisInterceptor implements InnerInterceptor {
    private static final Pattern PARAM_PAIRS_PATTERN = Pattern
            .compile("#\\{ew\\.paramNameValuePairs\\.(" + Constants.WRAPPER_PARAM + "\\d+)\\}");

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (null == parameter) {
            return;
        }
        // 如果是 Map 类型参数，处理加密
        if (parameter instanceof Map parameterMap) {
            encryptQueryParameter((Map<String, Object>) parameterMap, ms);
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        if (null == parameter) {
            return;
        }
        if (parameter instanceof Map parameterMap) {
            // 带别名方法（使用 @Param 注解的场景）
            this.encryptMap((Map<String, Object>) parameterMap, ms);
        } else {
            // 无别名方法（例如：MP insert 等方法）
            this.encryptEntity(super.getEncryptFields(parameter), parameter);
        }
    }

    /**
     * 加密 Map 类型数据（使用 @Param 注解的场景）
     *
     * @param parameterMap    参数
     * @param mappedStatement 映射语句
     */
    private void encryptMap(Map<String, Object> parameterMap, MappedStatement mappedStatement) {
        Object parameter;
        // 别名带有 et（针对 MP 的 updateById、update 等方法）
        if (parameterMap.containsKey(Constants.ENTITY) && null != (parameter = parameterMap.get(Constants.ENTITY))) {
            this.encryptEntity(super.getEncryptFields(parameter), parameter);
        }
        // 别名带有 ew（针对 MP 的 UpdateWrapper、LambdaUpdateWrapper 等参数）
        if (parameterMap.containsKey(Constants.WRAPPER) && null != (parameter = parameterMap.get(Constants.WRAPPER))) {
            this.encryptUpdateWrapper(parameter, mappedStatement);
        }
    }

    /**
     * 加密查询参数（针对 Map 类型参数）
     *
     * @param parameterMap    参数
     * @param mappedStatement 映射语句
     */
    private void encryptQueryParameter(Map<String, Object> parameterMap, MappedStatement mappedStatement) {
        Map<String, FieldEncrypt> encryptParams = super.getEncryptParams(mappedStatement);
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 如果是基本类型、AbstractWrapper、加密注解、加密字段不做处理
            if (null == value
                    || value instanceof AbstractWrapper
                    || ClassUtil.isBasicType(value.getClass())) {
                continue;
            }
            // 只有String类型才加密
            if (value instanceof String valueStr) {
                FieldEncrypt fieldEncrypt = encryptParams.get(key);
                if (null != fieldEncrypt && fieldEncrypt.value()) {
                    parameterMap.put(key, doEncrypt(valueStr, fieldEncrypt));
                }
            } else {
                // 如果是对象
                encryptEntity(super.getEncryptFields(value), value);
            }
        }
    }

    /**
     * 处理 UpdateWrapper 类型参数加密（针对 MP 的 UpdateWrapper、LambdaUpdateWrapper 等参数）
     *
     * @param parameter       Wrapper 参数
     * @param mappedStatement 映射语句
     * @author cary
     * @author wangshaopeng@talkweb.com.cn（<a
     * href="https://blog.csdn.net/tianmaxingkonger/article/details/130986784">基于Mybatis-Plus拦截器实现MySQL数据加解密</a>）
     * @since 2.1.1
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void encryptUpdateWrapper(Object parameter, MappedStatement mappedStatement) {
        if (parameter instanceof AbstractWrapper updateWrapper) {
            String sqlSet = updateWrapper.getSqlSet();
            if (CharSequenceUtil.isBlank(sqlSet)) {
                return;
            }
            // 将 name=#{ew.paramNameValuePairs.xxx},age=#{ew.paramNameValuePairs.xxx} 切出来
            String[] elArr = sqlSet.split(StrPool.COMMA);
            Map<String, String> propMap = new HashMap<>(elArr.length);
            Arrays.stream(elArr).forEach(el -> {
                String[] elPart = el.split(StrPool.EQUALS);
                propMap.put(elPart[0], elPart[1]);
            });
            // 获取加密字段
            Class<?> entityClass = mappedStatement.getParameterMap().getType();
            List<Field> encryptFieldList = super.getEncryptFields(entityClass);
            for (Field field : encryptFieldList) {
                FieldEncrypt fieldEncrypt = field.getAnnotation(FieldEncrypt.class);
                if (null == fieldEncrypt) {
                    continue;
                }
                if (!fieldEncrypt.value()) {
                    continue;
                }
                if (!String.class.equals(field.getType())) {
                    continue;
                }

                String el = propMap.get(field.getName());
                if (CharSequenceUtil.isBlank(el)) {
                    continue;
                }
                Matcher matcher = PARAM_PAIRS_PATTERN.matcher(el);
                if (matcher.matches()) {
                    String valueKey = matcher.group(1);
                    Object value = updateWrapper.getParamNameValuePairs().get(valueKey);
                    Object ciphertext = this.doEncrypt((String) value, fieldEncrypt);
                    updateWrapper.getParamNameValuePairs().put(valueKey, ciphertext);
                }
            }
        }
    }

    private void encryptEntity(List<Field> fields, Object entity) {
        for (Field field : fields) {
            FieldEncrypt fieldEncrypt = field.getAnnotation(FieldEncrypt.class);
            if (null == fieldEncrypt) {
                continue;
            }
            if (!fieldEncrypt.value()) {
                continue;
            }
            // 如果不是String类型不做处理
            if (!String.class.equals(field.getType())) {
                continue;
            }
            // 获取值
            Object value = ReflectUtil.getFieldValue(entity, field);
            if (null == value) {
                continue;
            }
            // 加密
            String encrypt = doEncrypt((String) value, fieldEncrypt);
            ReflectUtil.setFieldValue(entity, field, encrypt);
        }

    }

    public String doEncrypt(String parameterValue, FieldEncrypt fieldEncrypt) {
        return getEncrypt(fieldEncrypt).encrypt(parameterValue);
    }

}
