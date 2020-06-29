package com.hb0730.boot.admin.project.monitor.job.handler;

import com.hb0730.boot.admin.commons.constant.ActionEnum;
import com.hb0730.boot.admin.project.monitor.job.mapper.ISystemJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class JobHandler extends AbstractJob {
    @Autowired
    private JobHandlers jobHandlers;

    public JobHandler(ISystemJobMapper mapper) {
        super(mapper);
    }

    /**
     * 初始化
     * {@code @postConstruct 导致spring Scheduling 还未装配}
     */
    @Override
    public void init() {
        jobHandlers.init();
    }

    /**
     * <p>
     * 更新删除新增
     * </p>
     *
     * @param type 动作类型
     * @param ids  ids
     */
    @Override
    public void updateJob(ActionEnum type, Collection<Long> ids) {
        jobHandlers.updateJob(type, ids);
    }

}
