package com.hb0730.mybatis.core.interceptor;

import com.hb0730.zoom.base.utils.ReflectUtil;
import com.hb0730.zoom.mybatis.core.annotation.FieldEncrypt;
import com.hb0730.zoom.mybatis.core.encrypt.MybatisEncryptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.type.SimpleTypeRegistry;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;

/**
 * 解密拦截器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
@Slf4j
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class MyBatisDecryptInterceptor extends AbstractMyBatisInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object obj = invocation.proceed();
        if (null == obj || !(invocation.getTarget() instanceof ResultSetHandler)) {
            return obj;
        }
        List<?> result = (List<?>) obj;
        for (Object o : result) {
            //对于简单类型不做处理
            if (SimpleTypeRegistry.isSimpleType(o.getClass())) {
                continue;
            }
            // 获取所有字符串类型、需要解密的、有值字段
            List<Field> fields = super.getEncryptFields(o);
            for (Field field : fields) {
                FieldEncrypt fieldEncrypt = field.getAnnotation(FieldEncrypt.class);
                if (null == fieldEncrypt) {
                    continue;
                }
                // 如果不需要解密
                if (!fieldEncrypt.decrypt()) {
                    continue;
                }
                Object value = ReflectUtil.getFieldValue(o, field);
                if (null == value) {
                    continue;
                }
                if (!(value instanceof String)) {
                    continue;
                }
                // 解密 并设置值
                String decrypt = doDecrypt((String) value, fieldEncrypt);
                ReflectUtil.setFieldValue(o, field, decrypt);
            }
        }
        return obj;
    }


    /**
     * 解密
     *
     * @param parameterValue 参数值
     * @param fieldEncrypt   字段加密注解
     * @return 解密后的值
     */
    public String doDecrypt(String parameterValue, FieldEncrypt fieldEncrypt) {
        MybatisEncryptService encrypt = getEncrypt(fieldEncrypt);
        return encrypt.decrypt(parameterValue);
    }
}
