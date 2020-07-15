package com.hb0730.boot.admin.project.system.permission.model.vo;

import com.hb0730.boot.admin.commons.utils.convert.InputConverter;
import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.project.system.permission.model.entity.SystemPermissionEntity;
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
public class SystemPermissionVO extends BusinessVO  implements InputConverter<SystemPermissionEntity> {

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
