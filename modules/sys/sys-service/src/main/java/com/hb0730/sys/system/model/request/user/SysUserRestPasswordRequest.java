package com.hb0730.sys.system.model.request.user;

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
@Schema(description = "重置密码")
public class SysUserRestPasswordRequest implements Serializable {

    /**
     * 新密码
     */
    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String password;
    @Schema(description = "验证码KEY")
    @NotBlank(message = "验证码KEY不能为空")
    private String captchaKey;
    @Schema(description = "时间戳")
    @NotBlank(message = "时间戳不能为空")
    private String timestamp;
}
