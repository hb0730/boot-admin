package com.hb0730.boot.admin.listener.dict;

import com.hb0730.boot.admin.event.DictEvent;
import com.hb0730.boot.admin.project.system.dict.service.IDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * Dict监听
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
@RequiredArgsConstructor
public class DictEventListener implements ApplicationListener<DictEvent> {
    private final IDictService dictService;

    @Override
    @Async(value = "threadPoolTaskExecutor")
    public void onApplicationEvent(@Nonnull DictEvent event) {
        dictService.updateCache();
    }
}
