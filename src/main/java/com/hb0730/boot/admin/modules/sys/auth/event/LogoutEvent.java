package com.hb0730.boot.admin.modules.sys.auth.event;

import org.springframework.context.ApplicationEvent;

/**
 * logout event
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/5
 */
public class LogoutEvent extends ApplicationEvent {
    /**
     * 访问token
     */
    private String token;
    /**
     * 用户名
     */
    private String username;

    public LogoutEvent(Object source, String token, String username) {
        super(source);
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
