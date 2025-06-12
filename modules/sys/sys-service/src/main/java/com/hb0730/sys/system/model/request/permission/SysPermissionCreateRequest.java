package com.hb0730.sys.system.model.request.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Data
@Schema(description = "更新&创建权限请求")
public class SysPermissionCreateRequest implements Serializable {
    /**
     * 父级ID
     */
    @Schema(description = "父级ID")
    private String parentId;
    /**
     * 是否叶子节点: 1:是 0:不是
     */
    @JsonIgnore
    @Schema(hidden = true)
    private Boolean isLeaf;

    /*==========================路由信息==========================*/
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String routePath;
    /**
     * 路由名称（必须保持唯一）
     */
    @Schema(description = "路由名称")
    private String routeName;
    /**
     * 路由重定向
     */
    @Schema(description = "路由重定向")
    private String redirect;
    /**
     * 路由组件
     */
    @Schema(description = "路由组件")
    private String component;

    /*==========================菜单信息==========================*/
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String title;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;
    /**
     * 是否隐藏
     */
    @Schema(description = "是否隐藏")
    private Boolean isHidden;

    /**
     * 是否缓存,如果缓存，请保持routerName与组件名称一致
     */
    @Schema(description = "是否缓存,如果缓存，请保持routerName与组件名称一致")
    private Boolean isKeepAlive;

    /**
     * 是否固定在标签视图
     */
    @Schema(description = "是否固定在标签视图")
    private Boolean isAffix;

    /**
     * 是否大屏
     */
    @Schema(description = "是否大屏")
    private Boolean isFullScreen;
    /**
     * 是否需要登陆
     */
    @Schema(description = "是否需要登陆")
    private Boolean isRequireLogin = true;

    /**
     * iframe
     */
    @Schema(description = "iframe url")
    private String frameSrc;

    /**
     * 菜单类型 0 目录 1 菜单 2 按钮
     *
     * @see com.hb0730.sys.enums.MenuTypeEnums
     */
    @Schema(description = "菜单类型 0 菜单 1 frame 2 外链 3 按钮")
    private Integer menuType;
    /**
     * 权限标识
     */
    @Schema(description = "权限标识,多个用逗号分隔,如：sys:user:list,sys:user:add")
    private String perms;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean status;
}
