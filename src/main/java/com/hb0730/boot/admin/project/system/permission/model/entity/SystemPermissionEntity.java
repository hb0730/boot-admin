package com.hb0730.boot.admin.project.system.permission.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@TableName("t_system_permission")
public class SystemPermissionEntity extends BusinessDomain {

    private static final long serialVersionUID=1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 权限名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限英文名称
     */
    @TableField("enname")
    private String enname;

    /**
     * 权限标识(路径)
     */
    @TableField("mark")
    private String mark;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ENNAME = "enname";

    public static final String MARK = "mark";

    public static final String SORT = "sort";

}
