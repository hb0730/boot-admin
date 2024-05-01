package com.hb0730.sys.remoterpc;

import com.hb0730.base.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.job.domain.QuartzJobDto;
import com.hb0730.rpc.job.service.QuartzJobServerRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
@Service
@Slf4j
public class JobRemoteRpcService extends BaseRemoteRpcService<QuartzJobServerRpcService> implements QuartzJobServerRpcService {

    @Override
    protected String getAppName() {
        return "job";
    }

    @Override
    public JR<String> validateCronExpression(String cronExpression) {
        return getRpcService().validateCronExpression(cronExpression);
    }

    @Override
    public JR<String> add(QuartzJobDto quartzJobDto) {
        return getRpcService().add(quartzJobDto);
    }

    @Override
    public JR<String> edit(QuartzJobDto quartzJobDto) {
        return getRpcService().edit(quartzJobDto);
    }

    @Override
    public JR<String> delete(QuartzJobDto quartzJobDto) {
        return getRpcService().delete(quartzJobDto);
    }

    @Override
    public JR<String> pauseJob(QuartzJobDto quartzJobDto) {
        return getRpcService().pauseJob(quartzJobDto);
    }

    @Override
    public JR<String> resumeJob(QuartzJobDto quartzJobDto) {
        return getRpcService().resumeJob(quartzJobDto);
    }

    @Override
    public JR<String> run(QuartzJobDto quartzJobDto) {
        return getRpcService().run(quartzJobDto);
    }
}
