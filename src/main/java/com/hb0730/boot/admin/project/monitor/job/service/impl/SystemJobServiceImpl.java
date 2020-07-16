package com.hb0730.boot.admin.project.monitor.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.constant.ActionEnum;
import com.hb0730.boot.admin.commons.domain.service.SuperBaseService;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.event.job.JobEvent;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.exception.file.FileUploadException;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.project.monitor.job.mapper.ISystemJobMapper;
import com.hb0730.boot.admin.project.monitor.job.model.dto.JobExportDto;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.hb0730.boot.admin.project.monitor.job.model.vo.JobParams;
import com.hb0730.boot.admin.project.monitor.job.model.vo.SystemJobVO;
import com.hb0730.boot.admin.project.monitor.job.service.ISystemJobService;
import com.hb0730.boot.admin.task.spring.TaskConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 定时任务  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
@Service
public class SystemJobServiceImpl extends SuperBaseService<JobParams, SystemJobVO, ISystemJobMapper, SystemJobEntity> implements ISystemJobService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SystemJobEntity entity) {
        verify(entity);
        String number = entity.getNumber();
        QueryWrapper<SystemJobEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemJobEntity.NUMBER, number);
        List<SystemJobEntity> list = super.list(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            throw new BaseException("编码已存在");
        }
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
    public Page<SystemJobVO> page(@NonNull JobParams params) {
        QueryWrapper<SystemJobEntity> query = query(params);
        Page<SystemJobEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, SystemJobVO.class);
    }

    @Override
    public List<SystemJobVO> list(@NonNull JobParams params) {
        QueryWrapper<SystemJobEntity> query = query(params);
        List<SystemJobEntity> list = super.list(query);
        return BeanUtils.transformFromInBatch(list, SystemJobVO.class);
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

    @Override
    @Async
    public void executor(Long id) {
        SystemJobEntity entity = super.getById(id);
        if (Objects.isNull(entity)) {
            return;
        }
        TaskConstant constant = new TaskConstant();
        constant.setBeanName(entity.getBeanName());
        constant.setMethodName(entity.getMethodName());
        constant.setCron(entity.getCron());
        constant.setTaskId(String.valueOf(entity.getId()));
        constant.setParams(entity.getParams());
        AsyncManager.me().executorJob(constant);
    }

    @Override
    public List<JobExportDto> export(@NonNull JobParams params) {
        QueryWrapper<SystemJobEntity> query = query(params);
        List<SystemJobEntity> entities = super.list(query);
        return BeanUtils.transformFromInBatch(entities, JobExportDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean upload(Collection<JobExportDto> list) {
        System.out.println("导入");
        QueryWrapper<SystemJobEntity> queryWrapper = new QueryWrapper<>();
        list.forEach((dto) -> {
            String number = dto.getNumber();
            queryWrapper.eq(SystemJobEntity.NUMBER, number);
            int count = super.count(queryWrapper);
            if (count > 0) {
                throw new FileUploadException("定时任务 %s 已存在，请检查", number);
            }
            SystemJobEntity entity = new SystemJobEntity();
            org.springframework.beans.BeanUtils.copyProperties(dto, entity);
            verify(entity);
        });
        List<SystemJobEntity> entities = BeanUtils.transformFromInBatch(list, SystemJobEntity.class);
        return saveBatch(entities);
    }

    @Override
    @NonNull
    public QueryWrapper<SystemJobEntity> query(@NonNull JobParams params) {
        QueryWrapper<SystemJobEntity> query = QueryWrapperUtils.getQuery(params);
        if (StringUtils.isNotBlank(params.getNumber())) {
            query.eq(SystemJobEntity.NUMBER, params.getNumber());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            query.like(SystemJobEntity.NAME, params.getName());
        }
        if (Objects.nonNull(params.getEnabled())) {
            query.eq(SystemJobEntity.IS_ENABLED, params.getEnabled());
        }
        return query;
    }


    private void verify(SystemJobEntity entity) {
        if (StringUtils.isBlank(entity.getNumber())) {
            throw new BaseException("任务编码为空");
        }
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
