package com.hb0730.rpc.sys.system.domain.query;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.IsNull;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.common.api.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class PermissionQuery extends BaseQuery {
    /**
     * 菜单名称
     */
    @Like
    @Schema(description = "菜单名称")
    private String title;

    /**
     * 父级id
     */
    @Equals
    @Schema(description = "父级id")
    private Integer parentId;

    /**
     * 父级id为空
     */
    @IsNull("parentId")
    @Schema(hidden = true)
    private String parentIdIsNull;
}
