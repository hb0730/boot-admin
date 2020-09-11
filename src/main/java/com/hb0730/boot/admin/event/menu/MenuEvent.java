package com.hb0730.boot.admin.event.menu;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 菜单event
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class MenuEvent extends ApplicationEvent {
    @Getter
    private final Long userId;

    public MenuEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }
}
