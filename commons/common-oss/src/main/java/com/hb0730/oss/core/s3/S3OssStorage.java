package com.hb0730.oss.core.s3;

import cn.hutool.core.net.URLDecoder;
import com.hb0730.base.exception.OssException;
import com.hb0730.oss.core.AbstractOssStorage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * S3 Client
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/13
 */
@Slf4j
public class S3OssStorage extends AbstractOssStorage {
    private S3Client s3Client;
    @Setter
    @Getter
    private S3OssProperties ossProperties;

    public S3OssStorage() {
    }

    public S3OssStorage(S3OssProperties properties) {
        super(properties);
        this.ossProperties = properties;
    }

    @Override
    public void deleteUrl(String accessUrl) {
        has(accessUrl, "accessUrl is null");
        String objectKey = ossProperties.getObjectKey(accessUrl);
        has(objectKey, "objectKey is null");
        removeObject(ossProperties.getBucketName(), objectKey);
    }

    @Override
    public void removeObject(String objectKey, String bucketName) {
        has(bucketName, "bucketName is null")
                .has(objectKey, "objectKey is null");
        s3Client.deleteObject(builder -> builder.bucket(bucketName).key(objectKey));
        log.info("delete object success, bucketName: {}, objectKey: {}", bucketName, objectKey);
    }

    @Override
    public String uploadFile(File file) {
        has(file, "file is null")
                .has(ossProperties, "ossProperties is null");
        String objectKey = ossProperties.getObjectKey(file, "");
        return uploadFile(objectKey, file);
    }

    @Override
    public String uploadFile(String objectKey, File file) {
        has(file, "file is null")
                .has(objectKey, "objectName is null");
        return uploadFile(objectKey, ossProperties.getBucketName(), file);
    }

    @Override
    public String uploadFile(String objectKey, String bucketName, File file) {
        has(file, "file is null")
                .has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");

        s3Client.putObject(
                builder -> builder.bucket(bucketName).key(objectKey).build(),
                RequestBody.fromFile(file)
        );
        return ossProperties.getAccessUrl(objectKey);

    }

    @Override
    public String upload(String objectKey, InputStream stream) {
        has(stream, "stream is null")
                .has(objectKey, "objectKey is null");
        return upload(objectKey, ossProperties.getBucketName(), stream);
    }

    @Override
    public String upload(String objectKey, String bucketName, InputStream stream) {
        has(stream, "stream is null")
                .has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");
        return doUpload(objectKey, bucketName, stream);
    }

    protected String doUpload(String objectKey, String bucketName, InputStream stream) {
        has(stream, "stream is null")
                .has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");

        // bucket exist
        checkBucket(bucketName);
        try {

            s3Client.putObject(builder -> builder.bucket(bucketName).key(objectKey).build(),
                    RequestBody.fromInputStream(stream, stream.available()));
            return ossProperties.getAccessUrl(objectKey);
        } catch (Exception e) {
            log.error("upload file error", e);
            throw new OssException("upload file error", e);
        }
    }

    @Override
    public InputStream getFile(String objectKey) {
        return getFile(objectKey, ossProperties.getBucketName());
    }

    @Override
    public InputStream getFile(String objectKey, String bucketName) {
        has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");
        try {
            ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(
                    builder -> builder.bucket(bucketName).key(objectKey)
            );
            return responseBytes.asInputStream();
        } catch (Exception e) {
            log.error("get file error", e);
        }
        return null;
    }

    @Override
    public String getShareUrl(String objectKey, long expires) {
        return getShareUrl(objectKey, ossProperties.getBucketName(), expires);
    }

    @Override
    public String getShareUrl(String objectKey, String bucketName, long expires) {
        has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");
        try (S3Presigner s3Presigner = buildS3Presigner()) {
            URL url = s3Presigner.presignGetObject(
                            builder -> builder
                                    .getObjectRequest(
                                            r -> r.bucket(bucketName)
                                                    .key(objectKey)
                                    )
                                    .signatureDuration(Duration.ofSeconds(expires)))
                    .url();
            return URLDecoder.decode(url.toString(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("get share url error", e);
            throw new OssException("get share url error", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getClient() {
        return (T) this.s3Client;
    }

    @Override
    public void init() {
        this.s3Client = buildClient();
    }

    /**
     * 检查bucket是否存在,不存在则创建
     *
     * @param bucketName bucketName
     */
    private void checkBucket(String bucketName) {
        has(bucketName, "bucketName is null");
        try {
            s3Client.headBucket(builder -> builder.bucket(bucketName));
        } catch (Exception ignored) {
            s3Client.createBucket(builder -> builder.bucket(bucketName));
            s3Client.putBucketPolicy(builder -> builder.bucket(bucketName).policy(ossProperties.getBucketPolicy()));
        }
    }

    /**
     * 创建客户端
     *
     * @return {@link S3Client}
     */
    private S3Client buildClient() {
        return S3Client.builder()
                .endpointOverride(
                        URI.create(ossProperties.getEndpointProtocol() + "://" + ossProperties.getEndpoint())
                )
                .region(
                        Region.of(ossProperties.getRegion())
                )
                .credentialsProvider(
                        () -> AwsBasicCredentials.create(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                ).serviceConfiguration(
                        builder -> builder.chunkedEncodingEnabled(false).pathStyleAccessEnabled(ossProperties.isPathStyleAccess())
                )
                .build();
    }

    private S3Presigner buildS3Presigner() {
        return S3Presigner.builder()
                .endpointOverride(
                        URI.create(ossProperties.getEndpointProtocol() + "://" + ossProperties.getEndpoint())
                )
                .region(Region.of(ossProperties.getRegion()))
                .credentialsProvider(() -> AwsBasicCredentials.create(ossProperties.getAccessKey(),
                        ossProperties.getAccessKey()))
                .credentialsProvider(
                        () -> AwsBasicCredentials.create(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                )
                .serviceConfiguration(
                        S3Configuration
                                .builder()
                                .chunkedEncodingEnabled(false)
                                .pathStyleAccessEnabled(ossProperties.isPathStyleAccess())
                                .build()
                )
                .build();
    }


}