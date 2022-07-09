package com.hb0730.boot.admin.listener.job;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.boot.admin.event.job.JobEvent;
import com.hb0730.boot.admin.project.system.quartz.mapper.IJobMapper;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobEntity;
import com.hb0730.boot.admin.task.domain.JobInfo;
import com.hb0730.boot.admin.task.handler.IJobAction;
import com.hb0730.boot.admin.task.handler.JobActionFactory;
import com.hb0730.boot.admin.task.handler.JobHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.util.List;

/**
 * job listener
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
@RequiredArgsConstructor
public class JobEventListener implements ApplicationListener<JobEvent> {
    private final IJobMapper mapper;
    private final JobHelper jobHelper;
    private final JobActionFactory factory;

    @SneakyThrows
    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(@Nonnull JobEvent event) {
        IJobAction jobAction = factory.getJobAction(event.getAction());
        if (null != jobAction) {
            jobAction.run(event.getIds());
        }
    }

    @PostConstruct
    public void init() throws SchedulerException {
        List<JobEntity> jobEntities = mapper.selectList(Wrappers.lambdaQuery());
        if (CollectionUtil.isEmpty(jobEntities)) {
            return;
        }
        List<JobInfo> jobInfos = BeanUtil.copyToList(jobEntities, JobInfo.class);
        for (JobInfo jobInfo : jobInfos) {
            jobHelper.addJob(jobInfo);
        }
    }
}
