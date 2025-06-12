package com.hb0730.sys.base.model.dto;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.base.utils.TreeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Data
@EqualsAndHashCode
@ToString
public class PermissionDTO implements TreeUtil.Node<PermissionDTO, String> {
    /**
     * ID
     */
    private String id;

    /**
     * 父级ID
     */
    private String parentId;
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
     * iframe src
     */
    private String frameSrc;

    /**
     * 是否需要登陆
     */
    private Boolean isRequireLogin = true;

    /**
     * 菜单类型 0 目录 1 菜单 2 按钮
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

    private List<PermissionDTO> children;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public List<PermissionDTO> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<PermissionDTO> children) {
        this.children = children;
    }

    @Override
    public boolean isRoot() {
        return StrUtil.isBlank(parentId) || parentId.equals("0");
    }
}
