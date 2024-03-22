package com.hb0730.listener.auth;

import com.hb0730.biz.dto.sys.system.UserDto;
import com.hb0730.commons.JR;
import com.hb0730.security.event.LogoutSuccessEvent;
import com.hb0730.security.security.cache.UserProvider;
import com.hb0730.security.utils.JwtUtil;
import com.hb0730.sys.service.PermissionRpcService;
import com.hb0730.sys.service.RoleRpcService;
import com.hb0730.sys.service.UserRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/16
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LogoutListener {
    private final UserProvider userProvider;
    private final PermissionRpcService permissionRpcService;
    private final UserRpcService userRpcService;
    private final RoleRpcService roleRpcService;

    @EventListener
    @Async
    public void onApplicationListener(LogoutSuccessEvent event) {
        log.info("【退出登录】 LoginSuccessEvent 事件监听器执行...");
        String token = event.getToken();
        String username = JwtUtil.getUsername(token);
        JR<UserDto> jr = userRpcService.findByUsername(username);
        if (!jr.isSuccess()) {
            log.info("【退出登录】 用户不存在");
            return;
        }
        UserDto result = jr.getResult();
        clearUserCache(username);
        clearUserMenu(result.getId());
        clearUserRole(result.getId());
        log.info("【退出登录】 LoginSuccessEvent 事件监听器执行结束...");
    }

    private void clearUserCache(String username) {
        log.info("【退出登录】 清除用户缓存...");
        userProvider.removeUser(username);
        log.info("【退出登录】 清除用户缓存完成...");
    }

    private void clearUserMenu(Integer userId) {
        log.info("【退出登录】 清除用户菜单缓存...");
        permissionRpcService.clearUserPermissionCache(userId);
        log.info("【退出登录】 清除用户菜单缓存完成...");
    }

    private void clearUserRole(Integer userId) {
        log.info("【退出登录】 清除用户角色缓存...");
        roleRpcService.clearUserRoleCache(userId);
        log.info("【退出登录】 清除用户角色缓存完成...");
    }
}
