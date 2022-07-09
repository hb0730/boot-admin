package com.hb0730.boot.admin.listener.option;

import com.hb0730.boot.admin.event.option.OptionUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * option listener
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
@RequiredArgsConstructor
public class OptionListener implements ApplicationListener<OptionUpdatedEvent> {
    @Override
    public void onApplicationEvent(@Nonnull OptionUpdatedEvent event) {
    }
}
