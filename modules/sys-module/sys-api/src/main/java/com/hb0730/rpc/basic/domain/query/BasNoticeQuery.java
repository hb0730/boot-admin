package com.hb0730.rpc.basic.domain.query;

import com.hb0730.common.api.BaseQuery;
import com.hb0730.jpa.specification.annotation.Equals;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 公告
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
@Getter
@Setter
@Accessors(chain = true)
public class BasNoticeQuery extends BaseQuery {
    @Schema(hidden = true)
    private String userId;
    /**
     * 商户ID
     */
    @Equals(allowNull = true)
    @Schema(hidden = true)
    private String orgId;


    @Equals
    @Schema(hidden = true)
    private Boolean enabled;
}
