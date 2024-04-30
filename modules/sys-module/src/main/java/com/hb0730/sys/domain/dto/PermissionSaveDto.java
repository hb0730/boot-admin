package com.hb0730.sys.domain.dto;

import com.hb0730.mybatis.core.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 更新权限 dto
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PermissionSaveDto extends BaseDto {
    @Schema(description = "id")
    private String id;
    /**
     * 父类id
     */
    @Schema(description = "父类id")
    private String parentId;
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;
    /**
     * 路由名称（必须保持唯一）
     */
    @Schema(description = "路由名称（必须保持唯一）")
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
    /*==========================meta==========================*/
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String title;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;
    /**
     * 是否展示
     */
    @Schema(description = "是否展示")
    private Boolean showLink;
    /**
     * 菜单排序，值越高排的越后（只针对顶级路由）
     */
    @Schema(description = "菜单排序，值越高排的越后（只针对顶级路由）")
    private Integer rank;
    /**
     * 是否显示父菜单
     */
    @Schema(description = "是否显示父菜单")
    private Boolean showParent;
    /**
     * 是否缓存
     */
    @Schema(description = "是否缓存")
    private Boolean keepAlive;
    /**
     * 需要内嵌的iframe链接地址
     */
    @Schema(description = "需要内嵌的iframe链接地址")
    private String frameSrc;
    /**
     * 菜单类型 1 菜单 2 iframe 3 外链 4 按钮
     */
    @Schema(description = "菜单类型 1 菜单 2 iframe 3 外链 4 按钮")
    @NotBlank(message = "菜单类型不能为空")
    private Integer menuType;
    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String permission;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @NotBlank(message = "是否启用不能为空")
    private Boolean enabled;
}