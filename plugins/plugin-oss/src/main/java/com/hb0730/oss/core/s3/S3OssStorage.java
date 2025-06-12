package com.hb0730.oss.core.s3;

import cn.hutool.core.net.URLDecoder;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.utils.StrUtil;
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
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Map;

/**
 * S3 Client
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/13
 */
@Slf4j
public class S3OssStorage extends AbstractOssStorage {
    private volatile S3Client s3Client;
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
        _getOssClient().deleteObject(builder -> builder.bucket(bucketName).key(objectKey));
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
        return uploadFile(objectKey, ossProperties.getBucketName(), file);
    }

    @Override
    public String uploadFile(String objectKey, String bucketName, File file) {
        has(file, "file is null")
                .has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");
        try {
            _getOssClient().putObject(
                    builder -> builder.bucket(bucketName).key(objectKey).build(),
                    RequestBody.fromFile(file)
            );
            return ossProperties.getAccessUrl(objectKey);
        } catch (Exception e) {
            log.error("upload file error", e);
            throw new BasicException("upload file error", e);
        }

    }

    @Override
    public String upload(String objectKey, InputStream stream) {
        return upload(objectKey, ossProperties.getBucketName(), stream);
    }

    @Override
    public String upload(String objectKey, String bucketName, InputStream stream) {
        return doUpload(objectKey, bucketName, -1, "", stream);
    }

    @Override
    public String upload(String objectKey, long contentLength, String contentType, InputStream stream) {
        return doUpload(objectKey, ossProperties.getBucketName(), contentLength, contentType, stream);
    }

    /**
     * 上传文件
     *
     * @param objectKey     objectKey
     * @param bucketName    bucketName
     * @param contentLength 文件大小
     * @param contentType   文件类型
     * @param stream        文件流
     * @return accessUrl
     */
    protected String doUpload(String objectKey, String bucketName, long contentLength,
                              String contentType, InputStream stream) {
        has(stream, "stream is null")
                .has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");
        // bucket exist
        checkBucket(bucketName);
        try {
            contentLength = contentLength <= 0 ? stream.available() : contentLength;
            contentType = StrUtil.isBlank(contentType) ? "application/octet-stream" : contentType;

            _getOssClient().putObject(builder -> builder.bucket(bucketName).key(objectKey).build(),
                    RequestBody.fromContentProvider(() -> stream, contentLength, contentType));
            return ossProperties.getAccessUrl(objectKey);
        } catch (Exception e) {
            log.error("upload file error", e);
            throw new BasicException("upload file error", e);
        }
    }

    @Override
    public InputStream getInputStream(String objectKey) {
        return getInputStream(objectKey, ossProperties.getBucketName());
    }

    @Override
    public InputStream getInputStream(String objectKey, String bucketName) {
        has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");
        try {
            ResponseBytes<GetObjectResponse> responseBytes = _getOssClient().getObjectAsBytes(
                    builder -> builder.bucket(bucketName).key(objectKey)
            );
            return responseBytes.asInputStream();
        } catch (Exception e) {
            log.error("get file error", e);
        }
        return null;
    }

    @Override
    public InputStream getInputStreamByAccessUrl(String accessUrl) {
        return getInputStreamByAccessUrl(accessUrl, ossProperties.getBucketName());
    }

    @Override
    public InputStream getInputStreamByAccessUrl(String accessUrl, String bucketName) {
        has(accessUrl, "accessUrl is null")
                .has(bucketName, "bucketName is null");
        String objectKey = ossProperties.getObjectKey(accessUrl);
        return getInputStream(objectKey, bucketName);
    }

    @Override
    public File downloadFile(String objectKey) {
        return downloadFile(objectKey, ossProperties.getBucketName());
    }

    @Override
    public File downloadFile(String objectKey, String bucketName) {
        has(objectKey, "objectKey is null")
                .has(bucketName, "bucketName is null");
        try {
            ResponseBytes<GetObjectResponse> responseBytes = _getOssClient().getObjectAsBytes(
                    builder -> builder.bucket(bucketName).key(objectKey)
            );
            // 创建临时文件
            File file = File.createTempFile(objectKey, ".tmp");
            // 写入临时文件
            Files.copy(responseBytes.asInputStream(), file.toPath());
            return file;
        } catch (Exception e) {
            log.error("download file error", e);
        }
        return null;
    }

    @Override
    public File downloadFileByAccessUrl(String accessUrl) {
        return downloadFileByAccessUrl(accessUrl, ossProperties.getBucketName());
    }

    @Override
    public File downloadFileByAccessUrl(String accessUrl, String bucketName) {
        String objectKey = ossProperties.getObjectKey(accessUrl);
        return downloadFile(objectKey, bucketName);
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
            throw new BasicException("get share url error", e);
        }
    }

    @Override
    public OssStsToken getStsToken(long expires) {
        try (StsClient stsClient = buildStsClient()) {
            AssumeRoleResponse roleResponse = stsClient.assumeRole(builder ->
                    builder.roleArn(ossProperties.getRoleArn())
                            .roleSessionName(ossProperties.getRoleSessionName())
                            .durationSeconds((int) expires));
            OssStsToken ossStsToken = new OssStsToken();
            ossStsToken.setAccessKey(roleResponse.credentials().accessKeyId());
            ossStsToken.setAccessSecret(roleResponse.credentials().secretAccessKey());
            ossStsToken.setSecurityToken(roleResponse.credentials().sessionToken());
            ossStsToken.setBucketName(ossProperties.getBucketName());
            return ossStsToken;

        } catch (Exception e) {
            log.error("get sts token error", e);
            throw new BasicException("get sts token error", e);
        }
    }

    @Override
    public PresignedUrl getPresignedUrl(String objectKey, long expires, Map<String, String> headers) {
        has(objectKey, "objectKey is null");
        try (S3Presigner s3Presigner = buildS3Presigner()) {
            PutObjectRequest.Builder putObjectRequest = PutObjectRequest.builder()
                    .bucket(ossProperties.getBucketName())
                    .key(objectKey);
            if (null != headers) {
                String contentType = headers.get("Content-Type");
                if (StrUtil.isNotBlank(contentType)) {
                    putObjectRequest.contentType(contentType);
                }
            }
            URL url = s3Presigner.presignPutObject(
                            builder -> builder
                                    .putObjectRequest(
                                            putObjectRequest.build()
                                    )
                                    .signatureDuration(Duration.ofSeconds(expires)))
                    .url();
            String presignedUrl = URLDecoder.decode(url.toString(), StandardCharsets.UTF_8);
            return new PresignedUrl(presignedUrl, ossProperties.getAccessUrl(objectKey));
        } catch (Exception e) {
            log.error("get presigned url error", e);
            throw new BasicException("get presigned url error", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getClient() {
        return (T) this.s3Client;
    }

    @Override
    public void init() {
        //双重检查，保证线程安全
        if (s3Client == null) {
            synchronized (this) {
                if (s3Client == null) {
                    s3Client = buildClient();
                }
            }
        }
    }

    @Override
    public void destroy() {
        if (null != s3Client) {
            s3Client.close();
        }
    }

    private S3Client _getOssClient() {
        if (null == s3Client) {
            init();
        }
        return s3Client;
    }

    /**
     * 检查bucket是否存在,不存在则创建
     *
     * @param bucketName bucketName
     */
    private void checkBucket(String bucketName) {
        has(bucketName, "bucketName is null");
        try {
            _getOssClient().headBucket(builder -> builder.bucket(bucketName));
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
                        URI.create(getEndpoint())
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
                        URI.create(getEndpoint())
                )
                .region(Region.of(ossProperties.getRegion()))
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

    private StsClient buildStsClient() {
        return StsClient.builder()
                .region(Region.of(ossProperties.getRegion()))
                .credentialsProvider(
                        () -> AwsBasicCredentials.create(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                )
                .build();
    }


    private String getEndpoint() {
        String _endpoint = ossProperties.getEndpoint();
        if (_endpoint.startsWith("http://") || _endpoint.startsWith("https://")) {
            return _endpoint;
        }
        return ossProperties.getEndpointProtocol() + "://" + _endpoint;
    }


}
