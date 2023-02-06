package com.hb0730.boot.admin.modules.sys.auth.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.base.util.JwtUtil;
import com.hb0730.boot.admin.base.util.RsaUtil;
import com.hb0730.boot.admin.modules.sys.auth.event.LogoutEvent;
import com.hb0730.boot.admin.modules.sys.auth.model.LoginRequest;
import com.hb0730.boot.admin.modules.sys.auth.model.LoginResponse;
import com.hb0730.boot.admin.security.config.LoginProperties;
import com.hb0730.boot.admin.security.model.UserInfo;
import com.hb0730.boot.admin.security.token.TokenProvider;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final LoginProperties loginProperties;
    private final ApplicationContext applicationContext;

    @Operation(summary = "登录授权")
    @PostMapping("/login")
    public R<LoginResponse> login(HttpServletRequest request, @RequestBody @Valid LoginRequest login) throws Exception {
        String password = login.getPassword();
        // 解密
        password = RsaUtil.decryptByPrivateKey(login.getPassword(), loginProperties.getRsaPrivateKey());
        //
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), password);
        Authentication authenticate = null;
        try {
            // 会调用UserDetailsService#loadUserByUsername(String username)
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            if (e instanceof UsernameNotFoundException) {
                return R.NG("用户名不存在");
            } else {
                return R.NG(e.getMessage());
            }
        }
        assert authenticate != null;
        UserInfo userInfo = (UserInfo) authenticate.getPrincipal();
        String jwtToken = jwtTokenRedisCacheProvider.createToken(userInfo, request);
        LoginResponse response = BeanUtil.toBean(userInfo, LoginResponse.class);
        response.setToken(jwtToken);
        return R.OK(response);
    }

    @Operation(summary = "用户信息")
    @GetMapping("/info")
    public R<Object> getUserInfo() {
        return null;
    }

    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        String _jwtToken = JwtUtil.getTokenByRequest(request);
        UserInfo userInfo = SecurityUtil.getCurrentUser();
        applicationContext.publishEvent(new LogoutEvent(this, _jwtToken, userInfo.getUsername(), userInfo.getUserid()));
        jwtTokenRedisCacheProvider.removeToken(_jwtToken);
        return R.OK("成功");
    }
}
