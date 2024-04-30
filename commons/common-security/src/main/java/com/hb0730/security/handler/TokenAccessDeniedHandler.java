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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Component
@Slf4j
public class TokenAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法发送403 Forbidden响应
        int code = HttpStatus.FORBIDDEN.value();
        String msg = StrUtil.format("请求访问:{} ,没有访问权限!", request.getRequestURI());
        JakartaServletUtil.write(response, JsonUtil.DEFAULT.toJson(R.NG(code, msg)), MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}