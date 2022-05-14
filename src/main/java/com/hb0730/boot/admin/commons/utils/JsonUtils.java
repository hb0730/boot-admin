package com.hb0730.boot.admin.commons.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hb0730.boot.admin.exceptions.JsonException;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 *
 * @author bing_huang
 */
@UtilityClass
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        JavaTimeModule timeModule = new JavaTimeModule();
        MAPPER.registerModules(timeModule);
    }

    /**
     * json 字符串转对象,会使用默认转换
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @see #jsonToObject(String, Class, Object)
     */
    public <T> T jsonToObject(String json, Class<T> type, Object mapper) throws JsonException {
        Assert.hasText(json, "json content must be not null");
        Assert.notNull(type, "target type must be not null");
        Assert.notNull(mapper, "ObjectMapper must be not null");
        if (mapper instanceof ObjectMapper) {
            try {

                return ((ObjectMapper) mapper).readValue(json, type);
            } catch (IOException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }
    /**
     * json 字符串转对象,会使用默认转换
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @see #jsonToObject(String, Class, Object)
     */
    public <T> T jsonToObject(String json, Class<T> type) throws JsonException {
        return jsonToObject(json, type, MAPPER);
    }

    /**
     * 支持泛型序列化
     *
     * @param json     json string
     * @param javaType 泛型类型
     * @param <T>      对象转换类型
     * @return 对象指定类型
     */
    public <T> T jsonObject(String json, JavaType javaType) {
        return jsonToObject(json, javaType, MAPPER);
    }

    /**
     * 支持泛型序列化
     *
     * @param json     json string
     * @param javaType 泛型类型
     * @param mapper   {@link ObjectMapper}
     * @param <T>      对象转换类型
     * @return 对象指定类型
     */
    public <T> T jsonToObject(String json, JavaType javaType, Object mapper) {
        Assert.hasText(json, "json content must be not null");
        Assert.notNull(javaType, "target type must be not null");
        Assert.notNull(mapper, "ObjectMapper must be not null");
        if (mapper instanceof ObjectMapper) {
            try {

                return ((ObjectMapper) mapper).readValue(json, javaType);
            } catch (IOException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }


    /**
     * json字符串转list
     *
     * @param json json字符串不为空
     * @param type 转换的类型,不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     * @see #jsonToList(String, Class, Object)
     */
    public <T> List<T> jsonToList(String json, Class<T> type) throws JsonException {
        return jsonToList(json, type, MAPPER);
    }

    /**
     * json 字符串转list对象
     *
     * @param json   json字符串，不为空
     * @param type   需要转换的类型，不为空
     * @param mapper {@link com.fasterxml.jackson.databind.ObjectMapper}
     * @param <T>    对象转换类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     */
    public <T> List<T> jsonToList(String json, Class<T> type, Object mapper) throws JsonException {
        Assert.hasText(json, "json content must be not null");
        Assert.notNull(type, "target type must be not null");
        Assert.notNull(mapper, "object mapper must be not null");
        if (mapper instanceof ObjectMapper) {
            try {

                CollectionType collectionType = ((ObjectMapper) mapper).getTypeFactory().constructCollectionType(List.class, type);
                return ((ObjectMapper) mapper).readValue(json, collectionType);
            } catch (IOException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }

    /**
     * json字符串转换list对象
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws IOException 对象转换异常
     */
    public <T> List<T> jsonToList2(String json, Class<T> type) throws IOException {
        return jsonToList2(json, type, MAPPER);
    }

    /**
     * json字符串转换list对象
     *
     * @param json         json字符串，不为空
     * @param type         需要转换的类型，不为空
     * @param objectMapper {@link ObjectMapper},不为空
     * @param <T>          对象转换类型
     * @return 对象指定类型
     * @throws IOException 对象转换异常
     */
    public <T> List<T> jsonToList2(String json, Class<T> type, ObjectMapper objectMapper) throws IOException {
        Assert.hasText(json, "json content must be not null");
        Assert.notNull(type, "target type must be not null");
        Assert.notNull(objectMapper, "object mapper must be not null");
        return objectMapper.readValue(json, new TypeReference<List<T>>() {
        });
    }

    /**
     * 将对象转换成string json
     *
     * @param source 目标对象，不为空
     * @return json string
     * @throws JsonException 转换异常
     * @see #objectToJson(Object, Object)
     */
    public String objectToJson(Object source) throws JsonException {
        return objectToJson(source, MAPPER);
    }

    /**
     * 将对象转换成string json
     *
     * @param source 目标对象，不为空
     * @param mapper Jackson{@link com.fasterxml.jackson.databind.ObjectMapper}
     * @return json string
     * @throws JsonException 转换异常
     */
    public String objectToJson(Object source, Object mapper) throws JsonException {
        Assert.notNull(source, "source object must be not null");
        Assert.notNull(mapper, "object mapper must be not null");
        if (mapper instanceof ObjectMapper) {
            try {
                return ((ObjectMapper) mapper).writeValueAsString(source);
            } catch (IOException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }

    /**
     * 将map类型转成对象类型
     *
     * @param sourceMap 源map类型对象，不为空
     * @param type      需要转成的对象类型，不为空
     * @param <T>       目标对象类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     * @see #mapToObject(Map, Class, Object)
     */
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type) throws JsonException {
        return mapToObject(sourceMap, type, MAPPER);
    }

    /**
     * 将map类型转成对象类型
     *
     * @param sourceMap 源map类型对象，不为空
     * @param type      需要转成的对象类型，不为空
     * @param mapper    {@link com.fasterxml.jackson.databind.ObjectMapper}
     * @param <T>       目标对象类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     */
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type, Object mapper) throws JsonException {
        Assert.notNull(sourceMap, "source map must be not null");
        String json = objectToJson(sourceMap, mapper);
        return jsonToObject(json, type, mapper);
    }

    /**
     * 将 源目标对象转成map类型
     *
     * @param source 源目标，不为空
     * @param <T>    返回值类型
     * @return map实例
     * @throws JsonException 转换异常
     * @see #objectToMap(Object, Object)
     */
    public <T> Map<String, T> objectToMap(Object source) throws JsonException {
        return objectToMap(source, MAPPER);
    }

    /**
     * 将 源目标对象转成map类型
     *
     * @param source 源目标，不为空
     * @param mapper {@link  ObjectMapper}
     * @param <T>    返回值类型
     * @return map实例
     * @throws JsonException 转换异常
     */
    @SuppressWarnings({"unchecked"})
    public <T> Map<String, T> objectToMap(Object source, Object mapper) throws JsonException {
        String json = objectToJson(source, mapper);
        return (Map<String, T>) jsonToObject(json, Map.class, mapper);
    }


}
