package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.exceptions.LoginException;
import com.hb0730.boot.admin.security.model.User;
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

    @Nullable
    public User login(@NonNull String username, @NonNull String password) {
        Authentication authenticate = null;
        try {
            // see com.hb0730.boot.admin.security.service.UserDetailsServiceImpl#loadUserByUsername
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            // 认证错误 可能密码错误账号信息错误等
            throw new LoginException(ResponseStatusEnum.USE_LOGIN_ERROR, "用户名或者密码错误");
        }
        Object principal = authenticate.getPrincipal();
        // 缓存 并返回
        return null;
    }

}
