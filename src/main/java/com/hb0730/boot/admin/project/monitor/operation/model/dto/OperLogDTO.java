package com.hb0730.boot.admin.project.monitor.operation.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperLogDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 操作人员
     */
    private String username;

    /**
     * 操作类型
     */
    private Integer operType;

    /**
     * 操作ip
     */
    private String operIp;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 操作方法
     */
    private String operMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应参数
     */
    private String requestResult;

    /**
     * 错误信息
     */
    private String errorMessage;
}
