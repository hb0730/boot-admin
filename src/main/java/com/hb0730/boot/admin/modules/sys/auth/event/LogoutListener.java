package com.hb0730.boot.admin.modules.sys.auth.event;

import com.hb0730.boot.admin.security.token.UserCacheProvider;
import lombok.RequiredArgsConstructor;
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
public class LogoutListener implements ApplicationListener<LogoutEvent> {
    private final UserCacheProvider userCacheProvider;

    @Override
    public void onApplicationEvent(LogoutEvent event) {
        String username = event.getUsername();
        userCacheProvider.clearUser(username);
    }
}
