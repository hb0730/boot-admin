package com.hb0730.oss.core;

import com.hb0730.base.utils.StrUtil;
import lombok.Getter;

import java.io.File;

/**
 * OSS 存储 配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/12
 */
public interface OssProperties {
    /**
     * 获取访问key
     *
     * @return key
     */
    String getAccessKey();

    /**
     * 获取秘钥
     *
     * @return 秘钥
     */
    String getSecretKey();

    /**
     * 获取bucketName,例如：{@code hb0730}
     *
     * @return bucketName
     */
    String getBucketName();

    /**
     * 获取区域,默认：{@code Auto}, 例如: {@code oss-cn-hongkong}
     *
     * @return 区域
     */
    default String getRegion() {
        return "Auto";
    }

    /**
     * 获取endpoint，例如：{@code oss-cn-hongkong.aliyuncs.com}
     *
     * @return .
     */
    default String getEndpoint() {
        return "";
    }

    /**
     * 获取协议,默认：{@link Protocol#HTTPS}
     *
     * @return 协议
     */
    default Protocol getEndpointProtocol() {
        return Protocol.HTTPS;
    }

    /**
     * 自定义域名，例如：{@code https://oss.hb0730.com}
     *
     * @return 静态域名
     */
    default String getCustomDomain() {
        return "";
    }

    /**
     * 获取对象key,如果存在{@link #getCustomDomain()}则去除域名,否则去除bucketName和endpoint
     * <pre>
     * 例如：访问url: {@code https://hb0730.oss-cn-hongkong.aliyuncs.com/2021/04/12/1.jpg} 且{@link #getCustomDomain()}为空，则返回{@code 2021/04/12/1.jpg}
     * 例如：访问url: {@code https://oss.hb0730.com/2021/04/12/1.jpg} 且{@link #getCustomDomain()}不为空，则返回{@code 2021/04/12/1.jpg}
     * </pre>
     *
     * @param accessUrl 访问url
     * @return key
     */
    default String getObjectKey(String accessUrl) {
        String objectName;
        if (StrUtil.isNotBlank(this.getCustomDomain()) && this.getCustomDomain().toLowerCase().startsWith("http")) {
            objectName = accessUrl.replace(this.getCustomDomain() + "/", "");
        } else {
            objectName = accessUrl.replace("https://" + this.getBucketName() + "." + this.getEndpoint() + "/",
                    "");
        }

        return objectName;
    }

    /**
     * 获取对象key
     *
     * @param fileName 文件名 {@code 1.jpg}
     * @param path     路径 {@code 2021/04/12/}
     * @return key, 例如：{@code 2021/04/12/1.jpg}
     */
    default String getObjectKey(String fileName, String path) {
        String bizPath = path;
        if (StrUtil.isNotBlank(path) && !bizPath.endsWith("/")) {
            bizPath = bizPath.concat("/");
        }

        String orgName = fileName;
        if (StrUtil.isBlank(orgName)) {
            orgName = fileName;
        }
        // 文件名= 文件名+时间戳+后缀
        String objectName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
        return bizPath + objectName;
    }

    /**
     * 获取对象key
     *
     * @param file 文件
     * @param path 路径
     * @return key
     */
    default String getObjectKey(File file, final String path) {
        return this.getObjectKey(file.getName(), path);
    }

    /**
     * 获取访问URL,如果存在{@link #getCustomDomain()}则返回自定义域名+对象key,否则返回{@code https://bucketName.endpoint/objectKey}
     * <pre>
     *     例如：{@code /2021/04/12/1.jpg} 且{@link #getCustomDomain()}为空，则返回{@code https://hb0730.oss-cn-hongkong.aliyuncs.com/2021/04/12/1.jpg},
     *     例如：{@code /2021/04/12/1.jpg} 且{@link #getCustomDomain()}不为空，则返回{@code https://oss.hb0730.com/2021/04/12/1.jpg}
     * </pre>
     *
     * @param objectKey 对象key
     * @return .
     */
    default String getAccessUrl(String objectKey) {
        String accessUrl;
        if (StrUtil.isNotBlank(this.getCustomDomain()) && this.getCustomDomain().toLowerCase().startsWith(
                "http")) {
            accessUrl = this.getCustomDomain() + "/" + objectKey;
        } else {
            accessUrl = "https://" + this.getBucketName() + "." + this.getEndpoint() + "/" + objectKey;
        }

        return accessUrl;
    }

    @Getter
    enum Protocol {
        HTTP("http"),
        HTTPS("https");

        private final String protocol;

        Protocol(String protocol) {
            this.protocol = protocol;
        }

    }
}