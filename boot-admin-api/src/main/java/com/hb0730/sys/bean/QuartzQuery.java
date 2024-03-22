package com.hb0730.sys.bean;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.base.base.BasePageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/21
 */
@Getter
@Setter
@Accessors(chain = true)
public class QuartzQuery extends BasePageQuery {
    @Like
    @Parameter(description = "任务名称")
    private String jobName;
    /**
     * 状态
     */
    @Parameter(description = "状态")
    @Equals
    private Boolean enabled;
}
