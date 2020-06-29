package com.hb0730.boot.admin.project.monitor.job.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.boot.admin.commons.constant.ActionEnum;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.constant.enums.JobTypeEnum;
import com.hb0730.boot.admin.project.monitor.job.mapper.ISystemJobMapper;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * @author bing_huang
 * @date 2020/06/29 7:54
 * @since V2.0
 */
@AllArgsConstructor
public abstract class AbstractJob implements IJob {
    private final ISystemJobMapper mapper;

    /**
     * 根据id获取已启用的任务
     *
     * @param ids  id
     * @param type 动作类型
     * @return 已启用的任务集
     */
    protected List<SystemJobEntity> getEnabledJob(Collection<Long> ids, ActionEnum type) {
        QueryWrapper<SystemJobEntity> queryWrapper = new QueryWrapper<>();
        if (!CollectionUtils.isEmpty(ids)) {
            queryWrapper.in(SystemJobEntity.ID, ids);
        }
        if (type == ActionEnum.SAVE || type == ActionEnum.SELECT) {
            queryWrapper.eq(SystemJobEntity.IS_ENABLED, SystemConstants.ENABLED);
        }

        return mapper.selectList(queryWrapper);

    }

    @Override
    public boolean supportType(@NotNull JobTypeEnum type) {
        return false;
    }
}
