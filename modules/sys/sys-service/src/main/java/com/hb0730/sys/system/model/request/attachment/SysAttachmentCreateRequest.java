package com.hb0730.sys.system.model.request.attachment;

import com.hb0730.core.data.Domain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建附件
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@Data
@Schema(description = "创建附件")
public class SysAttachmentCreateRequest implements Domain {
    /**
     * 文件名
     */
    @Schema(description = "文件名")
    @NotBlank(message = "文件名不能为空")
    private String displayName;
    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    @NotBlank(message = "文件类型不能为空")
    private String mediaType;
    /**
     * 文件大小
     */
    @Schema(description = "文件大小")
    private Long size;
    /**
     * 链接
     */
    @Schema(description = "链接")
    private String permalink;
}
