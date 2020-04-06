package com.hb0730.boot.admin.project.monitor.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb0730.boot.admin.commons.constant.ActionEnum;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.event.job.JobEvent;
import com.hb0730.boot.admin.project.monitor.job.mapper.ISystemJobMapper;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.hb0730.boot.admin.project.monitor.job.service.ISystemJobService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

/**
 * <p>
 * 定时任务  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
@Service
public class SystemJobServiceImpl extends ServiceImpl<ISystemJobMapper, SystemJobEntity> implements ISystemJobService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SystemJobEntity entity) {
        verify(entity);
        boolean save = super.save(entity);
        applicationContext.publishEvent(new JobEvent(this, Collections.singletonList(entity.getId()), ActionEnum.SAVE));
        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(SystemJobEntity entity) {
        verify(entity);
        SystemJobEntity e1 = super.getById(entity.getId());
        BeanUtils.updateProperties(entity, e1);
        boolean b = super.updateById(entity);
        applicationContext.publishEvent(new JobEvent(this, Collections.singletonList(entity.getId()), ActionEnum.UPDATE));
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Long id) {
        boolean b = super.removeById(id);
        applicationContext.publishEvent(new JobEvent(this, Collections.singletonList(id), ActionEnum.DELETE));
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(Collection<Long> ids) {
        boolean b = super.removeByIds(ids);
        applicationContext.publishEvent(new JobEvent(this, ids, ActionEnum.DELETE));
        return b;
    }

    private void verify(SystemJobEntity entity) {
        if (StringUtils.isBlank(entity.getBeanName())) {
            throw new BaseException("调用类为空");
        }
        if (StringUtils.isBlank(entity.getMethodName())) {
            throw new BaseException("调用方法为空");
        }
        if (StringUtils.isBlank(entity.getCron()) || !CronSequenceGenerator.isValidExpression(entity.getCron())) {
            throw new BaseException("定时任务cron 表达式不合法");
        }
    }
}
