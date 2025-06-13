package com.hb0730.sys.base.controller;

import com.hb0730.base.PairEnum;
import com.hb0730.base.R;
import com.hb0730.operator.log.core.annotation.OperatorLog;
import com.hb0730.security.SecurityUtils;
import com.hb0730.sys.base.define.operator.AuthenticationOperatorType;
import com.hb0730.sys.base.enums.LoginGrantEnums;
import com.hb0730.sys.base.granter.TokenGranterBuilder;
import com.hb0730.sys.base.model.dto.LoginInfo;
import com.hb0730.sys.base.model.request.UsernameLoginRequest;
import com.hb0730.sys.base.service.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * 用户认证
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "认证服务")
@Slf4j
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final TokenGranterBuilder tokenGranterBuilder;
    private final AuthUserService authUserService;

    /**
     * 用户登录
     */
    @PermitAll
    @PostMapping("/login/{type}")
    @Operation(summary = "用户登录,根据不同类型登录，password:用户名密码登录，mobile:手机号登录,social:社交登录,email:邮箱登录")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "登录成功,返回token", responseCode = "200"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "登录失败", responseCode = "400")
    })
    @OperatorLog(value = AuthenticationOperatorType.LOGIN)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "登录请求",
            required = true,
            content = {
                    @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json;charset=UTF-8",
                            schemaProperties = {
                                    @io.swagger.v3.oas.annotations.media.SchemaProperty(
                                            name = "password",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                                    implementation = UsernameLoginRequest.class
                                            )
                                    )
                            },
                            examples = {
                                    @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "password",
                                            description = "用户名密码登录",
                                            value = "{\"username\":\"admin\",\"password\":\"123456\",\"captchaKey\":\"123456\",\"timestamp\":\"123456\"}"
                                    ),
                                    @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "code",
                                            description = "验证码登陆",
                                            value = "{\"username\":\"123456\",\"captchaKey\":\"123456\"," +
                                                    "\"timestamp\":\"123456\"}"
                                    ),
                                    @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "social",
                                            description = "社交登录",
                                            value = "{\"socialSource\":\"github\",\"socialCode\":\"123456\"," +
                                                    "\"socialState\":\"123456\"}"
                                    )
                            }
                    )
            }
    )
    public R<String> login(@PathVariable String type,
                           @RequestBody Map<String, String> body) {
        Optional<LoginGrantEnums> grantEnums = PairEnum.of(LoginGrantEnums.class, type);
        if (grantEnums.isEmpty()) {
            return R.NG("暂不支持该登录方式");
        }
        return switch (grantEnums.get()) {
            case PASSWORD -> {
                UsernameLoginRequest dto = UsernameLoginRequest.of(body);
                yield loginByUsername(dto);
            }
            default -> R.NG("暂不支持该登录方式");
        };
    }

    /**
     * 用户登出
     */
    @PermitAll
    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    @OperatorLog(AuthenticationOperatorType.LOGOUT)
    public R<String> logout(HttpServletRequest request) {
        // 获取登录 token
        Optional<String> tokenOptional = SecurityUtils.obtainAuthorization(request);
        tokenOptional.ifPresent(tokenGranterBuilder.defaultGranter()::logout);
        return R.OK("登出成功");
    }

    /**
     * 用户登录
     */
    private R<String> loginByUsername(UsernameLoginRequest request) {
        LoginInfo loginInfo = new LoginInfo();
        // 解密
//        String iv = request.getCaptchaKey();
//        String key = SecureUtil.sha256(request.getCaptchaKey() + request.getTimestamp());
//        byte[] _key = HexUtil.decodeHex(key);
//        byte[] _iv = iv.getBytes();
//        String password = AesCryptoUtil.decrypt(request.getPassword(), AesCryptoUtil.mode, _key, _iv);
        // 解密密码
        loginInfo.setPassword(request.getPassword());
        loginInfo.setUsername(request.getUsername());
        // 登录
        return tokenGranterBuilder.getGranter(LoginGrantEnums.PASSWORD).login(loginInfo);
    }


}
