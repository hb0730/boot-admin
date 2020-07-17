package com.hb0730.boot.admin.event.job;

import com.hb0730.boot.admin.model.enums.ActionEnum;
import org.springframework.context.ApplicationEvent;

import java.util.Collection;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class JobEvent extends ApplicationEvent {
    private ActionEnum type;
    private Collection<Long> id;

    public JobEvent(Object source, Collection<Long> id, ActionEnum type) {
        super(source);
        this.id = id;
        this.type = type;
    }

    public Collection<Long> getId() {
        return id;
    }

    public void setId(Collection<Long> id) {
        this.id = id;
    }

    public ActionEnum getType() {
        return type;
    }

    public void setType(ActionEnum type) {
        this.type = type;
    }
}
