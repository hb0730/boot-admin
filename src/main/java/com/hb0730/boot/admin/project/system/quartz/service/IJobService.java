package com.hb0730.boot.admin.project.system.quartz.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.quartz.model.dto.JobDTO;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobEntity;
import com.hb0730.boot.admin.project.system.quartz.model.query.JobParams;

/**
 * 定时任务(quartz)  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IJobService extends IBaseService<Long, JobParams, JobDTO, JobEntity> {

}
