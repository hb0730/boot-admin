package com.hb0730.oss.core.s3;

import com.hb0730.oss.core.OssProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * S3 OSS 存储 配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/12
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class S3OssProperties implements OssProperties {
    /**
     * 访问key
     */
    private String accessKey;
    /**
     * 秘钥
     */
    private String secretKey;
    /**
     * bucketName
     */
    private String bucketName;
    /**
     * 区域
     */
    private String region;
    /**
     * endpoint
     */
    private String endpoint;
    /**
     * 协议
     */
    private Protocol endpointProtocol = Protocol.HTTPS;
    /**
     * 自定义域名
     */
    private String customDomain;

    /**
     * 是否启用路径样式访问
     */
    private boolean pathStyleAccess;

    /**
     * bucketPolicy
     */
    private String bucketPolicy = "Policy";
}