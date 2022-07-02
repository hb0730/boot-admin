package com.hb0730.boot.admin.security.controller;

import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.security.model.Login;
import com.hb0730.boot.admin.security.service.IUserLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final IUserLogin userLogin;

    @PostMapping("/login")
    public Result<Object> login(@Validated @RequestBody Login login) {
        Object result = userLogin.login(login);
        return R.success(result);
    }
}
