package com.hb0730.boot.admin.project.monitor.job.handler;

import com.google.common.collect.Lists;
import com.hb0730.boot.admin.model.enums.ActionEnum;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.project.monitor.job.mapper.ISystemJobMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * job 实现类集合
 *
 * @author bing_huang
 * @date 2020/06/29 8:04
 * @since V2.0
 */
@Component
public class JobHandlers extends AbstractJob {
    private final Collection<IJob> jobs = Lists.newArrayList();
    private IJob current;

    public JobHandlers(ISystemJobMapper mapper, ApplicationContext context, BootAdminProperties properties) {
        super(mapper);
        addJobImpl(context.getBeansOfType(IJob.class).values());
        for (IJob job : jobs) {
            if (job.supportType(properties.getJobType())) {
                this.current = job;
            }
        }
    }

    @Override
    public void init() {
        current.init();
    }

    @Override
    public void updateJob(ActionEnum type, Collection<Long> ids) {
        current.updateJob(type, ids);
    }

    @SuppressWarnings("UnusedReturnValue")
    public JobHandlers addJobImpl(Collection<IJob> jobs) {
        if (!CollectionUtils.isEmpty(jobs)) {
            this.jobs.addAll(jobs);
            this.jobs.remove(this);
        }
        return this;
    }
}
