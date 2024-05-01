package com.hb0730.rpc.job.service;

import com.hb0730.common.api.JR;
import com.hb0730.rpc.job.domain.QuartzJobDto;

/**
 * 定时任务RPC服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/13
 */
public interface QuartzJobServerRpcService {

    /**
     * 验证cron表达式
     *
     * @param cronExpression cron表达式
     * @return 是否成功
     */
    JR<String> validateCronExpression(String cronExpression);

    /**
     * 新增定时任务
     *
     * @param quartzJobDto 任务参数
     * @return 处理结果
     */
    JR<String> add(QuartzJobDto quartzJobDto);

    /**
     * 更新定时任务
     *
     * @param quartzJobDto 任务参数
     * @return 处理结果
     */
    JR<String> edit(QuartzJobDto quartzJobDto);

    /**
     * 删除定时任务
     *
     * @param quartzJobDto 任务参数
     * @return 处理结果
     */
    JR<String> delete(QuartzJobDto quartzJobDto);

    /**
     * 暂停定时任务
     *
     * @param quartzJobDto 任务参数
     * @return 处理结果
     */
    JR<String> pauseJob(QuartzJobDto quartzJobDto);

    /**
     * 恢复定时任务
     *
     * @param quartzJobDto 任务参数
     * @return 处理结果
     */
    JR<String> resumeJob(QuartzJobDto quartzJobDto);

    /**
     * 立即执行定时任务
     *
     * @param quartzJobDto 任务参数
     * @return 处理结果
     */
    JR<String> run(QuartzJobDto quartzJobDto);
}