package com.hb0730.boot.admin.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录body
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
public class LoginBody implements Serializable {
    @NotBlank(message = "用户账号不为空")
    private String username;
    @NotBlank(message = "用户密码不为空")
    private String password;
}
