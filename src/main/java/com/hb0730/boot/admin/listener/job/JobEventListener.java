package com.hb0730.boot.admin.listener.job;

import com.hb0730.boot.admin.event.job.JobEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * job listener
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
public class JobEventListener implements ApplicationListener<JobEvent> {
    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(@Nonnull JobEvent event) {

    }
}
