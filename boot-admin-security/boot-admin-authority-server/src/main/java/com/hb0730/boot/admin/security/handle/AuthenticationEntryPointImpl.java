package com.hb0730.boot.admin.security.handle;

import com.hb0730.boot.admin.commons.ServletUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        HttpStatus code = HttpStatus.UNAUTHORIZED;
        String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());
        e.printStackTrace();
        ServletUtils.renderString(response, msg);
    }
}
