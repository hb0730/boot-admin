package com.hb0730.base.utils;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * amazon s3  OSS工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@Slf4j
public class S3Util {
    private static final String DEFAULT_PROTOCOL = "https";
    @Setter
    private static String protocol = DEFAULT_PROTOCOL;
    @Setter
    private static String endpoint;
    @Setter
    private static String bucketName;
    @Setter
    private static String accessKey;
    @Setter
    private static String secretKey;
    @Setter
    private static String region = "Auto";
    @Setter
    private static String domain;
    private static S3Client s3Client;

    /**
     * 初始化客户端
     */
    private static S3Client initOSSClient() {
        if (s3Client == null) {
            s3Client = buildClient();
        }
        return s3Client;
    }

    /**
     * 上传文件
     *
     * @param objectKey filename, e.g. "wx/2021/07/31/xxxx.jpg"
     * @param file      文件
     * @return 访问链接
     */
    public static String uploadFile(String objectKey, File file) {
        // 上传文件到aws s3
        S3Client s3Client = initOSSClient();
        if (null != s3Client) {
            // 上传文件(不需要分层
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(S3Util.bucketName).key(objectKey).build();
            s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
            // 访问链接
            // 访问链接
            return buildUrl(objectKey);

        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param objectKey   .
     * @param inputStream .
     * @return .
     * @throws IOException .
     */
    public static String uploadFile(String objectKey, InputStream inputStream) throws IOException {
        // 上传文件到aws s3
        S3Client s3Client = initOSSClient();
        if (null != s3Client) {
            // 上传文件(不需要分层
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(S3Util.bucketName).key(objectKey).build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, inputStream.available()));
            // 访问链接
            return buildUrl(objectKey);
        }
        return null;
    }

    private static String buildUrl(String objectKey) {
        String url = "";
        if (StrUtil.isBlank(S3Util.domain)) {
            url = S3Util.protocol + "://" + S3Util.getEndpoint() + "/" + S3Util.bucketName + "/" + objectKey;
        } else {
            url = S3Util.protocol + "://" + S3Util.domain + "/" + objectKey;
        }
        log.info("~~~~~ 上传文件成功,访问链接:{} ~~~~~", url);
        return url;
    }

    static S3Client buildClient() {
        // 构建s3客户端
        return S3Client
                .builder()
                .region(Region.of(S3Util.region))
                .endpointOverride(URI.create(S3Util.getEndpoint()))
                .credentialsProvider(() -> AwsBasicCredentials.create(S3Util.accessKey, S3Util.secretKey))
                .serviceConfiguration(S3Configuration.builder().chunkedEncodingEnabled(false).pathStyleAccessEnabled(false).build())
                .build();

    }

    /**
     * 关闭客户端
     */
    @PreDestroy
    public void closeClient() {
        if (s3Client != null) {
            s3Client.close();
        }
    }

    private static String getEndpoint() {
        return S3Util.protocol + "://" + S3Util.endpoint;
    }
}
