package com.hb0730.boot.admin.project.system.menu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_menu")
public class SystemMenuEntity extends BusinessDomain {

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
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 英文名称
     */
    @TableField("enname")
    private String enname;

    /**
     * 路径
     */
    @TableField("url")
    private String url;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 父id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 是否有下级
     */
    @TableField("has_child")
    private Integer hasChild;

    /**
     * 是否为根节点
     */
    @TableField("is_root")
    private Integer isRoot;

    /**
     * 菜单级别
     */
    @TableField("level")
    private Integer level;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ENNAME = "enname";

    public static final String URL = "url";

    public static final String ICON = "icon";

    public static final String SORT = "sort";

    public static final String PARENT_ID = "parent_id";

    public static final String HAS_CHILD = "has_child";

    public static final String IS_ROOT = "is_root";

    public static final String LEVEL = "level";

}
