package com.hb0730.boot.admin.project.system.permission.model.dto;

import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统权限
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemPermissionDTO extends BusinessVO {

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限英文名称
     */
    private String enname;

    /**
     * 权限标识(路径)
     */
    private String mark;

    /**
     * 排序
     */
    private Integer sort;
}
