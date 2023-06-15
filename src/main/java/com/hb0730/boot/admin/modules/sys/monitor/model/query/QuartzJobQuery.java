package com.hb0730.boot.admin.modules.sys.monitor.model.query;

import com.hb0730.boot.admin.data.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 定时任务在线管理查询条件
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class QuartzJobQuery extends BasePageQuery {
    @Schema(description = "任务类名")
    private String jobClassName;
    @Schema(description = "任务状态")
    private Integer status;
}
