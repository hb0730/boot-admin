package com.hb0730.boot.admin.modules.sys.auth.controller;

import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.base.util.AesEncryptUtil;
import com.hb0730.boot.admin.modules.sys.auth.model.LoginRequest;
import com.hb0730.boot.admin.security.model.UserInfo;
import com.hb0730.boot.admin.security.token.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 授权、根据token获取用户详细信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/12
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "系统: 授权接口")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenRedisCacheProvider;

    @Operation(description = "登录授权")
    @PostMapping("/login")
    public R<Object> login(HttpServletRequest request, @RequestBody @Valid LoginRequest login) throws Exception {
        String password = login.getPassword();
        // 解密
        password = AesEncryptUtil.desEncrypt(password);
        //
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), password);
        Authentication authenticate = null;
        try {
            // 会调用UserDetailsService#loadUserByUsername(String username)
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {

        }
        assert authenticate != null;
        UserInfo userInfo = (UserInfo) authenticate.getPrincipal();
        String jwtToken = jwtTokenRedisCacheProvider.createToken(userInfo, request);
        Map<String, Object> res = Map.of("token", jwtToken, "user", userInfo);
        return R.OK(res);
    }

    @Operation(description = "用户信息")
    @GetMapping("/info")
    public R<Object> getUserInfo() {
        return null;
    }

    @Operation(description = "退出登录")
    @DeleteMapping("/logout")
    public R<Object> logout(HttpServletRequest request) {
        return null;
    }
}
