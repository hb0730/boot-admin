package com.hb0730.sys.system.model.request.log;

import com.hb0730.mybatis.query.annotation.Between;
import com.hb0730.mybatis.query.annotation.Equals;
import com.hb0730.mybatis.query.doamin.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "操作日志查询")
public class SysOperatorLogQueryRequest extends PageRequest {
    @Schema(description = "操作人")
    @Equals
    private String operatorId;
    @Schema(description = "操作模块")
    @Equals
    private String module;
    @Schema(description = "操作类型")
    @Equals
    private String type;
    @Schema(description = "风险等级")
    @Equals
    private String riskLevel;
    /**
     * 操作结果
     */
    @Schema(description = "操作结果")
    @Equals
    private Integer result;
    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    @Between(value = "start_time")
    private List<String> executeTime;
}
