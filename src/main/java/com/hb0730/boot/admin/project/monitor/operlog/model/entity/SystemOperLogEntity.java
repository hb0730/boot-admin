package com.hb0730.boot.admin.project.monitor.operlog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 业务操作日志
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_oper_log")
public class SystemOperLogEntity extends BusinessDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作账号
     */
    @TableId(value = "username")
    private String username;
    /**
     * 操作模块
     */
    @TableField("module")
    private String module;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 操作类型
     */
    @TableField("business_type")
    private Integer businessType;

    /**
     * 请求方法
     */
    @TableField("method")
    private String method;

    /**
     * 请求方式
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 请求地址
     */
    @TableField("oper_url")
    private String operUrl;

    /**
     * 操作地址
     */
    @TableField("oper_ip")
    private String operIp;

    /**
     * 操作地点
     */
    @TableField("oper_location")
    private String operLocation;

    /**
     * 请求参数
     */
    @TableField("oper_param")
    private String operParam;

    /**
     * 返回参数
     */
    @TableField("json_result")
    private String jsonResult;

    /**
     * 错误消息
     */
    @TableField("error_msg")
    private String errorMsg;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String MODULE = "module";

    public static final String TITLE = "title";

    public static final String STATUS = "status";

    public static final String BUSINESS_TYPE = "business_type";

    public static final String METHOD = "method";

    public static final String REQUEST_METHOD = "request_method";

    public static final String OPER_URL = "oper_url";

    public static final String OPER_IP = "oper_ip";

    public static final String OPER_LOCATION = "oper_location";

    public static final String OPER_PARAM = "oper_param";

    public static final String JSON_RESULT = "json_result";

    public static final String ERROR_MSG = "error_msg";

}
