package com.hb0730.boot.admin.project.system.quartz.service.impl;

import cn.hutool.core.util.StrUtil;
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
        if (StrUtil.isNotBlank(params.getJobName())) {
            query.eq(JobLogEntity.JOB_NAME, params.getJobName());
        }
        if (StrUtil.isNotBlank(params.getJobGroup())) {
            query.eq(JobLogEntity.JOB_GROUP, params.getJobGroup());
        }
        if (Objects.nonNull(params.getStatus())) {
            query.eq(JobLogEntity.STATUS, params.getStatus());
        }
        if (Objects.nonNull(params.getStartTime())) {
            query.gt(JobLogEntity.START_TIME, params.getStartTime());
        }
        if (Objects.nonNull(params.getEndTime())) {
            query.le(JobLogEntity.END_TIME, params.getEndTime());
        }
        return query;
    }
}
