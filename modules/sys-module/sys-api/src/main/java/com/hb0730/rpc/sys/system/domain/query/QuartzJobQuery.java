package com.hb0730.rpc.sys.system.domain.query;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.common.api.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Getter
@Setter
@Accessors(chain = true)
public class QuartzJobQuery extends BaseQuery {
    @Like
    @Schema(description = "任务名称")
    private String jobName;

    @Equals
    @Schema(description = "任务分组/商户识别码")
    private String sysCode;
    @Schema(description = "是否启用")
    private Boolean enabled;
}
