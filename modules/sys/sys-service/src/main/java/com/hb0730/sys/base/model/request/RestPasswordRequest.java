package com.hb0730.sys.base.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@Data
@EqualsAndHashCode
@Schema(description = "重置密码")
public class RestPasswordRequest implements Serializable {
    @NotBlank(message = "旧密码不能为空")
    @Schema(description = "旧密码")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    @Schema(description = "新密码")
    private String newPassword;
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @Schema(description = "验证码Key")
    private String captchaKey;

    /**
     * 时间戳
     */
    @NotBlank(message = "时间戳不能为空")
    @Schema(description = "时间戳")
    private String timestamp;
}
