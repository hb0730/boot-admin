package com.hb0730.boot.admin.project.monitor.job.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.constant.ActionEnum;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.utils.json.GsonUtils;
import com.hb0730.boot.admin.project.monitor.job.mapper.ISystemJobMapper;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.hb0730.boot.admin.task.spring.DynamicTask;
import com.hb0730.boot.admin.task.spring.TaskConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class JobHandler {
    @Autowired
    private DynamicTask dynamicTask;
    @Autowired
    private ISystemJobMapper mapper;

    /**
     * 初始化
     * {@code @postConstruct 导致spring Scheduling 还未装配}
     * @see com.hb0730.boot.admin.configuration.StartRunner#run(String...)
     */
    public void init() {
        List<SystemJobEntity> entities = getEnabledJob(null, ActionEnum.SELECT);
        if (!CollectionUtils.isEmpty(entities)) {
            List<TaskConstant> list = convertType(entities);
            addJob(list);
        }
    }

    /**
     * <p>
     * 更新删除新增
     * </p>
     *
     * @param type 动作类型
     * @param ids  ids
     */
    public void updateJob(ActionEnum type, Collection<Long> ids) {
        List<SystemJobEntity> job = getEnabledJob(ids, type);
        if (CollectionUtils.isEmpty(job)) {
            return;
        }

        switch (type) {
            case SAVE:
                List<TaskConstant> list = convertType(job);
                addJob(list);
                break;
            case UPDATE:
                Set<Long> notEnabledIds = job.stream().filter((entity) -> entity.getIsEnabled().equals(SystemConstants.NOT_USE)).map(SystemJobEntity::getId).collect(Collectors.toSet());
                Set<Long> enabledIds = job.stream().filter((entity) -> entity.getIsEnabled().equals(SystemConstants.USE)).map(SystemJobEntity::getId).collect(Collectors.toSet());
                cancel(notEnabledIds);

                if (!CollectionUtils.isEmpty(enabledIds)) {
                    List<SystemJobEntity> enabledJob = getEnabledJob(enabledIds, ActionEnum.SELECT);
                    List<TaskConstant> tasks = convertType(enabledJob);
                    addJob(tasks);
                }
                break;
            case DELETE:
                cancel(ids);
                break;
            default:
                break;
        }
    }

    /**
     * 新增job
     *
     * @param tasks 任务信息
     */
    public void addJob(List<TaskConstant> tasks) {
        if (!CollectionUtils.isEmpty(tasks)) {
            tasks.parallelStream().forEach((task) -> dynamicTask.addTriggerTask(task));
        }
    }

    /**
     * <p>
     * 取消相关任务定时
     * </p>
     *
     * @param jobIds 定时任务id
     */
    public void cancel(Collection<Long> jobIds) {
        if (!CollectionUtils.isEmpty(jobIds)) {
            jobIds.forEach((takId) -> {
                dynamicTask.cancelTriggerTask(String.valueOf(takId));
            });
        }
    }

    /**
     * 全部取消
     */
    public void cancelAll() {
        dynamicTask.cancelTriggerTask();
    }

    /**
     * <p>
     * 类型转换
     * </p>
     *
     * @param entities job任务
     * @return task任务
     */
    private List<TaskConstant> convertType(List<SystemJobEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        List<TaskConstant> list = Lists.newArrayList();
        entities.forEach((entity) -> {
            TaskConstant constant = new TaskConstant();
            constant.setBeanName(entity.getBeanName());
            constant.setMethodName(entity.getMethodName());
            constant.setCron(entity.getCron());
            constant.setTaskId(String.valueOf(entity.getId()));
            constant.setParams(entity.getParams());
            list.add(constant);
        });
        return list;
    }

    /**
     * <p>
     * 获取参数
     * </P>
     *
     * @param jsonParams json格式额参数
     * @return Object参数
     */
    private Object[] getParams(String jsonParams) {
        if (!StringUtils.isEmpty(jsonParams)) {
            String json = GsonUtils.json2String(jsonParams);
            List<Object> objects = GsonUtils.json2List(json, Object.class);
            return objects.toArray();
        } else {
            return null;
        }
    }

    /**
     * <p>
     * 根据id获取已启用的任务
     * </p>
     *
     * @param ids id
     * @return 已启用的任务集
     */
    private List<SystemJobEntity> getEnabledJob(Collection<Long> ids, ActionEnum type) {
        QueryWrapper<SystemJobEntity> queryWrapper = new QueryWrapper<>();
        if (!CollectionUtils.isEmpty(ids)) {
            queryWrapper.in(SystemJobEntity.ID, ids);
        }
        if (type == ActionEnum.SAVE || type == ActionEnum.SELECT) {
            queryWrapper.eq(SystemJobEntity.IS_ENABLED, SystemConstants.USE);
        }

        return mapper.selectList(queryWrapper);
    }

}
