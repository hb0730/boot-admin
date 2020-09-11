package com.hb0730.boot.admin.security.controller;

import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.security.model.LoginBody;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.service.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequiredArgsConstructor
@Validated
public class LoginController {
    private final LoginServiceImpl loginService;

    /**
     * 用户登录
     *
     * @param body 登录请求信息
     * @return 用户相关信息
     */
    @PostMapping("/login")
    public Result<Object> login(@Validated @RequestBody LoginBody body) {
        LoginUser login = loginService.login(body.getUsername(), body.getPassword());
        return R.success(login);
    }
}
