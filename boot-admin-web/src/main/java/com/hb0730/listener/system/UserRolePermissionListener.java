package com.hb0730.listener.system;

import com.hb0730.biz.dto.sys.system.UserDto;
import com.hb0730.commons.JR;
import com.hb0730.security.security.cache.UserProvider;
import com.hb0730.sys.service.PermissionRpcService;
import com.hb0730.sys.service.RoleRpcService;
import com.hb0730.sys.service.UserRpcService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户角色权限监听
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserRolePermissionListener {
    private final UserProvider userProvider;
    private final UserRpcService userRpcService;
    private final PermissionRpcService permissionRpcService;
    private final RoleRpcService roleRpcService;


    /**
     * 菜单变更
     */
    @Getter
    public static class PermissionChangeEvent extends ApplicationEvent {
        private final Integer permissionId;

        public PermissionChangeEvent(Object source, Integer permissionId) {
            super(source);
            this.permissionId = permissionId;
        }
    }


    /**
     * 角色变更
     */
    @Getter
    public static class RolePermissionChangeEvent extends ApplicationEvent {
        private final Integer roleId;

        public RolePermissionChangeEvent(Object source, Integer roleId) {
            super(source);
            this.roleId = roleId;
        }
    }


    @EventListener
    @Async
    public void onPermissionChange(PermissionChangeEvent event) {
        log.info("【菜单变更】 PermissionChangeEvent 事件监听器执行...");
        Integer permissionId = event.getPermissionId();
        if (permissionId == null) {
            log.info("【菜单变更】 permissionId 为空");
            return;
        }
        JR<List<UserDto>> res = userRpcService.findByMenuId(permissionId);
        if (!res.isSuccess()) {
            log.info("【菜单变更】 获取用户失败");
            return;
        }
        List<UserDto> users = res.getResult();
        if (users.isEmpty()) {
            log.info("【菜单变更】 用户为空");
            return;
        }
        for (UserDto user : users) {
            userProvider.removeUser(user.getUsername());
            // 权限
            permissionRpcService.clearUserPermissionCache(user.getId());
            // 角色变更
            roleRpcService.clearUserRoleCache(user.getId());
        }

        // 角色变更
        log.info("【菜单变更】 PermissionChangeEvent 事件监听器执行结束...");
    }

    @EventListener
    @Async
    public void onRolePermissionChange(RolePermissionChangeEvent event) {
        log.info("【角色变更】 RolePermissionChangeEvent 事件监听器执行...");
        Integer roleId = event.getRoleId();
        if (roleId == null) {
            log.info("【角色变更】 roleId 为空");
            return;
        }
        JR<List<UserDto>> res = userRpcService.findByRoleId(roleId);
        if (!res.isSuccess()) {
            log.info("【角色变更】 获取用户失败");
            return;
        }
        List<UserDto> users = res.getResult();
        if (users.isEmpty()) {
            log.info("【角色变更】 用户为空");
            return;
        }
        for (UserDto user : users) {
            userProvider.removeUser(user.getUsername());
            // 权限
            permissionRpcService.clearUserPermissionCache(user.getId());
            // 角色变更
            roleRpcService.clearUserRoleCache(user.getId());
        }
        // 角色变更
        log.info("【角色变更】 RolePermissionChangeEvent 事件监听器执行结束...");
    }
}
