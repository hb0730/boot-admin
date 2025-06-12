package com.hb0730.base.utils;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;

/**
 * Jackson 工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
public class JsonUtil {
    public static JsonUtil DEFAULT = new JsonUtil();
    /**
     * 默认 ObjectMapper
     */
    protected ObjectMapper shareMapper = null;

    /**
     * 初始化
     */
    protected JsonUtil() {
        shareMapper = get();
    }

    public ObjectMapper getObjectMapper() {
        return shareMapper;
    }


    /**
     * 获取默认的ObjectMapper
     *
     * @return ObjectMapper
     */
    private static ObjectMapper get() {
        return new ObjectMapper() {
            {
                //序列化时候，只序列化非空字段
                //this.setSerializationInclusion(Inclusion.NON_NULL);
                //or this.setSerializationConfig(this.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL));

                // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
                this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                this.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            }
        };
    }


    /**
     * 将对象转换为JSON字符串
     *
     * @param object 对象
     */
    public String toJson(Object object) {
        if (object == null) {
            return "";
        }
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json      JSON字符串
     * @param valueType 对象类型
     */
    public <T> T json2Obj(String json, Class<T> valueType) {
        Assert.notNull(valueType, "");

        if (StrUtil.isBlank(json)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(json, valueType);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象
     * (例：new TypeReference<List<MyBean>>())
     *
     * @param json          JSON字符串
     * @param typeReference 对象类型
     */
    public <T> T json2Obj(String json, TypeReference<T> typeReference) {
        Assert.notNull(typeReference, "");

        if (StrUtil.isBlank(json)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(json, typeReference);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象
     * （例：JavaType javaType = mapper.constructParametricType(List.class, TestBean.class))
     *
     * @param json     JSON字符串
     * @param javaType 对象类型
     */
    public <T> T json2Obj(String json, JavaType javaType) {
        Assert.notNull(javaType, "");

        if (StrUtil.isBlank(json)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(json, javaType);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JSON字符串转换为List
     *
     * @param json JSON字符串
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> json2List(String json) {
        try {
            return getObjectMapper().readValue(json, List.class);
        } catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("解析json错误");
        }
        return null;
    }
}
