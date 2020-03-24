package com.hb0730.boot.admin.conroller;

import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.model.LoginSuccess;
import com.hb0730.boot.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
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
    @GetMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        String accessToken = loginService.login(username, password);
        LoginSuccess success = new LoginSuccess();
        success.setAccessToken(accessToken);
        return ResponseResult.resultSuccess(success);
    }
}
