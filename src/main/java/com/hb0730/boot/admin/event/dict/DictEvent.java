package com.hb0730.boot.admin.event.dict;

import com.hb0730.boot.admin.model.enums.ActionEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 数据字典event
 *
 * @author bing_huang
 * @date 2020/06/28 14:37
 * @since V1.0
 */
public class DictEvent extends ApplicationEvent {
    @Getter
    @Setter
    private ActionEnum type;

    public DictEvent(Object source, ActionEnum type) {
        super(source);
        this.type = type;
    }
}
