package com.hb0730.boot.admin.project.system.permission.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 权限
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "权限名称不为空" )
    private String name;

    /**
     * 标识符
     */
    @NotBlank(message = "权限标识符不为空" )
    private String permission;

    /**
     * 菜单id
     */
    @NotNull(message = "所属菜单不为空" )
    private Long menuId;

    /**
     * 排序
     */
    @Max(value = 999, message = "排序值最大为999" )
    @Min(value = 0, message = "排序值最小为0" )
    private Integer sort;
}
