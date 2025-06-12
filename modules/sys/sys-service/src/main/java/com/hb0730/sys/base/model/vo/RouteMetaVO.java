package com.hb0730.sys.base.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/4
 */
@Data
@EqualsAndHashCode
@ToString
@Schema(description = "路由元数据")
public class RouteMetaVO implements Serializable {
    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;
    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;
    /**
     * 是否隐藏
     */
    @Schema(description = "是否隐藏")
    @JsonProperty("isHide")
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
    @JsonProperty("isAffix")
    private Boolean isAffix;

    /**
     * 是否大屏
     */
    @Schema(description = "是否大屏")
    @JsonProperty("isFull")
    private Boolean isFullScreen;
    /**
     * iframe src
     */
    @Schema(description = "iframe src")
    private String frameSrc;

    /**
     * 是否外链
     */
    @Schema(description = "是否外链")
    @JsonProperty("isLink")
    private Boolean external;

    /**
     * 是否需要登陆
     */
    @Schema(description = "是否需要登陆")
    private Boolean requireLogin = true;
}
