package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.commons.enums.StatusEnum;
import com.hb0730.boot.admin.exceptions.LoginException;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.ITokenService;
import com.hb0730.commons.spring.BeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
            if (e.getCause() instanceof com.hb0730.boot.admin.exceptions.UsernameNotFoundException) {
                AsyncManager.me().execute(
                        AsyncFactory.recordLoginLog(username,
                                StatusEnum.FAIL, "用户不存在")
                );
                throw new LoginException(ResponseStatusEnum.USER_NAME_NOT_FONT, "用户不存在");
            } else if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(
                        AsyncFactory.recordLoginLog(username,
                                StatusEnum.FAIL, "用户名或者密码错误")
                );
                throw new LoginException(ResponseStatusEnum.USER_PASSWORD_ERROR, "用户名或者密码错误");
            } else {
                AsyncManager.me().execute(
                        AsyncFactory.recordLoginLog(username,
                                StatusEnum.FAIL, e.getMessage())
                );
                throw new LoginException(ResponseStatusEnum.USE_LOGIN_ERROR, "登录异常,请稍后尝试", e);
            }
        }
        User user = (User) authenticate.getPrincipal();
        String accessToken = tokenService.createAccessToken(user);
        LoginUser loginUser = BeanUtils.transformFrom(user, LoginUser.class);
        assert loginUser != null;
        loginUser.setAccessToken(accessToken);
        AsyncManager.me().execute(
                AsyncFactory.recordLoginLog(username,
                        StatusEnum.SUCCESS, "登录成功")
        );
        return loginUser;
    }

}
