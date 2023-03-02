package com.hb0730.boot.admin.modules.sys.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hb0730.boot.admin.data.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SysRole extends BaseEntity {
    /**
     * 角色名称
     */
    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色名称为空")
    private String roleName;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色编码为空")
    private String roleCode;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 是否启用（1，启用，2禁用）
     */
    @TableField("is_enable")
    @Schema(description = "是否启用", defaultValue = "0")
    private Integer enabled;

    public Integer getEnabled() {
        if (null == enabled) {
            return 0;
        }
        return enabled;
    }

    /**
     * 总部标识：1是，0：否
     */
    @Schema(description = "总部标识", defaultValue = "0")
    private Integer isSystem;

    public Integer getIsSystem() {
        if (null == isSystem) {
            return 0;
        }
        return isSystem;
    }
}
