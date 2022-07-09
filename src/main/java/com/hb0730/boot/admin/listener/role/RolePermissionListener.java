package com.hb0730.boot.admin.listener.role;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.boot.admin.event.menu.MenuEvent;
import com.hb0730.boot.admin.event.role.RolePermissionEvent;
import com.hb0730.boot.admin.project.system.user.model.dto.UserDTO;
import com.hb0730.boot.admin.project.system.user.service.IUserInfoService;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.ITokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 角色权限监听,用于刷新用户权限
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
@RequiredArgsConstructor
public class RolePermissionListener implements ApplicationListener<RolePermissionEvent> {
    private final ITokenService tokenService;
    private final IUserInfoService userInfoService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(@Nonnull RolePermissionEvent event) {
        Long roleId = event.getRoleId();
        Set<UserDetails> onlineUser = getOnlineUserByRoleId(roleId);
        if (CollectionUtil.isEmpty(onlineUser)) {
            return;
        }
        Set<String> usernameList = onlineUser.stream().map(UserDetails::getUsername).collect(Collectors.toSet());
        Map<String, UserDetails> onlineUserMap = onlineUser.stream().collect(Collectors.toMap(UserDetails::getUsername, Function.identity()));
        //用户
        for (String username : usernameList) {
            UserDTO userDTO = userInfoService.loadUserByUsername(username);
            UserDetails details = onlineUserMap.get(username);
            BeanUtil.copyProperties(userDTO, details);
            //刷新token
            tokenService.refreshAccessToken((User) details);
            eventPublisher.publishEvent(new MenuEvent(this, userDTO.getId()));
        }
    }

    /**
     * 获取已分配当前角色的用户
     *
     * @param roleId 角色id
     * @return 已分配当前角色的在线用户
     */
    public Set<UserDetails> getOnlineUserByRoleId(Long roleId) {
        if (Objects.isNull(roleId)) {
            return null;
        }
        Map<String, UserDetails> onlineUser = tokenService.getOnline();
        Collection<UserDetails> values = onlineUser.values();
        if (CollectionUtil.isEmpty(values)) {
            return null;
        }
        return values
            .stream()
            .filter(value ->
                !CollectionUtil.isEmpty(((User) value).getRoleIds()) &&
                    ((User) value).getRoleIds().contains(roleId)
            )
            .collect(Collectors.toSet());
    }
}
