package com.hb0730.boot.admin.project.monitor.operlog.service.impl;

import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.project.monitor.operlog.mapper.ISystemOperLogMapper;
import com.hb0730.boot.admin.project.monitor.operlog.service.ISystemOperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务操作日志  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-02
 */
@Service
public class SystemOperLogServiceImpl extends ServiceImpl<ISystemOperLogMapper, SystemOperLogEntity> implements ISystemOperLogService {

}
