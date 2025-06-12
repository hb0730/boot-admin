package com.hb0730.oss.configuration;

import com.hb0730.base.exception.BasicException;
import com.hb0730.oss.core.OssStorage;
import com.hb0730.oss.core.s3.S3OssProperties;
import com.hb0730.oss.core.s3.S3OssStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/8
 */
@Configuration
@ConditionalOnProperty(prefix = "zoom.oss", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({OssProperties.class})
public class OssAutoConfiguration {

    /**
     * oss bean
     *
     * @param properties properties
     * @return oss storage
     */
    @Bean(destroyMethod = "destroy")
    @ConditionalOnProperty(prefix = "zoom.oss", name = "type", havingValue = "s3")
    @ConditionalOnMissingBean
    public OssStorage s3OssStorage(OssProperties properties) {
        S3OssProperties s3 = properties.getS3();
        if (s3 != null) {
            return new S3OssStorage(s3);
        }
        throw new BasicException("S3 OSS properties cannot be null");
    }
}
