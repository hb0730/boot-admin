package com.hb0730.security.handler;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.hb0730.base.R;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.base.utils.StrUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * token 认证失败/授权异常
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Component
@Slf4j
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("token认证失败:{}", authException.getMessage());
        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
        int code = HttpStatus.UNAUTHORIZED.value();
        String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        JakartaServletUtil.write(response, JsonUtil.DEFAULT.toJson(R.NG(code, msg)), MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}