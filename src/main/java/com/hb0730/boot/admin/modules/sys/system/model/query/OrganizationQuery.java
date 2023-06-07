package com.hb0730.boot.admin.modules.sys.system.model.query;

import com.hb0730.boot.admin.data.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 机构查询
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class OrganizationQuery extends BasePageQuery {
    /**
     * 所属机构
     */
    @Schema(description = "所属机构ID")
    private String parentId;
    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    private String name;
    /**
     * 机构编码
     */
    @Schema(description = "机构编码")
    private String code;
    /**
     * 联系人电话
     */
    @Schema(description = "联系人电话")
    private String linkTel;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer isEnable;

}
