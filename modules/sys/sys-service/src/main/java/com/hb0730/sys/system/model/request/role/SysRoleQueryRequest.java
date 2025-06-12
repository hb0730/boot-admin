package com.hb0730.sys.system.model.request.role;

import com.hb0730.mybatis.query.annotation.Equals;
import com.hb0730.mybatis.query.annotation.Like;
import com.hb0730.mybatis.query.doamin.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色查询
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色查询")
public class SysRoleQueryRequest extends PageRequest {
    /**
     * 角色名称
     */
    @Like
    @Schema(description = "角色名称,支持模糊查询")
    private String roleName;
    /**
     * 角色标识
     */
    @Schema(description = "角色标识")
    @Equals
    private String roleCode;

    /**
     * 角色状态
     */
    @Schema(description = "角色状态")
    @Equals
    private Boolean status = true;
}
