package com.hb0730.boot.admin.modules.sys.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/5
 */
@Data
@EqualsAndHashCode
@ToString
public class LoginResponse implements Serializable {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "用户昵称")
    private String nickname;
    @Schema(description = "权限")
    private List<String> permissions;
    @Schema(description = "访问token")
    private String token;
}
