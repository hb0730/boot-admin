package com.hb0730.sys.base.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件预签名URL
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@Data
@Schema(description = "文件预签名URL")
public class FilePresignedUrlRespVO implements Serializable {
    /**
     * 对象key
     */
    @Schema(description = "对象key")
    private String objectKey;
    /**
     * 访问URL
     */
    @Schema(description = "访问URL")
    private String accessUrl;
    /**
     * 上传URL
     */
    @Schema(description = "上传URL")
    private String uploadUrl;
}
