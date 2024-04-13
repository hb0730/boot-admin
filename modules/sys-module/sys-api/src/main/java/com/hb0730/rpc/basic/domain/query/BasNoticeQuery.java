package com.hb0730.rpc.basic.domain.query;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.blinkfox.fenix.specification.annotation.OrIsNull;
import com.hb0730.common.api.BaseQuery;
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
    @Like
    @Schema(hidden = true)
    private String orgId;

    @OrIsNull
    @Schema(hidden = true)
    private String orgIdIsNull;

    @Equals
    @Schema(hidden = true)
    private Boolean enabled;
}
