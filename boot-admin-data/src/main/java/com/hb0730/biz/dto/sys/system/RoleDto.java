package com.hb0730.biz.dto.sys.system;

import com.hb0730.data.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/28
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleDto extends BaseDto {
    /**
     * 角色名称
     */
    @Schema(description = "id")
    private Integer id;
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;
    /**
     * 角色标识
     */
    @Schema(description = "角色标识")
    private String code;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enabled;

    /**
     * 权限id
     */
    @Schema(description = "权限id")
    private List<Integer> permissionIds;
}
