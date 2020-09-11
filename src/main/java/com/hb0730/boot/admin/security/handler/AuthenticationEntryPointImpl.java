package com.hb0730.boot.admin.security.handler;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.commons.json.utils.Jsons;
import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 认证失败后处理
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 9223225789105991714L;

    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        e.printStackTrace();
        String resultParams = String.format("请求访问:%s ,认证失败,无法访问系统资源", request.getRequestURI());
        Result<String> result = R.result(ResponseStatusEnum.UNAUTHORIZED, resultParams);
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(Jsons.Utils.instance().objectToJson(result));
    }

}
