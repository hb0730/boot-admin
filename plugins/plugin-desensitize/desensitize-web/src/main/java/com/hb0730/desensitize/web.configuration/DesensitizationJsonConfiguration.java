package com.hb0730.desensitize.web.configuration;

import com.hb0730.desensitize.core.serializer.AnnotationDesensitizeSerializer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
@AutoConfiguration(after = HttpMessageConvertersAutoConfiguration.class)
public class DesensitizationJsonConfiguration {

    @Bean
    @ConditionalOnBean(MappingJackson2HttpMessageConverter.class)
    public AnnotationDesensitizeSerializer objectDesensitizeSerializer() {
        return new AnnotationDesensitizeSerializer();
    }
}
