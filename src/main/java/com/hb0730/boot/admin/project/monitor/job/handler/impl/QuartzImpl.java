package com.hb0730.boot.admin.project.monitor.job.handler.impl;

import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.constant.ActionEnum;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.constant.enums.JobTypeEnum;
import com.hb0730.boot.admin.project.monitor.job.handler.AbstractJob;
import com.hb0730.boot.admin.project.monitor.job.mapper.ISystemJobMapper;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.hb0730.boot.admin.task.quartz.Job;
import com.hb0730.boot.admin.task.quartz.JobModel;
import com.hb0730.boot.admin.task.quartz.QuartzUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hb0730.boot.admin.task.quartz.QuartzConstant.DEFAULT_GROUP;

/**
 * quartz 任务调度
 *
 * @author bing_huang
 * @date 2020/06/29 7:59
 * @since V2.0
 */
@Component
public class QuartzImpl extends AbstractJob {
    private static final Logger lOGGER = LoggerFactory.getLogger(QuartzImpl.class);
    @Autowired
    private Scheduler scheduler;

    public QuartzImpl(ISystemJobMapper mapper) {
        super(mapper);
    }

    @Override
    public void init() {
        List<SystemJobEntity> enabledJob = getEnabledJob(null, ActionEnum.SELECT);
        try {
            List<Long> allKeys = QuartzUtils.queryAllJobKeyName(scheduler);
            List<Long> runKeys = QuartzUtils.queryRunJobKeyName(scheduler);
            List<Long> keys = Lists.newArrayList();
            keys.addAll(allKeys);
            keys.addAll(runKeys);
            List<Long> ids = enabledJob.parallelStream().filter(e -> Objects.nonNull(e.getId())).map(SystemJobEntity::getId).collect(Collectors.toList());
            keys.removeAll(ids);
            removeJob(keys);
        } catch (org.quartz.SchedulerException e) {
            e.printStackTrace();
            lOGGER.error("初始化异常>>>>>>>", e);
        }
        addJob(enabledJob);
    }

    @Override
    public void updateJob(ActionEnum type, Collection<Long> ids) {
        List<SystemJobEntity> job = getEnabledJob(ids, type);
        if (CollectionUtils.isEmpty(job)) {
            return;
        }

        switch (type) {
            case SAVE:
                addJob(job);
                break;
            case UPDATE:
                Set<Long> notEnabledIds = job.stream().filter((entity) -> entity.getIsEnabled().equals(SystemConstants.UN_ENABLED)).map(SystemJobEntity::getId).collect(Collectors.toSet());
                Set<Long> enabledIds = job.stream().filter((entity) -> entity.getIsEnabled().equals(SystemConstants.ENABLED)).map(SystemJobEntity::getId).collect(Collectors.toSet());
                removeJob(notEnabledIds);

                if (!CollectionUtils.isEmpty(enabledIds)) {
                    List<SystemJobEntity> enabledJob = getEnabledJob(enabledIds, ActionEnum.SELECT);
                    addJob(enabledJob);
                }
                break;
            case DELETE:
                removeJob(ids);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean supportType(@NonNull JobTypeEnum type) {
        return JobTypeEnum.QUARTZ.equals(type);
    }

    private void addJob(List<SystemJobEntity> entities) {
        List<JobModel> models = convertType(entities);
        for (JobModel model : models) {
            try {
                QuartzUtils.addJob(Job.class, scheduler, model);
            } catch (org.quartz.SchedulerException e) {
                e.printStackTrace();
                lOGGER.error("新增job失败>>>>>>>>{}", e.getMessage());
            }
        }
    }

    private void removeJob(Collection<Long> ids) {
        for (Long id : ids) {
            try {
                QuartzUtils.deleteJob(scheduler, id.toString(), DEFAULT_GROUP);
            } catch (SchedulerException e) {
                e.printStackTrace();
                lOGGER.error("删除job失败>>>>>>>>>>{}", e.getMessage());
            }
        }
    }

    private List<JobModel> convertType(List<SystemJobEntity> entities) {

        List<JobModel> models = Lists.newArrayList();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        for (SystemJobEntity entity : entities) {
            JobModel model = new JobModel();
            model.setCron(entity.getCron());
            model.setJobId(entity.getId().toString());
            model.setJobName(entity.getId().toString());
            model.setBeanName(entity.getBeanName());
            model.setMethodName(entity.getMethodName());
            model.setParams(entity.getParams());
            models.add(model);
        }
        return models;

    }

}
