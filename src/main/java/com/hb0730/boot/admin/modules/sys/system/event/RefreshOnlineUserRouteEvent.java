package com.hb0730.boot.admin.modules.sys.system.event;

import org.springframework.context.ApplicationEvent;

/**
 * 刷新在线用户路由&权限信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/6
 */
public class RefreshOnlineUserRouteEvent extends ApplicationEvent {
    public RefreshOnlineUserRouteEvent(Object source) {
        super(source);
    }
}
