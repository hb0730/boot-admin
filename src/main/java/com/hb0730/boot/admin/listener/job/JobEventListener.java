package com.hb0730.boot.admin.listener.job;

import com.hb0730.boot.admin.event.job.JobEvent;
import com.hb0730.boot.admin.project.monitor.job.handler.JobHandler;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class JobEventListener {

    private JobHandler jobHandler;

    public JobEventListener(JobHandler jobHandler) {
        this.jobHandler = jobHandler;
    }

    @EventListener
    @Async
    public void onApplicationEvent(JobEvent event) {
        jobHandler.updateJob(event.getType(), event.getId());
    }
}
