package com.hb0730.boot.admin.security.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@Data
public class Login implements Serializable {
    @NotBlank(message = "用户账号不为空")
    private String username;
    @NotBlank(message = "用户密码不为空")
    private String password;
}
