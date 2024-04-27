package com.hb0730.rpc.basic.domain.query;

import com.hb0730.common.api.BaseQuery;
import com.hb0730.jpa.specification.annotation.Equals;
import com.hb0730.jpa.specification.annotation.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 角色查询
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/31
 */
@Getter
@Setter
@Accessors(chain = true)
public class BasRoleQuery extends BaseQuery {
    /**
     * 角色名称
     */
    @Like
    @Schema(description = "角色名称")
    private String name;
    /**
     * 角色标识
     */
    @Equals
    @Schema(description = "角色标识")
    private String code;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @Equals
    private Boolean enabled;
    /**
     * 商户识别码
     */
    @Equals
    @Schema(description = "商户识别码", hidden = true)
    private String sysCode;
}
