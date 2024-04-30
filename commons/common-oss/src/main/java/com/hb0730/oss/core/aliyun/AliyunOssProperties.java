package com.hb0730.oss.core.aliyun;

import com.hb0730.oss.core.OssProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/13
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AliyunOssProperties implements OssProperties {
    /**
     * 访问key
     */
    private String accessKey;
    /**
     * 访问密钥
     */
    private String secretKey;
    /**
     * 区域
     */
    private String region = "Auto";
    /**
     * endpoint
     */
    private String endpoint;
    /**
     * endpoint 策略,默认 `public-read` 公共读
     */
    private String endpointPolicy = "public-read";
    /**
     * bucketName
     */
    private String bucketName;
    /**
     * 自定义域名
     */
    private String customDomain;

}