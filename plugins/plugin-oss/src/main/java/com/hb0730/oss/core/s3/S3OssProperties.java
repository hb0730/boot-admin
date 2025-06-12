package com.hb0730.oss.core.s3;

import com.hb0730.oss.core.OssProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * S3 OSS 存储 配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/12
 */
@Data
@EqualsAndHashCode
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
    private String region = "Auto";
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
    /*=================STS相关参数====================*/
    /**
     * STS角色 ID
     */
    private String roleArn;
    /**
     * STS角色会话名称
     */
    private String roleSessionName;
}
