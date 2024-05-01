package com.hb0730.opentelemetry.autoconfigure.rpc;

import com.hb0730.opentelemetry.rpc.SofaRpcTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/25
 */
@Configuration
@ConditionalOnClass(SofaRpcTelemetry.class)
@ConditionalOnProperty(value = "boot.admin.opentelemetry.rpc.enabled", havingValue = "true")
@EnableConfigurationProperties(OpenTelemetrySofaRpcProperties.class)
@ConditionalOnBean(OpenTelemetry.class)
public class OpenTelemetrySofaRpcAutoConfiguration {


    @Bean
    public SofaRpcTelemetry sofaRpcTelemetry(OpenTelemetry openTelemetry) {
        return SofaRpcTelemetry.create(openTelemetry);
    }
}