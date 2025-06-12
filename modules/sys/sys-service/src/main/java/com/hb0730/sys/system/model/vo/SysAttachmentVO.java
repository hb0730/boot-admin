package com.hb0730.sys.system.model.vo;

import com.hb0730.core.data.Domain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 附件
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@Data
@Schema(description = "附件")
public class SysAttachmentVO implements Domain {
    /**
     * id
     */
    @Schema(description = "id")
    private String id;
    /**
     * 文件名
     */
    @Schema(description = "文件名")
    private String displayName;
    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
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
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date created;
}
