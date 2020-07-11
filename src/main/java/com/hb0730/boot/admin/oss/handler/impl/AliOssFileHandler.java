package com.hb0730.boot.admin.oss.handler.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.commons.utils.ImageUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.exception.file.FileOperationException;
import com.hb0730.boot.admin.oss.configuration.properties.OssProperties;
import com.hb0730.boot.admin.oss.configuration.properties.impl.AliOssProperties;
import com.hb0730.boot.admin.oss.handler.OssHandler;
import com.hb0730.boot.admin.oss.model.UploadResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class AliOssFileHandler implements OssHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AliOssFileHandler.class);
    private final AliOssProperties properties;

    public AliOssFileHandler(OssProperties ossProperties, BootAdminProperties properties) {
        this.properties = (AliOssProperties) ossProperties;
    }

    @NonNull
    @Override
    public UploadResult upload(@NonNull MultipartFile file) {
        String endPoint = properties.getEndPoint();
        String accessKey = properties.getAccessKey();
        String accessSecret = properties.getAccessSecret();
        String bucketName = properties.getBucketName();
        String styleRule = properties.getStyleRule();
        String thumbnailStyleRule = properties.getThumbnailStyleRule();

        // Init OSS client
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKey, accessSecret);
        StringBuilder basePath = new StringBuilder();
        basePath.append("https://").append(bucketName)
                .append(".")
                .append(endPoint)
                .append("/");

        try {
            String basename = FilenameUtils.getBaseName(file.getOriginalFilename());
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String timestamp = String.valueOf(System.currentTimeMillis());
            StringBuilder upFilePath = new StringBuilder();

            upFilePath.append(basename)
                    .append("_")
                    .append(timestamp)
                    .append(".")
                    .append(extension);

            String filePath = StringUtils.join(basePath.toString(), upFilePath.toString());

            PutObjectResult putObjectResult = ossClient.putObject(bucketName, upFilePath.toString(), file.getInputStream());
            if (putObjectResult == null) {
                LOGGER.error("上传附件 {} 到阿里云失败", file.getOriginalFilename());
                throw new FileOperationException("上传附件 %s 到阿里云失败", file.getOriginalFilename());

            }

            // Response result
            UploadResult uploadResult = new UploadResult();
            uploadResult.setFilename(basename);
            uploadResult.setFilePath(StringUtils.isBlank(styleRule) ? filePath : filePath + styleRule);
            uploadResult.setKey(upFilePath.toString());
            uploadResult.setMediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())));
            uploadResult.setSuffix(extension);
            uploadResult.setSize(file.getSize());

            // Handle thumbnail
            if (OssHandler.isImageType(uploadResult.getMediaType())) {
                BufferedImage image = ImageUtils.getImageFromFile(file.getInputStream(), extension);
                // image ==null exension jpg
                uploadResult.setWidth(image == null ? null : image.getWidth());
                uploadResult.setHeight(image == null ? null : image.getHeight());
                if (ImageUtils.EXTENSION_ICO.equals(extension)) {
                    uploadResult.setThumbPath(filePath);
                } else {
                    uploadResult.setThumbPath(StringUtils.isBlank(thumbnailStyleRule) ? filePath : filePath + thumbnailStyleRule);
                }
            }
            return uploadResult;
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return new UploadResult();
    }

    @Override
    public void delete(@NonNull String key) {
        Assert.notNull(key, "File key must not be blank");
        String endPoint = properties.getEndPoint();
        String accessKey = properties.getAccessKey();
        String accessSecret = properties.getAccessSecret();
        String bucketName = properties.getBucketName();
        // Init OSS client
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKey, accessSecret);

        try {
            ossClient.deleteObject(new DeleteObjectsRequest(bucketName).withKey(key));
        } catch (Exception e) {
            throw new FileOperationException("附件 " + key + " 从阿里云删除失败", e);
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public boolean supportType(AttachmentTypeEnum type) {
        return AttachmentTypeEnum.ALIOSS.equals(type);
    }
}
