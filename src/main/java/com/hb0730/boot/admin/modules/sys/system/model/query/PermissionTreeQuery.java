package com.hb0730.boot.admin.modules.sys.system.model.query;

import com.hb0730.boot.admin.data.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 菜单权限树形查询参数
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PermissionTreeQuery extends BasePageQuery {
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @Parameter(description = "菜单名称")
    private String title;
    /**
     * 菜单状态
     */
    @Schema(description = "菜单状态")
    @Parameter(description = "菜单状态")
    private Integer enabled;
}
