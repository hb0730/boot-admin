package com.hb0730.boot.admin.modules.sys.auth.event;

import com.hb0730.boot.admin.modules.sys.system.cache.RouteCache;
import com.hb0730.boot.admin.security.token.UserCacheProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 登出事件
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/5
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class LogoutListener implements ApplicationListener<LogoutEvent> {
    private final UserCacheProvider userCacheProvider;
    private final RouteCache routeCache;

    @Override
    public void onApplicationEvent(LogoutEvent event) {
        log.info("【用户注销事件】用户注销清理缓存信息>>>>>>>>>>>开始");
        String username = event.getUsername();
        userCacheProvider.clearUser(username);
        log.info("【用户注销事件】成功清理缓存登录用户信息");
        routeCache.removeCache(event.getUserid());
        log.info("【用户注销事件】成功清理缓存用户路由信息");
    }
}
