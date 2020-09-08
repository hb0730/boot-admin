package com.hb0730.boot.admin.listener.job;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.enums.JobActionEnum;
import com.hb0730.boot.admin.event.job.JobEvent;
import com.hb0730.boot.admin.project.system.quartz.mapper.IJobMapper;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobEntity;
import com.hb0730.boot.admin.task.domain.JobInfo;
import com.hb0730.boot.admin.task.handler.JobHelper;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.spring.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
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

    @SneakyThrows
    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(@Nonnull JobEvent event) {
        JobActionEnum action = event.getAction();
        Collection<? extends Serializable> ids = event.getIds();
        switch (action) {
            case ADD_NEW:
                add(ids);
                break;
            case UPDATE:
                update(ids);
                break;
            case DELETE:
                delete(ids);
                break;
            case PAUSE:
                pause(ids);
                break;
            case RESUME:
                resume(ids);
                break;
            case RUN:
                run(ids);
                break;
            default:
                break;
        }
    }

    @PostConstruct
    public void init() throws SchedulerException {
        List<JobEntity> jobEntities = mapper.selectList(Wrappers.lambdaQuery());
        if (CollectionUtils.isEmpty(jobEntities)) {
            return;
        }
        List<JobInfo> jobInfos = BeanUtils.transformFromInBatch(jobEntities, JobInfo.class);
        for (JobInfo jobInfo : jobInfos) {
            jobHelper.addJob(jobInfo);
        }
    }

    private void add(Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            jobHelper.addJob(jobInfo);
        }
    }

    private void update(Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            // 先移除
            if (jobHelper.checkExists(jobInfo.getId(), jobInfo.getGroup())) {
                jobHelper.deleteJob(jobInfo.getId(), jobInfo.getGroup());
            }
            jobHelper.addJob(jobInfo);

        }
    }

    private void delete(Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            jobHelper.deleteJob(jobInfo.getId(), jobInfo.getGroup());
        }
    }

    private void pause(Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            jobHelper.pauseJob(jobInfo.getId(), jobInfo.getGroup());
        }
    }

    private void resume(Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            jobHelper.resumeJob(jobInfo.getId(), jobInfo.getGroup());
        }
    }

    private void run(Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            jobHelper.runJob(jobInfo.getId(), jobInfo.getGroup(), jobInfo);
        }
    }

    private List<JobInfo> getJobInfo(Collection<? extends Serializable> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        List<JobEntity> entities = mapper.selectBatchIds(ids);
        if (CollectionUtils.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        return BeanUtils.transformFromInBatch(entities, JobInfo.class);
    }
}
