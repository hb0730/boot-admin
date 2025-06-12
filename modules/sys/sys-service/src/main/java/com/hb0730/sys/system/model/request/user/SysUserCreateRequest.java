package com.hb0730.sys.system.model.request.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 创建用户
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
@Schema(description = "创建用户")
@Data
@EqualsAndHashCode
public class SysUserCreateRequest implements Serializable {
    /**
     * 用户名
     */
    @Schema(description = "用户账号")
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;
    /**
     * 用户密码
     */
    @Schema(description = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    private String password;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;
    /**
     * 用户邮箱
     */
    @Schema(description = "用户邮箱")
    private String email;
    @JsonIgnore
    @Schema(hidden = true)
    private String hashEmail;
    /**
     * 用户手机
     */
    @Schema(description = "用户手机")
    private String phone;
    @JsonIgnore
    @Schema(hidden = true)
    private String hashPhone;
    /**
     * 用户性别
     */
    @Schema(description = "用户性别")
    private Integer gender;
    /**
     * 用户状态
     */
    @Schema(description = "用户状态")
    private Boolean status;

    /**
     * 验证码
     */
    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String captchaKey;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @NotBlank(message = "时间戳不能为空")
    private String timestamp;


    @JsonIgnore
    @Schema(hidden = true)
    private String salt;
}
