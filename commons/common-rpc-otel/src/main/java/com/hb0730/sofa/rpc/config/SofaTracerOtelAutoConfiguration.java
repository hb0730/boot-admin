package com.hb0730.sofa.rpc.config;

import io.opentelemetry.api.OpenTelemetry;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/23
 */
@Configuration
public class SofaTracerOtelAutoConfiguration {
    @Getter
    private static OpenTelemetry openTelemetry;

    public SofaTracerOtelAutoConfiguration(OpenTelemetry openTelemetry) {
        SofaTracerOtelAutoConfiguration.openTelemetry = openTelemetry;
    }

}
