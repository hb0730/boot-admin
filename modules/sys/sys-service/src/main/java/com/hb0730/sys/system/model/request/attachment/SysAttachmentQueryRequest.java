package com.hb0730.sys.system.model.request.attachment;

import com.hb0730.mybatis.query.annotation.Equals;
import com.hb0730.mybatis.query.annotation.Like;
import com.hb0730.mybatis.query.doamin.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "附件查询")
public class SysAttachmentQueryRequest extends PageRequest {

    @Schema(description = "创建人", hidden = true)
    @Equals
    private String createdBy;

    @Schema(description = "文件名")
    @Like
    private String displayName;

    @Schema(description = "文件类型")
    @Like
    private String mediaType;


}
