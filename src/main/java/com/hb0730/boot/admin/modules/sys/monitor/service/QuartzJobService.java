package com.hb0730.boot.admin.modules.sys.monitor.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.base.exception.BootAdminException;
import com.hb0730.boot.admin.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.data.enums.EnabledEnums;
import com.hb0730.boot.admin.modules.sys.monitor.model.entity.QuartzJob;
import com.hb0730.boot.admin.modules.sys.monitor.model.vo.QuartzJobVO;
import com.hb0730.boot.admin.modules.sys.monitor.mapper.QuartzJobMapper;
import com.hb0730.boot.admin.modules.sys.monitor.model.query.QuartzJobQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class QuartzJobService extends BaseServiceImpl<QuartzJobMapper, QuartzJob> {
    private final Scheduler scheduler;

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    public BasePage<QuartzJobVO> queryPage(QuartzJobQuery query) {
        Page<QuartzJobVO> page = new Page<>(query.getCurrent(), query.getSize());
        List<QuartzJobVO> list = this.baseMapper.queryPage(page, query);
        return new BasePage<>(page.getCurrent(), page.getSize(), page.getTotal(), list);
    }

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    public List<QuartzJobVO> queryList(QuartzJobQuery query) {
        return this.baseMapper.queryPage(null, query);
    }

    /**
     * 根据任务实现类全名取得匹配的任务
     *
     * @param jobClassName 任务实现类全名
     * @return 匹配的任务
     */
    public List<QuartzJob> findByJobClassName(String jobClassName) {
        return this.baseMapper.findByJobClassName(jobClassName);
    }

    /**
     * 保存&启动定时任务
     *
     * @param job 任务
     * @return 处理结果
     */
    public boolean saveAndScheduleJob(QuartzJob job) {
        if (EnabledEnums.enabled.getValue().equals(job.getStatus())) {
            // 定时器添加
            this.schedulerAdd(
                    job.getJobClassName().trim(),
                    job.getCronExpression().trim(),
                    job.getParameter());
        }
        return this.save(job);
    }

    /**
     * 编辑&启停定时任务
     *
     * @param job 任务
     * @return 处理结果
     * @throws SchedulerException .
     */
    public boolean editAndScheduleJob(QuartzJob job) throws SchedulerException {
        if (EnabledEnums.enabled.getValue().equals(job.getStatus())) {
            schedulerDelete(job.getJobClassName().trim());

            schedulerAdd(
                    job.getJobClassName().trim(),
                    job.getCronExpression().trim(),
                    job.getParameter());
        } else {
            scheduler.pauseJob(JobKey.jobKey(job.getJobClassName().trim()));
        }

        return this.updateById(job);
    }

    /**
     * 删除&停止删除定时任务
     *
     * @param job 任务
     * @return 处理结果
     */
    public boolean deleteAndStopJob(QuartzJob job) {
        schedulerDelete(job.getJobClassName().trim());

        return this.removeById(job.getId());
    }

    /**
     * 暂停定时任务
     *
     * @param job 任务
     * @return 处理结果
     */
    public boolean pauseJob(QuartzJob job) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(job.getJobClassName().trim()));

        job.setStatus(EnabledEnums.un_enabled.getValue());
        return this.updateById(job);
    }

    /**
     * 恢复定时任务
     *
     * @param job 任务
     * @return 处理结果
     */
    public boolean resumeJob(QuartzJob job) {
        schedulerDelete(job.getJobClassName().trim());

        schedulerAdd(
                job.getJobClassName().trim(),
                job.getCronExpression().trim(),
                job.getParameter());

        job.setStatus(EnabledEnums.enabled.getValue());
        return this.updateById(job);
    }

    /**
     * 立即执行定时任务
     *
     * @param job 任务
     */
    public void executeJob(QuartzJob job) {

        schedulerDelete(job.getJobClassName().trim());

        //没有cronExpression表达式，只执行一次
        schedulerAdd(
                job.getJobClassName().trim(),
                job.getParameter());
    }

    /**
     * 删除定时任务
     *
     * @param jobClassName 任务实现类全名
     */
    private void schedulerDelete(String jobClassName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BootAdminException("删除定时任务失败");
        }
    }

    /**
     * 添加定时任务
     *
     * @param jobClassName   任务实现类全名
     * @param cronExpression cron表达式
     * @param parameter      任务参数
     */
    private void schedulerAdd(String jobClassName, String cronExpression, String parameter) {
        try {
            // 启动调度器
            scheduler.start();

            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
                    .withIdentity(jobClassName)
                    .usingJobData("parameter", parameter)
                    .build();

            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobClassName)
                    .withSchedule(scheduleBuilder)
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new BootAdminException("创建定时任务失败", e);
        } catch (RuntimeException e) {
            throw new BootAdminException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BootAdminException("后台找不到该类名：" + jobClassName, e);
        }
    }

    /**
     * 添加定时任务，不带cron表达式，执行一次
     *
     * @param jobClassName 任务实现类全名
     * @param parameter    任务参数
     */
    private void schedulerAdd(String jobClassName, String parameter) {
        try {
            // 启动调度器
            scheduler.start();

            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
                    .withIdentity(jobClassName)
                    .usingJobData("parameter", parameter)
                    .build();

            // 没有cronExpression表达式构建一个新的trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobClassName)
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            throw new BootAdminException("创建定时任务失败", e);
        } catch (RuntimeException e) {
            throw new BootAdminException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BootAdminException("后台找不到该类名：" + jobClassName, e);
        }
    }

    /**
     * 实例化指定任务实现类
     *
     * @param jobClassName 任务实现类全名
     * @return 任务实现类实例
     * @throws Exception .
     */
    @SuppressWarnings("unchecked")
    private static Job getClass(String jobClassName) throws Exception {
        Class<Job> clazz = (Class<Job>) Class.forName(jobClassName);
        return clazz.getConstructor().newInstance();
    }

}
