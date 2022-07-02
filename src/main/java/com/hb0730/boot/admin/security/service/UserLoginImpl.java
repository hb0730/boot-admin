package com.hb0730.boot.admin.security.service;

import cn.hutool.core.bean.BeanUtil;
import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.commons.enums.StatusEnum;
import com.hb0730.boot.admin.exceptions.LoginException;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.security.model.Login;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.ITokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class UserLoginImpl implements IUserLogin {
    private final AuthenticationManager authenticationManager;
    private final ITokenService tokenService;

    @Override
    public Object login(Login login) {
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (Exception e) {
            if (e.getCause() instanceof UsernameNotFoundException) {
                AsyncManager.me().execute(
                    AsyncFactory.recordLoginLog(login.getUsername(),
                        StatusEnum.FAIL, "用户不存在")
                );
                throw new LoginException(ResponseStatusEnum.USER_NAME_NOT_FONT, "用户不存在");
            } else if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(
                    AsyncFactory.recordLoginLog(login.getUsername(),
                        StatusEnum.FAIL, "用户名或者密码错误")
                );
                throw new LoginException(ResponseStatusEnum.USER_PASSWORD_ERROR, "用户名或者密码错误");
            } else {
                AsyncManager.me().execute(
                    AsyncFactory.recordLoginLog(login.getUsername(),
                        StatusEnum.FAIL, e.getMessage())
                );
                throw new LoginException(ResponseStatusEnum.USE_LOGIN_ERROR, "登录异常,请稍后尝试", e);
            }
        }
        //开始创建创建token并且缓存用户信息
        User user = (User) authenticate.getPrincipal();
        String accessToken = tokenService.createAccessToken(user);
        //转换成响应对象
        LoginUser loginUser = BeanUtil.toBean(user, LoginUser.class);
        loginUser.setAccessToken(accessToken);

        AsyncManager.me().execute(
            AsyncFactory.recordLoginLog(login.getUsername(),
                StatusEnum.SUCCESS, "登录成功")
        );
        return loginUser;
    }
}
