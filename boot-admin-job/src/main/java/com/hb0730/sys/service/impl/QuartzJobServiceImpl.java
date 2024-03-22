package com.hb0730.sys.service.impl;

import com.hb0730.base.R;
import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import com.hb0730.biz.entity.quartz.QuartzJob;
import com.hb0730.component.JobManager;
import com.hb0730.sys.repository.QuartzJobRepository;
import com.hb0730.sys.service.IQuartzJobService;
import com.hb0730.sys.service.mapstruct.QuartzJobMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuartzJobServiceImpl implements IQuartzJobService {
    private final QuartzJobRepository quartzJobRepository;
    private final JobManager jobManager;
    private final QuartzJobMapper quartzJobMapper;

    @Override
    public R<String> validateCronExpression(String cronExpression) {
        if (!CronExpression.isValidExpression(cronExpression)) {
            return R.NG(0, "cron表达式不合法");
        }
        return R.OK();
    }

    @Override
    public List<QuartzJob> findByJobClassName(String jobClassName) {
        return quartzJobRepository.findByJobClassName(jobClassName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<String> addJob(QuartzJobDto job) {
        if (!CronExpression.isValidExpression(job.getCronExpression())) {
            return R.NG("cron表达式不合法");
        }
        QuartzJob entity = quartzJobMapper.toEntity(job);

        entity = quartzJobRepository.save(entity);

        if (Boolean.TRUE.equals(entity.getEnabled())) {
            jobManager.jobAdd(entity);
        }
        return R.OK("添加成功");
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<String> editJob(QuartzJobDto job) {
        if (!CronExpression.isValidExpression(job.getCronExpression())) {
            return R.NG("cron表达式不合法");
        }
        QuartzJob entity = quartzJobMapper.toEntity(job);
        entity = quartzJobRepository.save(entity);


        if (Boolean.TRUE.equals(entity.getEnabled())) {
            jobManager.jobDelete(entity.getId());
            jobManager.jobAdd(entity);
        } else {
            jobManager.jobPause(entity.getId());
        }
        return null;
    }

    @Override
    public R<String> deleteJob(Integer id) {
        QuartzJob job = quartzJobRepository.findById(id).orElse(null);
        if (job == null) {
            return R.NG("定时任务不存在");
        }
        quartzJobRepository.deleteById(id);
        jobManager.jobDelete(id);
        return R.OK("删除成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<String> pauseJob(Integer id) {
        quartzJobRepository.updateEnabledById(id, false);
        QuartzJob quartzJob = quartzJobRepository.findById(id).orElse(null);
        if (quartzJob == null) {
            throw new RuntimeException("定时任务不存在");
        }
        jobManager.jobPause(id);
        return R.OK("暂停成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<String> resumeJob(Integer id) {
        quartzJobRepository.updateEnabledById(id, true);
        QuartzJob quartzJob = quartzJobRepository.findById(id).orElse(null);
        if (quartzJob == null) {
            throw new RuntimeException("定时任务不存在");
        }
        jobManager.jobResume(quartzJob);
        return R.OK("恢复成功");
    }

    @Override
    public R<String> executeJob(Integer id) {
        QuartzJob job = quartzJobRepository.findById(id).orElse(null);
        if (job == null) {
            return R.NG("定时任务不存在");
        }
        jobManager.jobRunOnce(job);
        return R.OK("执行成功");
    }
}
