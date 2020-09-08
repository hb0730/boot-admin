package com.hb0730.boot.admin.project.system.quartz.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.quartz.mapper.IJobLogMapper;
import com.hb0730.boot.admin.project.system.quartz.model.dto.JobLogDTO;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobLogEntity;
import com.hb0730.boot.admin.project.system.quartz.model.query.JobLogParams;
import com.hb0730.boot.admin.project.system.quartz.service.IJobLogService;
import org.springframework.stereotype.Service;

/**
 * 任务日志  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class JobLogServiceImpl extends SuperBaseServiceImpl<Long, JobLogParams, JobLogDTO, JobLogEntity, IJobLogMapper> implements IJobLogService {
}
