package com.hb0730.boot.admin.security.conroller;

import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.security.model.LoginSuccess;
import com.hb0730.boot.admin.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 登录端口
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * <p>
     * 认证
     * </p>
     */
    @PostMapping("/login")
    public Result login(String username, String password) {
        LoginSuccess success = loginService.login(username, password);
        return ResponseResult.resultSuccess(success);
    }
}
