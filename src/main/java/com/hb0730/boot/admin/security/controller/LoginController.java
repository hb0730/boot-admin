package com.hb0730.boot.admin.security.controller;

import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.security.service.LoginServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {
    private LoginServiceImpl loginService;
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 用户相关信息
     */
    @PostMapping("/login")
    public Result<Object> login(String username, String password) {

        return null;
    }
}
