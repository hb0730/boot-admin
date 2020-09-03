package com.hb0730.boot.admin.project.system.role.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String name;

    /**
     * 标识
     */
    private String code;

    /**
     * 排序
     */
    private Integer sort;
}
