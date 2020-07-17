package com.hb0730.boot.admin.project.monitor.operlog.model.vo;

import com.hb0730.boot.admin.model.converter.InputConverter;
import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
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
public class SystemOperLogVO extends BusinessVO implements InputConverter<SystemOperLogEntity> {

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
     * 操作账号
     */
    private String username;
    /**
     * 操作模块
     */
    private String module;

    /**
     * 标题
     */
    private String title;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 操作类型
     */
    private Integer businessType;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求地址
     */
    private String operUrl;

    /**
     * 操作地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 错误消息
     */
    private String errorMsg;
}
