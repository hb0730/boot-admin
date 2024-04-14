package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.RpcRemoteProperties;
import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.job.domain.QuartzJobDto;
import com.hb0730.rpc.job.service.QuartzJobServerRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Service
@Slf4j
public class QuartzJobServerRemoteRpcService extends BaseRemoteRpcService<QuartzJobServerRpcService> implements QuartzJobServerRpcService {
    @Value("${boot.admin.rpc.job.remote.rul:127.0.0.1:12201}")
    private String url;

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

    @Override
    public RpcRemoteProperties getRpcRemoteProperties() {
        return new RpcRemoteProperties().setService(
                new RpcRemoteProperties.Service().setBoltServer(url)
        );
    }
}
