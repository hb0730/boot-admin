package com.hb0730.oss.configuration;

import com.hb0730.oss.core.s3.S3OssProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/8
 */
@Data
@ConfigurationProperties(prefix = "zoom.oss")
public class OssProperties {
    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 类型
     */
    private String type;
    /**
     * s3
     */
    private S3OssProperties s3 = new S3OssProperties();
}
