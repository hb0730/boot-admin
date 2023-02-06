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
     * 父类
     */
    private String parentId;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 路由名字
     */
    private String name;
    /**
     * 菜单名称
     */
    private String title;
    /**
     * 菜单icon
     */
    private String icon;
    /**
     * 是否显示
     */
    private Integer showLink;

    public Integer getShowLink() {
        return showLink == null ? 0 : showLink;
    }

    /**
     * 排序
     */
    private Integer rank;
    /**
     * 组件
     */
    private String component;
    /**
     * 是否显示父菜单
     */
    private Integer showParent;

    public Integer getShowParent() {
        return showParent == null ? 0 : showParent;
    }

    /**
     * 是否缓存该路由页面（开启后，会保存该页面的整体状态，刷新后会清空状态）
     */
    private Integer keepAlive;

    public Integer getKeepAlive() {
        return keepAlive == null ? 0 : keepAlive;
    }

    /**
     * 是否iframe
     */
    private Integer isFrame;

    public Integer getIsFrame() {
        return isFrame == null ? 0 : isFrame;
    }

    /**
     * 需要内嵌的iframe链接地址
     */
    private String frameSrc;
    /**
     * 类型（0：目录；1：菜单 ；2：按钮权限）
     */
    private Integer menuType;
    /**
     * 菜单权限编码，例如：“sys:schedule:list,sys:schedule:info”,多个逗号隔开
     */
    private String perms;
    /**
     * 是否启用
     */
    @TableField("is_enable")
    private Integer enabled;
}
