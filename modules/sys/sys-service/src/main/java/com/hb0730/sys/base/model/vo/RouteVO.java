package com.hb0730.sys.base.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * vue路由
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Data
@EqualsAndHashCode
@ToString
@Schema(description = "vue路由信息")
public class RouteVO implements Serializable {
    /**
     * 路由路径
     */
    @Schema(description = "路由路径")
    private String path;
    /**
     * 路由名称
     */
    @Schema(description = "路由名称")
    private String name;
    /**
     * 路由组件
     */
    @Schema(description = "路由组件")
    private String component;
    /**
     * 路由重定向
     */
    @Schema(description = "路由重定向")
    private String redirect;
    /**
     * 路由元数据
     */
    @Schema(description = "路由元数据")
    private RouteMetaVO meta;
    /**
     * 子路由
     */
    @Schema(description = "子路由")
    private List<RouteVO> children;
}
