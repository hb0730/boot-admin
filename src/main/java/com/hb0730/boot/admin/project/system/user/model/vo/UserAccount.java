package com.hb0730.boot.admin.project.system.user.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户账号信息
 *
 * @author bing_huang
 * @see 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
public class UserAccount implements Serializable {
    /**
     * 原密码
     */
    @NotBlank(message = "原密码不为空")
    private String oldPassword;
    /**
     * 新密码
     */
    @NotBlank(message = "新密码不为空")
    private String newPassword;
    /**
     * 原密码
     */
    @NotBlank(message = "新密码不为空")
    private String newPassword2;
}
