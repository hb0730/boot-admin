package com.hb0730.boot.admin.project.monitor.operation.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.monitor.operation.model.dto.OperLogDTO;
import com.hb0730.boot.admin.project.monitor.operation.model.entity.OperLogEntity;
import com.hb0730.boot.admin.project.monitor.operation.model.query.OperLogParams;

/**
 * 操作日志  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IOperLogService extends IBaseService<Long, OperLogParams, OperLogDTO, OperLogEntity> {

}
