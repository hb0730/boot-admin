package com.hb0730.boot.admin.listener.dict;

import com.hb0730.boot.admin.event.dict.DictEvent;
import com.hb0730.boot.admin.project.system.dict.service.ISystemDictService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 数据字典 listener
 *
 * @author bing_huang
 * @date 2020/06/28 14:38
 * @since V1.0
 */
@Component
@AllArgsConstructor
public class DictEventListener implements ApplicationListener<DictEvent> {
    private final ISystemDictService systemDictService;

    @Override
    @Async(value = "threadPoolTaskExecutor")
    public void onApplicationEvent(DictEvent event) {
        systemDictService.cache();
    }
}
