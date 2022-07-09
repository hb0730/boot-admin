package com.hb0730.boot.admin.task.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.project.system.quartz.mapper.IJobMapper;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobEntity;
import com.hb0730.boot.admin.task.domain.JobInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author bing_huang
 */
public abstract class AbstractAction implements IJobAction {
    private final JobHelper jobHelper;
    private final IJobMapper mapper;

    public AbstractAction(JobHelper helper, IJobMapper mapper) {
        this.jobHelper = helper;
        this.mapper = mapper;
    }

    public JobHelper getJobHelper() {
        return jobHelper;
    }

    protected List<JobInfo> getJobInfo(Collection<? extends Serializable> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        List<JobEntity> entities = mapper.selectBatchIds(ids);
        if (CollectionUtil.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        return BeanUtil.copyToList(entities, JobInfo.class);
    }
}
