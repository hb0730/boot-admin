package com.hb0730.spring.security.core.handler;

import com.hb0730.base.R;
import com.hb0730.base.utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * 未认证处理器
 * <p>
 * 过滤器执行完还未设置用户上下文则会进入此处理器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@Slf4j
@ControllerAdvice
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    @ExceptionHandler(AuthenticationException.class)
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.debug("AuthenticationEntryPointHandler-commence {}", request.getRequestURI(), e);
        R<String> jr = R.NG(401, "当前认证信息已失效, 请重新登录");
        String res = JsonUtil.DEFAULT.toJson(jr);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(res);
    }
}
