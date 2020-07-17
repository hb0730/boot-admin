package com.hb0730.boot.admin.oss.handler;

import com.hb0730.boot.admin.model.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.oss.model.UploadResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import static com.hb0730.boot.admin.model.constants.SystemConstants.FILE_SEPARATOR;

/**
 * <p>
 * 文件处理程序接口
 * </P>
 *
 * @author bing_huang
 * @since V2.0
 */
public interface OssHandler {
    MediaType IMAGE_TYPE = MediaType.valueOf("image/*");

    /**
     * <p>
     * 检查媒体类型是否为图像类型
     * </p>
     *
     * @param mediaType 媒体类型
     * @return 图像类型返回true
     */
    static boolean isImageType(@Nullable String mediaType) {
        return mediaType != null && IMAGE_TYPE.includes(MediaType.valueOf(mediaType));
    }

    /**
     * <p>
     * 检查媒体类型是否为图像类型
     * </p>
     *
     * @param mediaType 媒体类型
     * @return 图像类型返回true
     */
    static boolean isImageType(@Nullable MediaType mediaType) {
        return mediaType != null && IMAGE_TYPE.includes(mediaType);
    }

    /**
     * 规范化目录全名
     *
     * @param dir 目录全名不能为空
     * @return 带结束路径分隔符的规范化目录全名
     */
    @NonNull
    static String normalizeDirectory(@NonNull String dir) {
        Assert.hasText(dir, "Directory full name must not be blank");

        return StringUtils.appendIfMissing(dir, FILE_SEPARATOR);
    }

    /**
     * <p>
     * 文件上传
     * </p>
     *
     * @param file 文件
     * @return 文件上传返回信息
     */
    @NonNull
    UploadResult upload(@NonNull MultipartFile file);

    /**
     * <p>
     * 删除文件
     * </p>
     *
     * @param key 文件key
     */
    void delete(@NonNull String key);

    /**
     * 检查是否支持给定类型
     *
     * @param type 附件类型
     * @return true为支持类型
     */
    boolean supportType(@Nullable AttachmentTypeEnum type);
}
