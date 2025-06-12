package com.hb0730.sys.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "操作日志")
public class SysOperatorLogVO implements Serializable {
    @Schema(description = "ID")
    private String id;
    /**
     * 操作人ID
     */
    @Schema(description = "操作人ID")
    private String operatorId;
    /**
     * 操作人
     */
    @Schema(description = "操作人")
    private String operator;
    /**
     * traceId
     */
    @Schema(description = "traceId")
    private String traceId;
    /**
     * 请求ip
     */
    @Schema(description = "请求ip")
    private String address;
    /**
     * 地址
     */
    @Schema(description = "地址")
    private String location;

    /**
     * userAgent
     */
    @Schema(description = "userAgent")
    private String userAgent;

    /**
     * 操作模块
     */
    @Schema(description = "操作模块")
    private String module;

    /**
     * 操作类型
     */
    @Schema(description = "操作类型")
    private String type;
    /**
     * 风险等级
     */
    @Schema(description = "风险等级")
    private String riskLevel;
    /**
     * 操作内容
     */
    @Schema(description = "操作内容")
    private String logInfo;
    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String extra;
    /**
     * 操作结果，0-成功，1-失败
     */
    @Schema(description = "操作结果，0-成功，1-失败")
    private Integer result;
    /**
     * 错误信息
     */
    @Schema(description = "错误信息")
    private String errorMessage;
    /**
     * 返回值
     */
    @Schema(description = "返回值")
    private String returnValue;
    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    private Integer duration;
    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
}
