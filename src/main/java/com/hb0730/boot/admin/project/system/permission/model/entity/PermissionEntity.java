package com.hb0730.boot.admin.project.system.permission.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 权限
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_permission" )
public class PermissionEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description" )
    private String description;

    /**
     * id
     */
    @TableId("id" )
    private Long id;

    /**
     * 名称
     */
    @TableField("name" )
    private String name;

    /**
     * 标识符
     */
    @TableField("permission" )
    private String permission;

    /**
     * 菜单id
     */
    @TableField("menu_id" )
    private Long menuId;

    /**
     * 排序
     */
    @TableField("sort" )
    private Integer sort;


    public static final String DESCRIPTION = "description" ;

    public static final String ID = "id" ;

    public static final String NAME = "name" ;

    public static final String PERMISSION = "permission" ;

    public static final String MENU_ID = "menu_id" ;

    public static final String SORT = "sort" ;

}
