package com.hb0730.boot.admin.security.handler;

import com.hb0730.boot.admin.commons.enums.StatusEnum;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.ITokenService;
import com.hb0730.commons.json.utils.Jsons;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出成功
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
@RequiredArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);
    private final ITokenService tokenService;

    @SneakyThrows
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LOGGER.debug("logout success <<<<<");
        User loginUser = tokenService.getLoginUser(request);
        if (null != loginUser) {
            tokenService.delLoginUser(request);
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(loginUser.getUsername(), StatusEnum.SUCCESS, "登出成功"));
        }
        Result<String> result = R.success("注销成功");
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(Jsons.JSONS.objectToJson(result));
    }
}
