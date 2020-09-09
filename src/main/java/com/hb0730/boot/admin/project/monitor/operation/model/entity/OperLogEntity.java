package com.hb0730.boot.admin.project.monitor.operation.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志 
 * </p>
 *
 * @author bing_huang
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_oper_log")
public class OperLogEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 操作人员
     */
    @TableField("username")
    private String username;

    /**
     * 操作类型
     */
    @TableField("oper_type")
    private Integer operType;

    /**
     * 操作ip
     */
    @TableField("oper_ip")
    private String operIp;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 请求地址
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 请求方法
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 操作方法
     */
    @TableField("oper_method")
    private String operMethod;

    /**
     * 请求参数
     */
    @TableField("request_params")
    private String requestParams;

    /**
     * 响应参数
     */
    @TableField("request_result")
    private String requestResult;

    /**
     * 错误信息
     */
    @TableField("error_message")
    private String errorMessage;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String OPER_TYPE = "oper_type";

    public static final String OPER_IP = "oper_ip";

    public static final String STATUS = "status";

    public static final String REQUEST_URL = "request_url";

    public static final String REQUEST_METHOD = "request_method";

    public static final String OPER_METHOD = "oper_method";

    public static final String REQUEST_PARAMS = "request_params";

    public static final String REQUEST_RESULT = "request_result";

    public static final String ERROR_MESSAGE = "error_message";

}
