package com.hb0730.boot.admin.service;

import com.hb0730.boot.admin.commons.web.security.model.LoginUser;
import com.hb0730.boot.admin.commons.web.utils.SecurityUtils;
import com.hb0730.boot.admin.security.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    public String login(String username, String password) {
        // 删除缓存

        // 用户验证
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createAccessToken(loginUser);
    }
}
