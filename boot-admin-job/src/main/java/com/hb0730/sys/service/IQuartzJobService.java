package com.hb0730.sys.service;

import com.hb0730.base.R;
import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import com.hb0730.biz.entity.quartz.QuartzJob;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */
public interface IQuartzJobService {

    /**
     * 验证cron表达式
     *
     * @param cronExpression cron表达式
     * @return 是否成功
     */
    R<String> validateCronExpression(String cronExpression);

    /**
     * 根据任务实现类全名取得匹配的任务
     *
     * @param jobClassName 任务实现类全名
     * @return 匹配的任务
     */
    public List<QuartzJob> findByJobClassName(String jobClassName);

    /**
     * 保存&启动定时任务
     *
     * @param job 任务
     * @return 处理结果
     */
    R<String> addJob(QuartzJobDto job);

    /**
     * 更新定时任务
     *
     * @param job 任务
     * @return 处理结果
     */
    R<String> editJob(QuartzJobDto job);

    /**
     * 删除定时任务
     *
     * @param id 定时任务id
     * @return 处理结果
     */
    R<String> deleteJob(Integer id);

    /**
     * 暂停定时任务
     *
     * @param id 定时任务id
     * @return 处理结果
     */
    R<String> pauseJob(Integer id);

    /**
     * 恢复定时任务
     *
     * @param id 定时任务id
     * @return 处理结果
     */
    R<String> resumeJob(Integer id);

    /**
     * 执行定时任务
     *
     * @param id 定时任务id
     * @return 处理结果
     */
    R<String> executeJob(Integer id);

}
