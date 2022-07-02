package com.hb0730.boot.admin.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 响应
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class LoginUser extends User {
    private String accessToken;
}
