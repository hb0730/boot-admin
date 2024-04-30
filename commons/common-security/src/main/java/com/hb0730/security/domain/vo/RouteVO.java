package com.hb0730.security.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Getter
@Setter
@Schema(description = "路由菜单")
// 不输出为null的字段
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteVO implements TreeVO<RouteVO> {
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;
    /**
     * 路由名称（必须保持唯一）
     */
    @Schema(description = "路由名称（必须保持唯一）")
    private String name;
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
    /**
     * 路由元信息
     */
    @Schema(description = "路由元信息")
    private RouteMetaVO meta;

    /**
     * 子路由
     *
     * @see <a href="https://github.com/swagger-api/swagger-ui/issues/3325">嵌套集合展示问题</a>
     * @see <a href="https://github.com/springdoc/springdoc-openapi/issues/1999">Nested lists are not handled</a>
     */
    @ArraySchema(schema = @Schema(description = "子路由", implementation = Object.class, allOf = RouteVO.class))
    private List<RouteVO> children;
}