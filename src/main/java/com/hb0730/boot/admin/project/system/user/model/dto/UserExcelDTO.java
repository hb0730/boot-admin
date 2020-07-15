package com.hb0730.boot.admin.project.system.user.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.hb0730.boot.admin.commons.domain.model.domain.ExcelDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * user excel
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserExcelDTO extends ExcelDomain {
    @ExcelIgnore
    private Long id;
    /**
     * 用户账号
     */
    @ExcelProperty(value = "用户账号", index = 0)
    private String username;

    /**
     * 用户密码
     */
    @ExcelProperty(value = "用户密码", index = 1)
    private String password;
    /**
     * 用户昵称
     */
    @ExcelProperty(value = "用户昵称", index = 2)
    private String nickName;

    /**
     * 用户邮箱
     */
    @ExcelProperty(value = "用户邮箱", index = 3)
    private String email;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "用户手机号", index = 4)
    private String phonenumber;

    /**
     * 用户性别
     */
    @ExcelProperty(value = "用户性别(-1 未知|1 男|2 女)", index = 5)
    private Integer sex;

    /**
     * 用户头像
     */
    @ExcelIgnore
    private String avatar;

    /**
     * 部门ID
     */
    @ExcelIgnore
    private Long deptId;

    /**
     * 组织编码
     */
    @ExcelProperty(value = "组织编码", index = 6)
    private String deptNumber;
    /**
     * 组织名称
     */
    @ExcelProperty(value = "组织名称", index = 7)
    private String deptName;
    /**
     * 排序
     */
    @ExcelProperty(value = "排序", index = 8)
    private Integer sort;
}
