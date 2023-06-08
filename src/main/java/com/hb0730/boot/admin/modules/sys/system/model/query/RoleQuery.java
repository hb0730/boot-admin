package com.hb0730.boot.admin.modules.sys.system.model.query;

import com.hb0730.boot.admin.data.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 权限查询
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class RoleQuery extends BasePageQuery {
    /**
     * 角色code
     */
    @Parameter(description = "角色code")
    private String roleCode;
    /**
     * 角色name
     */
    @Parameter(description = "角色name")
    private String roleName;
    /**
     * 是否启用
     */
    @Parameter(description = "是否启用")
    private Integer enabled;
}
