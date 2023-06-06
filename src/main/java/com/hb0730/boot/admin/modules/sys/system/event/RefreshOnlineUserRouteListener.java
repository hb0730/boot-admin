package com.hb0730.boot.admin.modules.sys.system.event;

import com.hb0730.boot.admin.config.cache.BootAdminProperties;
import com.hb0730.boot.admin.modules.sys.system.cache.RouteCache;
import com.hb0730.boot.admin.security.model.UserInfo;
import com.hb0730.boot.admin.security.token.UserCacheProvider;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/6
 */
@Component
@RequiredArgsConstructor
@Slf4j
//@Async
public class RefreshOnlineUserRouteListener implements ApplicationListener<RefreshOnlineUserRouteEvent> {
    private final UserCacheProvider userCacheProvider;
    private final BootAdminProperties properties;
    private final RouteCache routeCache;

    @Override
    public void onApplicationEvent(@Nonnull RefreshOnlineUserRouteEvent event) {
        log.info("【刷新在线用户路由事件】刷新在线用户路由信息>>>>>>>>>>>开始");
        Boolean refreshRoutes = properties.getRefreshRoutes();
        if (!refreshRoutes) {
            log.warn("【刷新在线用户路由事件】刷新在线用户路由信息>>>>>>>>>>>未开启");
            return;
        }
        Optional<List<UserInfo>> users = userCacheProvider.getCacheUsers();
        if (users.isPresent()) {
            List<UserInfo> userInfoList = users.get();
            for (UserInfo userInfo : userInfoList) {
                log.info("【刷新在线用户路由事件】开始清理登陆用户缓存信息: {}", userInfo.getUsername());
                // TODO 是否刷新用户权限信息
                userCacheProvider.clearUser(userInfo.getUsername());
                log.info("【刷新在线用户路由事件】开始清理登陆用户路由信息: {}", userInfo.getUsername());
                routeCache.removeCache(userInfo.getUserid());
            }
        }
        log.info("【刷新在线用户路由事件】刷新在线用户路由信息>>>>>>>>>>>结束");
    }
}
