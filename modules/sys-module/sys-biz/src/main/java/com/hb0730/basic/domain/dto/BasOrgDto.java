package com.hb0730.basic.domain.dto;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.common.mybatis.tenant.core.BaseTenantDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class BasOrgDto extends BaseTenantDto implements TreeUtil.Node<BasOrgDto, String> {
    @Schema(description = "机构ID")
    private String id;
    @Schema(description = "父类ID")
    private String parentId;
    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    @NotBlank(message = "机构名称不能为空")
    private String name;
    /**
     * 联系人
     */
    @Schema(description = "联系人")
    @NotBlank(message = "联系人不能为空")
    private String linkMan;
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String linkTel;
    /**
     * 联系邮箱
     */
    @Schema(description = "联系邮箱")
    private String linkEmail;
    /**
     * 机构地址
     */
    @Schema(description = "机构地址")
    private String address;
    /**
     * 等级
     */
    @Schema(description = "等级")
    private Integer level;
    /**
     * 路径
     */
    @Schema(description = "路径")
    private String path;
    /**
     * 机构类型
     * 1 商户
     * 2 机构
     * 3 部门
     */
    @Schema(description = "机构类型,1 商户,2 机构,3 部门")
    @NotBlank(message = "机构类型不能为空")
    private Integer type = 2;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String memo;
    /**
     * 是否系统机构
     */
    @Schema(description = "是否系统机构")
    private Boolean system;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enabled;
    /**
     * 是否启用saas
     */
    @Schema(description = "是否启用saas")
    private Boolean saas;

    /**
     * 子机构
     */
    @ArraySchema(schema = @Schema(description = "子集，只有获取树形时，可能返回不为空", implementation = Object.class, allOf =
            BasOrgDto.class))
    private List<BasOrgDto> children;

    @Override
    public boolean isRoot() {
        return StrUtil.isBlank(parentId);
    }

    @Override
    public void setChildren(List<BasOrgDto> children) {
        this.children = children;
    }
}
