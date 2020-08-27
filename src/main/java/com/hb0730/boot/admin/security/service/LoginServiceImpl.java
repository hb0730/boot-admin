package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.exceptions.LoginException;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.ITokenService;
import com.hb0730.commons.spring.BeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 用户登录
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
@AllArgsConstructor
public class LoginServiceImpl {
    private final AuthenticationManager authenticationManager;
    private final ITokenService tokenService;
    @Nullable
    public LoginUser login(@NonNull String username, @NonNull String password) {
        Authentication authenticate = null;
        try {
            // see com.hb0730.boot.admin.security.service.UserDetailsServiceImpl#loadUserByUsername
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            // 认证错误 可能密码错误账号信息错误等
            throw new LoginException(ResponseStatusEnum.USE_LOGIN_ERROR, "用户名或者密码错误");
        }
        User user = (User) authenticate.getPrincipal();
        String accessToken = tokenService.createAccessToken(user);
        LoginUser loginUser = BeanUtils.transformFrom(user, LoginUser.class);
        assert loginUser != null;
        loginUser.setAccessToken(accessToken);
        return loginUser;
    }

}
