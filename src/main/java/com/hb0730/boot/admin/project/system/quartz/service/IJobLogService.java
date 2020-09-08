package com.hb0730.boot.admin.project.system.quartz.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.quartz.model.dto.JobLogDTO;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobLogEntity;
import com.hb0730.boot.admin.project.system.quartz.model.query.JobLogParams;

/**
 * 任务日志  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IJobLogService extends IBaseService<Long, JobLogParams, JobLogDTO, JobLogEntity> {

}
