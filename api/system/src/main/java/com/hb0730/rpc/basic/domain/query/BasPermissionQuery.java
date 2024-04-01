package com.hb0730.rpc.basic.domain.query;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.commons.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/31
 */
@Getter
@Setter
@Accessors(chain = true)
public class BasPermissionQuery extends BaseQuery {

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @Like
    private String name;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @Equals
    private Boolean enabled;
}
