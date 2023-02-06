package com.hb0730.boot.admin.modules.sys.system.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * vue 路由菜单
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/6
 */
@Data
@EqualsAndHashCode
@ToString
public class VueMenuRouteVO implements Serializable {
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;
    /**
     * 路由名字（必须保持唯一）
     */
    @Schema(description = "路由名字")
    private String name;
    /**
     * 按需加载需要展示的页面
     */
    @Schema(description = "组件")
    private String component;
    /**
     * 路由元信息
     */
    @Schema(description = "路由元信息")
    private VueMenuRouteMeta meta;
    /**
     * 菜单
     */
    @Schema(description = "菜单")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<VueMenuRouteVO> children;
}
