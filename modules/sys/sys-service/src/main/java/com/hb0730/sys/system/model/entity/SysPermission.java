package com.hb0730.sys.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hb0730.core.entity.BizEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 菜单与权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission extends BizEntity {
    /**
     * 父级ID
     */
    @TableField(value = "parent_id", updateStrategy = FieldStrategy.ALWAYS, insertStrategy = FieldStrategy.ALWAYS)
    private String parentId;
    /**
     * 是否叶子节点: 1:是 0:不是
     */
    private Boolean isLeaf;

    /*==========================路由信息==========================*/
    /**
     * 路由地址
     */
    private String routePath;
    /**
     * 路由名称（必须保持唯一）
     */
    private String routeName;
    /**
     * 路由重定向
     */
    private String redirect;
    /**
     * 路由组件
     */
    private String component;

    /*==========================菜单信息==========================*/
    /**
     * 菜单名称
     */
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否隐藏
     */
    private Boolean isHidden;

    /**
     * 是否缓存,如果缓存，请保持routerName与组件名称一致
     */
    private Boolean isKeepAlive;
    /**
     * 是否固定在标签视图
     */
    private Boolean isAffix;

    /**
     * 是否大屏
     */
    private Boolean isFullScreen;
    /**
     * 是否需要登陆
     */
    private Boolean isRequireLogin = true;
    /**
     * iframe
     */
    private String frameSrc;

    /**
     * 菜单类型 0 目录 1 菜单 2 按钮
     *
     * @see com.hb0730.zoom.base.enums.MenuTypeEnums
     */
    private Integer menuType;
    /**
     * 权限标识
     */
    private String perms;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Boolean status = true;

    /**
     * 是否 禁用
     */
    public boolean isDisabled() {
        return null != status && !status;
    }

}
