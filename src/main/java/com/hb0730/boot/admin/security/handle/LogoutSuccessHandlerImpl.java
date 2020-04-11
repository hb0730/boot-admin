package com.hb0730.boot.admin.security.handle;

import com.hb0730.boot.admin.commons.constant.enums.SystemStatusEnum;
import com.hb0730.boot.admin.commons.constant.enums.TokenTypeEnum;
import com.hb0730.boot.admin.commons.constant.enums.ValueEnum;
import com.hb0730.boot.admin.commons.utils.MessageUtils;
import com.hb0730.boot.admin.commons.utils.ServletUtils;
import com.hb0730.boot.admin.commons.utils.json.GsonUtils;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.service.ITokenService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 自定义退出处理类 返回成功
 *
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    private final BootAdminProperties properties;
    private final TokenHandlers tokenHandlers;

    public LogoutSuccessHandlerImpl(BootAdminProperties properties, TokenHandlers tokenHandlers) {
        this.properties = properties;
        this.tokenHandlers = tokenHandlers;
    }

    /**
     * 退出处理
     *
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        TokenTypeEnum tokenTypeEnum = ValueEnum.valueToEnum(TokenTypeEnum.class, properties.getTokenType());
        ITokenService tokenService = tokenHandlers.getImpl(tokenTypeEnum);
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (Objects.nonNull(loginUser)) {
            tokenService.delLoginUser(request);
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(loginUser.getUsername(), SystemStatusEnum.SUCCESS.getValue(), MessageUtils.message("logout.success")));
        }
        // 记录用户退出日志
        Result<String> result = ResponseResult.resultSuccess("退出成功");
        ServletUtils.renderString(response, GsonUtils.gson2String(result));
    }
}
