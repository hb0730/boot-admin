package com.hb0730.otel.autoconfigure.quartz;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.quartz.v2_0.QuartzTelemetry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/25
 */
@ConditionalOnProperty(prefix = "boot.admin.opentelemetry.quartz", value = "enabled", havingValue = "true")
@Configuration
@ConditionalOnClass(QuartzTelemetry.class)
@Slf4j
public class QuartzAutoConfiguration {


    public QuartzAutoConfiguration(OpenTelemetry openTelemetry) {
        QuartzTelemetry.create(openTelemetry);
    }

}
