package com.hb0730.service;

import com.hb0730.common.api.JR;
import com.hb0730.rpc.job.domain.QuartzJobDto;
import com.hb0730.rpc.job.service.QuartzJobServerRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Service("quartzJobService")
@Slf4j
@RequiredArgsConstructor
public class QuartzJobServerService implements QuartzJobServerRpcService {
    private final JobManager jobManager;

    @Override
    public JR<String> validateCronExpression(String cronExpression) {
        boolean res = CronExpression.isValidExpression(cronExpression);
        if (res) {
            return JR.ok();
        }
        return JR.fail("cron表达式错误");
    }

    @Override
    public JR<String> add(QuartzJobDto quartzJobDto) {
        jobManager.jobAdd(quartzJobDto);
        if (Boolean.FALSE.equals(quartzJobDto.getEnabled())) {
            pauseJob(quartzJobDto);
        }
        return JR.ok();
    }

    @Override
    public JR<String> edit(QuartzJobDto quartzJobDto) {

        if (Boolean.TRUE.equals(quartzJobDto.getEnabled())) {
            jobManager.jobDelete(quartzJobDto.getId(), quartzJobDto.getSysCode());
            jobManager.jobAdd(quartzJobDto);
        } else {
            jobManager.jobPause(quartzJobDto.getId(), quartzJobDto.getSysCode());
        }
        return JR.ok();
    }

    @Override
    public JR<String> delete(QuartzJobDto quartzJobDto) {
        jobManager.jobDelete(quartzJobDto.getId(), quartzJobDto.getSysCode());
        return JR.ok();
    }

    @Override
    public JR<String> pauseJob(QuartzJobDto quartzJobDto) {
        jobManager.jobPause(quartzJobDto.getId(), quartzJobDto.getSysCode());
        return JR.ok();
    }

    @Override
    public JR<String> resumeJob(QuartzJobDto quartzJobDto) {
        jobManager.jobResume(quartzJobDto);
        return JR.ok();
    }

    @Override
    public JR<String> run(QuartzJobDto quartzJobDto) {
        jobManager.jobRunOnce(quartzJobDto);
        return JR.ok();
    }
}