package com.hb0730.boot.admin.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.TimeZone;

/**
 * json序列化
 *
 * @author bing_huang
 * @since 3.0.0
 */
@JsonComponent
public class JsonSerializerManage {
    /**
     * 构建{@link ObjectMapper},主要是解决{@link Long}转{@link String}
     *
     * @param builder json build
     * @return {@link ObjectMapper}
     */
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        builder.timeZone(TimeZone.getDefault());
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //long丢失精度问题
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        //java8 时间类型
        JavaTimeModule timeModule = new JavaTimeModule();
        objectMapper.registerModules(simpleModule, timeModule);
        // 忽略多余字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
