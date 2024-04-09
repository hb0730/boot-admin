package com.hb0730.security.listener;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.rpc.basic.service.BasUserRpcService;
import com.hb0730.rpc.sys.system.service.UserRpcService;
import com.hb0730.security.domain.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessListener {
    private final UserRpcService userRpcService;
    private final BasUserRpcService basUserRpcService;

    @EventListener
    @Async
    public void onLoginSuccess(AuthenticationSuccessEvent event) {
        log.info("登录成功监听器 登录事件:{}", event);
        Authentication authentication = event.getAuthentication();
        if (null == authentication) {
            log.info("登录成功监听器 登录事件 认证信息为空>>>>");
            return;
        }
        log.info("登录成功监听器 登录事件 认证信息:{}", authentication);
        if (authentication.getPrincipal() instanceof UserInfoDto user) {
            log.info("登录成功监听器 登录事件 用户信息:{}", user);
            changeLastLoginTime(user.getUsername(), user.getSysCode());
        }

    }

    private void changeLastLoginTime(String username, String sysCode) {
        log.info("修改最后登录时间:{}", username);
        if (StrUtil.isNotBlank(sysCode)) {
            basUserRpcService.updateLastLoginTime(username);
        } else {
            userRpcService.changeLastLoginTime(username);
        }
        log.info("修改最后登录时间完成:{}", username);
    }

}