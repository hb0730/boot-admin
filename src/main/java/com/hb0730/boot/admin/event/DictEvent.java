package com.hb0730.boot.admin.event;

import org.springframework.context.ApplicationEvent;

/**
 * 数据字典event
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class DictEvent extends ApplicationEvent {

    public DictEvent(Object source) {
        super(source);
    }
}
