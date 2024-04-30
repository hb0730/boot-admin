package com.hb0730.security.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 登录信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Data
@Schema(description = "登录信息")
public class LoginInfoVo implements Serializable {
    @Schema(description = "用户id")
    private String id;
    @Schema(description = "username")
    private String username;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "token,只用于登录时返回，其他接口不返回")
    private String token;
    @Schema(description = "权限")
    private List<String> permissions;
    @Schema(description = "角色")
    private List<String> roles;
    /**
     * 商户标识
     */
    @Schema(description = "商户标识")
    private String sysCode;
}