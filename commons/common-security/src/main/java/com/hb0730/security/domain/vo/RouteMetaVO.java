package com.hb0730.security.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Data
@ToString
@Schema(description = "路由元信息")
// 不输出为null的字段
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteMetaVO implements Serializable {
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
     * 是否显示在菜单栏
     */
    @Schema(description = "是否显示在菜单栏")
    private Boolean showLink;
    /**
     * 菜单排序，值越高排的越后（只针对顶级路由）
     */
    @Schema(description = "菜单排序，值越高排的越后（只针对顶级路由）")
    private Integer rank;
    /**
     * 是否显示父级菜单
     */
    @Schema(description = "是否显示父级菜单")
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

}