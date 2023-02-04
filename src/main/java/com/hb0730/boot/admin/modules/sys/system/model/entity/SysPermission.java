package com.hb0730.boot.admin.modules.sys.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hb0730.boot.admin.base.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 菜单与权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class SysPermission extends BaseEntity {
    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 权限字符串
     */
    private String perms;
    /**
     * 类型（0：目录；1：菜单 ；2：按钮）
     */
    private Integer menuType;
    /**
     * 是否叶子节点: 1:是 0:不是
     */
    private boolean leaf;
    /**
     * 是否路由菜单: 0:不是 1:是（默认值1）
     */
    @TableField(value = "is_route")
    private boolean route;
}
