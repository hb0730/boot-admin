package com.hb0730.rpc.basic.domain.query;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.IsNull;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.common.api.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/31
 */
@Getter
@Setter
@Accessors(chain = true)
public class BasOrgQuery extends BaseQuery {
    /**
     * 父级机构ID
     */
    @Equals
    @Schema(description = "父级机构ID")
    private String parentId;
    /**
     * 机构名称
     */
    @Like
    @Schema(description = "机构名称")
    private String name;
    /**
     * 联系人
     */
    @Like
    @Schema(description = "联系人")
    private String linkMan;
    /**
     * 联系电话
     */
    @Like
    @Schema(description = "联系电话")
    private String linkTel;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @Equals
    private Boolean enabled;


    /**
     * 父级id为空
     */
    @IsNull("parentId")
    @Schema(hidden = true)
    private String parentIdIsNull;

    /**
     * 系统编码
     */
    @Equals
    @Schema(description = "系统编码", hidden = true)
    private String sysCode;
}
