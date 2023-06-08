package com.hb0730.boot.admin.modules.sys.system.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/25
 */
@Data
@EqualsAndHashCode
@ToString
public class PermissionVO implements Serializable {

    /**
     * ID
     */
    @Schema(description = "id")
    protected String id;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    protected String createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    protected LocalDateTime created;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    protected String modifiedBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    protected LocalDateTime modified;
    /**
     * 父类
     */
    @Schema(description = "父ID")
    private String parentId;
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;
    /**
     * 路由名字
     */
    @Schema(description = "路由名称")
    private String name;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String title;
    /**
     * 菜单icon
     */
    @Schema(description = "菜单icon")
    private String icon;
    /**
     * 是否显示
     */
    @Schema(description = "是否显示")
    private boolean showLink;

    public Integer getShowLink() {
        return showLink ? 1 : 0;
    }

    /**
     * 排序
     */
    @Schema(description = "排序")
    @TableField(value = "`rank`")
    private Integer rank;
    /**
     * 组件
     */
    @Schema(description = "组件地址")
    private String component;
    /**
     * 是否显示父菜单
     */
    @Schema(description = "是否显示父菜单")
    private boolean showParent;

    public Integer getShowParent() {
        return showParent ? 1 : 0;
    }

    /**
     * 是否缓存该路由页面（开启后，会保存该页面的整体状态，刷新后会清空状态）
     */
    @Schema(description = "是否缓存该路由页面")
    private boolean keepAlive;

    public Integer getKeepAlive() {
        return keepAlive ? 1 : 0;
    }

    /**
     * 是否iframe
     */
    @Schema(description = "是否iframe")
    private Integer isFrame;

    /**
     * 需要内嵌的iframe链接地址
     */
    @Schema(description = "需要内嵌的iframe链接地址")
    private String frameSrc;
    /**
     * 类型（0：目录；1：菜单 ；2：按钮权限）
     */
    @Schema(description = "类型")
    @NotBlank(message = "菜单类型不为空")
    private Integer menuType;
    /**
     * 菜单权限编码，例如：“sys:schedule:list,sys:schedule:info”,多个逗号隔开
     */
    @Schema(description = "菜单权限编码")
    private String perms;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private boolean enabled;

    public Integer getEnabled() {
        return enabled ? 1 : 0;
    }
}
