package com.hb0730.operator.log.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志模型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@Data
public class OperatorLogModel implements Serializable {
    /**
     * userId
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * traceId
     */
    private String traceId;

    /**
     * 请求 ip
     */
    private String address;

    /**
     * 请求地址
     */
    private String location;

    /**
     * user-agent
     */
    private String userAgent;

    /**
     * 日志
     */
    private String logInfo;

    /**
     * 风险等级
     */
    private String riskLevel;

    /**
     * 模块
     */
    private String module;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 参数
     */
    private String extra;

    /**
     * 操作结果 0失败 1成功
     */
    private Integer result;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 返回值
     */
    private String returnValue;

    /**
     * 操作时间
     */
    private Integer duration;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
}
