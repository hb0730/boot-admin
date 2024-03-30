package com.hb0730.rpc.sys.system.domain.query;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.commons.BaseQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleQuery extends BaseQuery {
    /**
     * 角色名称
     */
    @Like
    @Parameter(description = "角色名称")
    private String name;
    /**
     * 角色编码
     */
    @Equals
    @Parameter(description = "角色编码")
    private String code;
    /**
     * 是否启用
     */
    @Parameter(description = "是否启用")
    @Equals
    private Boolean enabled;
}