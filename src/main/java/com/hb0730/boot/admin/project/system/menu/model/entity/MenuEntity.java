package com.hb0730.boot.admin.project.system.menu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 菜单
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_menu")
public class MenuEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

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
     * 是否外链
     */
    @TableField("is_external")
    private Integer external;
    /**
     * iframe菜单
     */
    @TableField("is_iframe")
    private Integer iframe;
    /**
     * cache
     */
    @TableField("is_cache")
    private Integer cache;
    /**
     * 是否可见
     */
    @TableField("is_hidden")
    private Integer hidden;
    /**
     * 是否18n;
     */
    @TableField("is_i18n")
    private Integer i18n;
    /**
     * 是否显示父级菜单
     */
    @TableField("is_show_parent")
    private Integer showParent;
    /**
     * 名称
     */
    @TableField("name")
    private String title;
    /**
     * 组件名称
     */
    @TableField("enname")
    private String enname;

    /**
     * 父菜单id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 路由地址
     */
    @TableField("path")
    private String path;

    /**
     * 组件地址
     */
    @TableField("component")
    private String component;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 展示顺序
     */
    @TableField("sort")
    private Integer sort;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NAME = "title";

    public static final String ENNAME = "enname";

    public static final String PARENT_ID = "parent_id";

    public static final String PATH = "path";

    public static final String COMPONENT = "component";

    public static final String ICON = "icon";

    public static final String SORT = "sort";

}
