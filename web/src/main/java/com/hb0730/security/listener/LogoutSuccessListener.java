package com.hb0730.security.listener;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.rpc.basic.service.BasPermissionRpcService;
import com.hb0730.rpc.sys.system.service.PermissionRpcService;
import com.hb0730.security.domain.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LogoutSuccessListener {
    private final PermissionRpcService permissionRpcService;
    private final BasPermissionRpcService basPermissionRpcService;

    @EventListener
    @Async
    public void onLogoutSuccess(LogoutSuccessEvent event) {
        log.info("退出监听器 退出事件:{}", event);
        Authentication authentication = event.getAuthentication();
        if (null == authentication) {
            log.info("退出监听器 退出事件 认证信息为空>>>>");
            return;
        }
        log.info("退出监听器 退出事件 认证信息:{}", authentication);
        if (authentication.getPrincipal() instanceof UserInfoDto user) {
            log.info("退出监听器 退出事件 用户信息:{}", user);
            clearUserRoutes(user.getId(), user.getSysCode());
        }

    }

    /**
     * 清除用户路由信息
     *
     * @param userId 用户ID
     */
    private void clearUserRoutes(String userId, String sysCode) {
        log.info("清除用户路由信息:{}", userId);
        if (StrUtil.isNotBlank(sysCode)) {
            basPermissionRpcService.clearUserRoutesCache(userId, sysCode);
        } else {
            permissionRpcService.clearUserRouteCache(Long.parseLong(userId));
        }
        log.info("清除用户路由信息完成:{}", userId);
    }

    private String getCacheKey(String username, String sysCode) {
        if (StrUtil.isNotBlank(sysCode)) {
            return sysCode + ":" + username;
        }
        return username;
    }
}