package com.hb0730.boot.admin.security.handler;

import com.hb0730.boot.admin.domain.result.CodeStatusEnum;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.commons.json.gson.GsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败后处理
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        String resultParams = String.format("请求访问:%s ,认证失败,无法访问系统资源", request.getRequestURI());
        Result<String> result = Results.result(CodeStatusEnum.UNAUTHORIZED, resultParams);
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(GsonUtils.objectToJson(result));
    }

}
