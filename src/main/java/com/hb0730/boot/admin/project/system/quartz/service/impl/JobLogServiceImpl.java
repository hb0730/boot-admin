package com.hb0730.boot.admin.project.system.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.quartz.mapper.IJobLogMapper;
import com.hb0730.boot.admin.project.system.quartz.model.dto.JobLogDTO;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobLogEntity;
import com.hb0730.boot.admin.project.system.quartz.model.query.JobLogParams;
import com.hb0730.boot.admin.project.system.quartz.service.IJobLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * 任务日志  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class JobLogServiceImpl extends SuperBaseServiceImpl<Long, JobLogParams, JobLogDTO, JobLogEntity, IJobLogMapper> implements IJobLogService {

    @Override
    public QueryWrapper<JobLogEntity> query(@Nonnull JobLogParams params) {
        QueryWrapper<JobLogEntity> query = QueryWrapperUtils.getQuery(params);
        if (Objects.nonNull(params.getJobId())) {
            query.eq(JobLogEntity.JOB_ID, params.getJobId());
        }
        return query;
    }
}
