package com.hb0730.security.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 登录成功 event
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/23
 */
@Getter
public class LoginSuccessEvent extends ApplicationEvent {
    private final String token;

    public LoginSuccessEvent(String token, Object source) {
        super(source);
        this.token = token;
    }
}
