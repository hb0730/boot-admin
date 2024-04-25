package com.hb0730.otel.autoconfigure.rpc;

import com.hb0730.otel.sofa.rpc.SofaRpcTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/25
 */
@ConditionalOnClass(SofaRpcTelemetry.class)
//@ConditionalOnBean(OpenTelemetry.class)
//@Conditional(SdkEnabled.class)
@ConditionalOnProperty(value = "boot.admin.opentelemetry.rpc.enabled", havingValue = "true", matchIfMissing = true)
@Configuration
public class SofaRpcAutoConfiguration {


    @Bean
    public SofaRpcTelemetry sofaRpcTelemetry(OpenTelemetry openTelemetry) {
        return SofaRpcTelemetry.create(openTelemetry);
    }
}
