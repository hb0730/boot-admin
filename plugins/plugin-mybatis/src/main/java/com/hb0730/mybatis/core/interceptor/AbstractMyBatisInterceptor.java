package com.hb0730.mybatis.core.interceptor;

import cn.hutool.core.util.ReflectUtil;
import com.hb0730.base.AppUtil;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.pool.StrPool;
import com.hb0730.mybatis.core.annotation.FieldEncrypt;
import com.hb0730.mybatis.core.encrypt.AesMybatisEncryptService;
import com.hb0730.mybatis.core.encrypt.Base64MybatisEncryptService;
import com.hb0730.mybatis.core.encrypt.MybatisEncryptService;
import com.hb0730.mybatis.core.encrypt.NonMybatisEncryptService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字段解密拦截器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
public class AbstractMyBatisInterceptor {
    // 缓存 对象的字段
    protected final static Map<Class<?>, List<Field>> CLASS_FIELD_CACHE = new ConcurrentHashMap<>();
    // 缓存需要加密的参数
    protected final static Map<String, Map<String, FieldEncrypt>> PARAM_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取字符串类型的加密字段
     *
     * @param object 对象
     * @return 字段
     */
    public List<Field> getEncryptFields(Object object) {
        if (null == object) {
            return Collections.emptyList();
        }
        return getEncryptFields(object.getClass());
    }

    /**
     * 获取字符串类型的加密字段
     *
     * @param clazz 类
     * @return 字段
     */
    protected List<Field> getEncryptFields(Class<?> clazz) {
        return CLASS_FIELD_CACHE.computeIfAbsent(clazz, k -> Arrays.asList(ReflectUtil.getFields(k)))
                .stream().filter(field -> String.class.equals(field.getType()))
                .filter(field -> field.isAnnotationPresent(FieldEncrypt.class))
                .toList();
    }

    /**
     * 获取加密服务
     *
     * @param fieldEncrypt {@link FieldEncrypt}
     * @return {@link MybatisEncryptService}
     */
    protected MybatisEncryptService getEncrypt(FieldEncrypt fieldEncrypt) {
        return switch (fieldEncrypt.algorithm()) {
            case AES -> new AesMybatisEncryptService(fieldEncrypt.key(), fieldEncrypt.iv());
            case BASE64 -> new Base64MybatisEncryptService();
            case NON -> new NonMybatisEncryptService();
            default -> AppUtil.getBean(MybatisEncryptService.class);
        };
    }

    /**
     * 获取加密参数
     *
     * @param mappedStatement {@link MappedStatement}
     * @return {@link FieldEncrypt}
     */
    protected Map<String, FieldEncrypt> getEncryptParams(MappedStatement mappedStatement) {
        String mappedStatementId = mappedStatement.getId();
        return PARAM_CACHE.computeIfAbsent(mappedStatementId, k -> {
            Method method = getMappedStatementMethod(mappedStatementId);
            if (null == method) {
                return Collections.emptyMap();
            }
            Map<String, FieldEncrypt> map = new ConcurrentHashMap<>();
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                FieldEncrypt fieldEncrypt = parameter.getAnnotation(FieldEncrypt.class);
                if (null != fieldEncrypt) {
                    map.put(getParameterName(parameter), fieldEncrypt);
                }
            }
            return map;
        });
    }

    /**
     * 获取方法
     *
     * @param mappedStatementId mappedStatementId
     * @return {@link Method}
     */
    private Method getMappedStatementMethod(String mappedStatementId) {
        //paddedStatementId:  typeName + "." + methodName
        int typeMethodSpiltIndex = mappedStatementId.lastIndexOf(StrPool.DOT);
        String mapperClassName = mappedStatementId.substring(0, typeMethodSpiltIndex);
        String mapperMethodName = mappedStatementId.substring(typeMethodSpiltIndex + 1);
        try {
            Class<?> clazz = Class.forName(mapperClassName);
            return Arrays.stream(clazz.getMethods())
                    .filter(method -> method.getName().equals(mapperMethodName))
                    .findFirst()
                    .orElse(null);
        } catch (ClassNotFoundException e) {
            throw new BasicException("Class not found: " + mapperClassName, e);
        }
    }

    /**
     * 获取参数名称
     *
     * @param parameter 参数
     * @return 参数名称
     */
    public String getParameterName(Parameter parameter) {
        Param param = parameter.getAnnotation(Param.class);
        return null != param ? param.value() : parameter.getName();
    }
}
