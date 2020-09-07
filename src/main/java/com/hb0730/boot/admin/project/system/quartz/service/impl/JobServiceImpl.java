package com.hb0730.boot.admin.project.system.quartz.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.quartz.mapper.IJobMapper;
import com.hb0730.boot.admin.project.system.quartz.model.dto.JobDTO;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobEntity;
import com.hb0730.boot.admin.project.system.quartz.model.query.JobParams;
import com.hb0730.boot.admin.project.system.quartz.service.IJobService;
import org.springframework.stereotype.Service;

/**
 * 定时任务(quartz)  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class JobServiceImpl extends SuperBaseServiceImpl<Long, JobParams, JobDTO, JobEntity, IJobMapper> implements IJobService {

}
