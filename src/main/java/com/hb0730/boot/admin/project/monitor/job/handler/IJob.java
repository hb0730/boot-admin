package com.hb0730.boot.admin.project.monitor.job.handler;

import com.hb0730.boot.admin.model.enums.ActionEnum;
import com.hb0730.boot.admin.model.enums.JobTypeEnum;
import org.springframework.lang.NonNull;

import java.util.Collection;

/**
 * 定时任务
 *
 * @author bing_huang
 * @date 2020/06/29 7:51
 * @since V2.0
 */
public interface IJob {
    /**
     * 初始化
     */
    void init();

    /**
     * 更新删除新增
     *
     * @param type 动作类型
     * @param ids  ids
     */
    void updateJob(ActionEnum type, Collection<Long> ids);

    /**
     * 检查是否支持给定类型
     *
     * @param type 附件类型
     * @return true为支持类型
     */
    boolean supportType(@NonNull JobTypeEnum type);

}
