package com.hb0730.boot.admin.project.system.role.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 角色
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseDTO {

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
    @NotBlank(message = "角色名称不为空")
    private String name;

    /**
     * 标识
     */
    @NotBlank(message = "角色标识符不为空")
    private String code;

    /**
     * 排序
     */
    @Max(value = 999, message = "排序最大值为999")
    @Min(value = 0, message = "排序最小值为0")
    private Integer sort;
}
