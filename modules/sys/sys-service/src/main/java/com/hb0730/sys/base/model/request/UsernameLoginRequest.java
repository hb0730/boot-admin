package com.hb0730.sys.base.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@Data
@Schema(description = "登录请求")
public class UsernameLoginRequest implements Serializable {
    @NotBlank(message = "验证码KEY不能为空")
    @Schema(description = "用户名")
    private String username;
    @NotBlank(message = "验证码KEY不能为空")
    @Schema(description = "密码")
    private String password;
    /**
     * 验证码
     */
    @Schema(description = "验证码KEY")
    @Size(min = 16, max = 256)
    @NotBlank(message = "验证码KEY不能为空")
    private String captchaKey;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    private String timestamp;


    /**
     * 转换为对象
     *
     * @param map map
     * @return 对象
     */
    public static UsernameLoginRequest of(Map<String, String> map) {
        UsernameLoginRequest request = new UsernameLoginRequest();
        request.setUsername(map.get("username"));
        request.setPassword(map.get("password"));
        request.setCaptchaKey(map.get("captchaKey"));
        request.setTimestamp(map.get("timestamp"));
        return request;
    }
}
