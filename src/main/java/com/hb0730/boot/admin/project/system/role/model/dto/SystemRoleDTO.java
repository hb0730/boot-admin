package com.hb0730.boot.admin.project.system.role.model.dto;

import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemRoleDTO extends BusinessDomain {

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
     * 英文名称
     */
    private String enname;

    /**
     * 排序
     */
    private Integer sort;
}
