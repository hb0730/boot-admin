package com.hb0730.conf.s3;

import com.hb0730.base.utils.S3Util;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * amazon s3配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@Configuration
@EnableConfigurationProperties({S3Properties.class})
@ConditionalOnProperty(prefix = "boot.admin.s3", name = "enable", havingValue = "true")
public class S3Configuration {


    /**
     * s3配置
     *
     * @param properties s3配置
     */
    public S3Configuration(S3Properties properties) {
        S3Util.setProtocol(properties.getProtocol().getValue());
        S3Util.setEndpoint(properties.getEndpoint());
        S3Util.setBucketName(properties.getBucketName());
        S3Util.setAccessKey(properties.getAccessKey());
        S3Util.setSecretKey(properties.getSecretKey());
        S3Util.setRegion(properties.getRegion());
        S3Util.setDomain(properties.getDomain());
    }
}
