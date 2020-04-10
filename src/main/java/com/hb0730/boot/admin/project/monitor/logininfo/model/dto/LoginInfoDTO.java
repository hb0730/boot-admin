package com.hb0730.boot.admin.project.monitor.logininfo.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.hb0730.boot.admin.commons.domain.ExcelDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 登录日志导出
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginInfoDTO extends ExcelDomain {
    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = -1)
    private String description;

    /**
     * id
     */
    @ExcelIgnore
    private Long id;

    /**
     * 用户账号
     */
    @ExcelProperty(value = "登录账号", index = 0)
    private String username;

    /**
     * 登录地址
     */
    @ExcelProperty(value = "登录ip", index = 1)
    private String ipaddr;
    /**
     * 登录地点
     */
    @ExcelProperty(value = "登录地点", index = 2)
    private String loginLocation;

    /**
     * 浏览器
     */
    @ExcelProperty(value = "浏览器", index = 3)
    private String browser;


    /**
     * 操作系统
     */
    @ExcelProperty(value = "操作系统", index = 4)
    private String os;
    /**
     * 登录状态
     */
    @ExcelProperty(value = "状态 (1 成功|0 失败)", index = 5)
    private Integer status;

    /**
     * 提示信息
     */
    @ExcelProperty(value = "登录信息", index = 6)
    private String message;
}
