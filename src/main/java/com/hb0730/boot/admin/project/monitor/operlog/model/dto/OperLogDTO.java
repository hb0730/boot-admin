package com.hb0730.boot.admin.project.monitor.operlog.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.hb0730.boot.admin.domain.model.domain.ExcelDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperLogDTO extends ExcelDomain {
    /**
     * id
     */
    @ExcelIgnore
    private Long id;

    /**
     * 操作模块
     */
    @ExcelProperty(value = "操作模块", index = 0)
    private String module;

    /**
     * 标题
     */
    @ExcelProperty(value = "操作内容", index = 1)
    private String title;

    /**
     * 操作类型
     */
    @ExcelIgnore
    private Integer businessType;

    /**
     * <p>
     * 操作类型
     * </p>
     */
    @ExcelProperty(value = "操作类型", index = 2)
    private String businessTypeName;

    /**
     * 操作账号
     */
    @ExcelProperty(value = "操作账号", index = 3)
    private String username;


    /**
     * 操作地址
     */
    @ExcelProperty(value = "操作ip", index = 4)
    private String operIp;

    /**
     * 操作地点
     */
    @ExcelProperty(value = "操作地点", index = 5)
    private String operLocation;

    /**
     * 备注
     */
    @ExcelIgnore
    private String description;


    /**
     * 状态
     */
    @ExcelProperty(value = "状态 (1 成功 | 0 失败)", index = 6)
    private Integer status;

    /**
     * 请求时间
     */
    @ExcelProperty(value = "请求时间", index = 7)
    private Date createTime;
    /**
     * 请求地址
     */
    @ExcelProperty(value = "请求地址", index = 8)
    private String operUrl;

    /**
     * 请求方式
     */
    @ExcelProperty(value = "请求方式", index = 9)
    private String requestMethod;


    /**
     * 请求方法
     */
    @ExcelProperty(value = "请求方法", index = 10)
    private String method;

    /**
     * 请求参数
     */
    @ExcelProperty(value = "请求参数", index = 11)
    private String operParam;

    /**
     * 返回参数
     */
    @ExcelProperty(value = "返回参数", index = 12)
    private String jsonResult;

    /**
     * 错误消息
     */
    @ExcelProperty(value = "错误消息", index = 13)
    private String errorMsg;
}
