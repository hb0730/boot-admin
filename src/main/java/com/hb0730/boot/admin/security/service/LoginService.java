package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.commons.constant.SystemStatusEnum;
import com.hb0730.boot.admin.commons.utils.MessageUtils;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.exception.UserPasswordNotMatchException;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.security.model.LoginSuccess;
import com.hb0730.boot.admin.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class LoginService {
    @Autowired
    private TokenServiceImpl tokenService;

    @Resource
    private AuthenticationManager authenticationManager;


    /**
     * <p>
     * 用户登录
     * </p>
     *
     * @param username 用户账号
     * @param password 用户密码
     * @return token令牌
     */
    public LoginSuccess login(String username, String password) {
        // 删除缓存

        // 用户验证
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, SystemStatusEnum.FAIL.ordinal(), MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, SystemStatusEnum.FAIL.ordinal(), e.getMessage()));
                throw new BaseException(e.getMessage());
            }

        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        String accessToken = tokenService.createAccessToken(loginUser);
        LoginSuccess success = new LoginSuccess();
        success.setAccessToken(accessToken);
        success.setLoginUser(loginUser);
        AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, SystemStatusEnum.SUCCESS.ordinal(), MessageUtils.message("login.success")));
        return success;
    }
}
