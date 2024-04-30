package com.hb0730.security.handler;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.hb0730.base.R;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.security.cache.TokenProvider;
import com.hb0730.security.cache.UserProvider;
import com.hb0730.security.domain.dto.UserInfoDto;
import com.hb0730.security.utils.JwtUtil;
import jakarta.annotation.Nullable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    private final TokenProvider tokenProvider;
    private final UserProvider userProvider;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功 处理器>>>>>");
        String token = JwtUtil.getToken(request);
        if (null != token) {
            log.info("退出成功 处理器>>>>> token:{}", token);
            tokenProvider.removeToken(token);
        }
        removeUserProvider(authentication);

        String message = "退出成功";
        JakartaServletUtil.write(
                response,
                JsonUtil.DEFAULT.toJson(R.OK(message)),
                MediaType.APPLICATION_JSON_UTF8_VALUE
        );
    }

    private void removeUserProvider(@Nullable Authentication authentication) {
        if (null != authentication && authentication.getPrincipal() instanceof UserInfoDto user) {
            log.info("退出监听器 退出事件 用户信息:{}", user);
            userProvider.removeUser(getCacheKey(user.getUsername(), user.getSysCode()));
        }
    }

    private String getCacheKey(String username, String sysCode) {
        if (StrUtil.isNotBlank(sysCode)) {
            return sysCode + ":" + username;
        }
        return username;
    }
}