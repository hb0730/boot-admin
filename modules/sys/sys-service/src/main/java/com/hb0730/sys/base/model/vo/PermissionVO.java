package com.hb0730.sys.base.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Data
@EqualsAndHashCode
@ToString
@Schema(description = "用户权限&菜单")
public class PermissionVO implements Serializable {
    /**
     * 菜单
     */
    @Schema(description = "路由菜单")
    private List<RouteVO> menu;
    /**
     * 权限
     */
    @Schema(description = "按钮/访问权限")
    private List<String> auth;
}
