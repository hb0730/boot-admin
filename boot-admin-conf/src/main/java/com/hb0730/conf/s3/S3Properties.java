package com.hb0730.conf.s3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@ConfigurationProperties(prefix = "boot.admin.s3")
@Getter
@Setter
public class S3Properties {
    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * endpoint
     */
    private String endpoint;
    /**
     * accessKey
     */
    private String accessKey;
    /**
     * secretKey
     */
    private String secretKey;
    /**
     * region
     */
    private String region = "Auto";
    /**
     * bucketName
     */
    private String bucketName;
    /**
     * domain
     */
    private String domain;
    /**
     * protocol
     */
    private Protocol protocol = Protocol.HTTPS;


    /**
     * 协议
     */
    @Getter
    public enum Protocol {
        HTTP("http"),
        HTTPS("https");
        private final String value;

        Protocol(String value) {
            this.value = value;
        }

    }
}
