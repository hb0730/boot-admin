package com.hb0730.sys.system.model.request.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
@Data
@EqualsAndHashCode
@Schema(description = "更新用户")
public class SysUserUpdateRequest implements Serializable {
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;
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
    /**
     * 用户手机号
     */
    @Schema(description = "用户手机号")
    private String phone;

    /**
     * 用户性别 0-未知 1-男 2-女
     */
    @Schema(description = "用户性别 0-未知 1-男 2-女")
    private Integer gender;

    /**
     * 用户状态 0-正常 1-禁用
     */
    @Schema(description = "用户状态 0-正常 1-禁用")
    private Boolean status;

    /**
     * 验证码
     */
    @Schema(description = "验证码")
    @NotBlank(message = "验证码KEY不能为空")
    private String captchaKey;
    /**
     * 验证码
     */
    @Schema(description = "当前时间戳")
    @NotBlank(message = "当前时间戳不能为空")
    private String timestamp;


    @JsonIgnore
    @Schema(hidden = true)
    private String hashPhone;

    @JsonIgnore
    @Schema(hidden = true)
    private String hashEmail;
}
