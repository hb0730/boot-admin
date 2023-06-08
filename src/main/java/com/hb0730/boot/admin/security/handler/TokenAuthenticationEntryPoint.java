package com.hb0730.boot.admin.security.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.base.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * token 授权异常
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/3
 */
@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
        int code = HttpStatus.UNAUTHORIZED.value();
        String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        JakartaServletUtil.write(response, JsonUtil.DEFAULT.toJson(R.NG(code, msg)), MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}
