package com.hb0730.boot.admin.file.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.MediaType;

/**
 * <p>
 * 文件上传返回信息
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@ToString
public class UploadResult {
    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件密钥
     */
    private String key;

    /**
     * 缩略图访问路径。
     */
    private String thumbPath;

    /**
     * 媒体类型
     */
    private String suffix;

    /**
     * 文件类型
     */
    private MediaType mediaType;

    /**
     * 大小
     */
    private Long size;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;
}
