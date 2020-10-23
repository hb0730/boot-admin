package com.hb0730.boot.admin.security.handler;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.commons.json.utils.Jsons;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败处理类 返回未授权,
 * 用来解决认证过的用户访问无权限资源时的异常
 *
 * @author bing_huang
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @SneakyThrows
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        e.printStackTrace();
        String resultParams = String.format("请求访问:%s ,没有访问权限!", request.getRequestURI());
        Result<String> result = R.result(ResponseStatusEnum.NO_PERMISSION, resultParams);
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(Jsons.JSONS.objectToJson(result));
    }
}
