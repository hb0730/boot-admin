package com.hb0730.base.utils;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
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

    public static class ExtJsonUtil extends JsonUtil {
        private final ObjectMapper privateMapper;

        private ExtJsonUtil() {
            privateMapper = get();
            privateMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
                @Override
                public Object findFilterId(Annotated ac) {
                    // Let's default to current behavior if annotation is found:
                    Object id = super.findFilterId(ac);
                    // but use simple class name if not
                    if (id == null) {
                        String name = ac.getName();
                        if (StrUtil.isNotBlank(name)) {
                            if (name.contains("_$$_javassist")) {
                                name = name.substring(0, name.indexOf("_$$_javassist"));
                            }
                        }
                        id = name;
                        try {
                            PropertyFilter filter = getFilterProvider().findPropertyFilter(id, id);
                            if (filter == getFilterProvider().getDefaultFilter()) {
                                return null;
                            }
                        } catch (IllegalArgumentException e) {
                            return null;
                        }
                    }
                    return id;
                }
            });
        }

        public ObjectMapper getObjectMapper() {
            return privateMapper;
        }

        /**
         * 自定义过滤条件（排除指定属性）
         *
         * @param clazz      自定义过滤条件的类（过滤条件的名称=clazz.getName()）
         * @param properties 需要过滤掉属性名称
         * @return ExJsonUtil
         */
        public ExtJsonUtil excludeProperties(Class<?> clazz, String... properties) {
            return excludeProperties(clazz.getName(), properties);
        }

        /**
         * 自定义过滤条件（排除指定属性）
         * （需要配合 @JsonFilter("filterName") 使用）
         *
         * @param filterName 自定义过滤条件的名称
         * @param properties 需要过滤掉属性名称
         * @return ExJsonUtil
         */
        public ExtJsonUtil excludeProperties(String filterName, String... properties) {
            FilterProvider filterProvider = getFilterProvider()
                    .addFilter(
                            filterName,
                            SimpleBeanPropertyFilter.serializeAllExcept(properties)
                    );

            privateMapper.setFilterProvider(filterProvider);
            return this;
        }

        /**
         * 自定义过滤条件（包含指定属性）
         *
         * @param clazz      自定义过滤条件的类（过滤条件的名称=clazz.getName()）
         * @param properties 需要序列化属性名称
         * @return ExJsonUtil
         */
        public ExtJsonUtil includeProperties(Class<?> clazz, String... properties) {
            return includeProperties(clazz.getName(), properties);
        }

        /**
         * 自定义过滤条件（包含指定属性）
         * （需要配合 @JsonFilter("filterName") 使用）
         *
         * @param filterName 自定义过滤条件的名称
         * @param properties 需要序列化的属性名称
         * @return ExJsonUtil
         */
        public ExtJsonUtil includeProperties(String filterName, String... properties) {
            FilterProvider filterProvider = getFilterProvider()
                    .addFilter(
                            filterName,
                            SimpleBeanPropertyFilter.filterOutAllExcept(properties)
                    );

            privateMapper.setFilterProvider(filterProvider);
            return this;
        }

        /**
         * 取得FilterProvider（SimpleFilterProvider）
         *
         * @return SimpleFilterProvider
         */
        private SimpleFilterProvider getFilterProvider() {
            FilterProvider filterProvider = privateMapper.getSerializationConfig().getFilterProvider();
            if (null == filterProvider) {
                return new SimpleFilterProvider();
            }
            if (filterProvider instanceof SimpleFilterProvider) {
                ((SimpleFilterProvider) filterProvider).setFailOnUnknownId(false);
            }
            assert filterProvider instanceof SimpleFilterProvider;
            return (SimpleFilterProvider) filterProvider;
        }

        /**
         * 设置日期格式
         *
         * @param pattern .
         * @return ExJsonUtil
         */
        public ExtJsonUtil setDateFormat(String pattern) {
            privateMapper.setDateFormat(new SimpleDateFormat(pattern));
            return this;
        }

        /**
         * 设置日期格式
         *
         * @param dateFormat .
         * @return ExJsonUtil
         */
        public ExtJsonUtil setDateFormat(DateFormat dateFormat) {
            privateMapper.setDateFormat(dateFormat);
            return this;
        }

        /**
         * 生成的JSON字符串带有换行和缩进
         *
         * @return .
         */
        public ExtJsonUtil enableIndentOutput() {
            privateMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return this;
        }

        /**
         * 生成的紧凑格式JSON字符串
         *
         * @return .
         */
        public ExtJsonUtil disableIndentOutput() {
            privateMapper.disable(SerializationFeature.INDENT_OUTPUT);
            return this;
        }

        /**
         * 空值处理
         *
         * @return .
         */
        public ExtJsonUtil null2EmptyString() {
            privateMapper.getSerializerProvider()
                    .setNullValueSerializer(new JsonSerializer<Object>() {
                        @Override
                        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                            jsonGenerator.writeString("");
                        }
                    });
            return this;
        }
    }


}