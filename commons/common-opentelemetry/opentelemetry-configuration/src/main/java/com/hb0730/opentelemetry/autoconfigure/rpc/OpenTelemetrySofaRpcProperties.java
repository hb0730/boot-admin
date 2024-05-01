package com.hb0730.opentelemetry.autoconfigure.rpc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
@ConfigurationProperties(prefix = "boot.admin.opentelemetry.rpc")
@Getter
@Setter
public class OpenTelemetrySofaRpcProperties {
    /**
     * Whether to enable RPC tracing.
     */
    private boolean enabled = true;
}
