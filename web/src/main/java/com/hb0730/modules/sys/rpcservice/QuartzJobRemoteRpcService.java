package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.QuartzJobDto;
import com.hb0730.rpc.sys.system.domain.query.QuartzJobQuery;
import com.hb0730.rpc.sys.system.service.QuartzJobRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Service
@Slf4j
public class QuartzJobRemoteRpcService extends BaseRemoteRpcService<QuartzJobRpcService> implements QuartzJobRpcService {
    @Override
    protected String getAppName() {
        return "job";
    }

    @Override
    public JR<JsfPage<QuartzJobDto>> page(QuartzJobQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<QuartzJobDto> getById(String id) {
        return getRpcService().getById(id);
    }

    @Override
    public JR<String> save(QuartzJobDto quartzJob) {
        return getRpcService().save(quartzJob);
    }

    @Override
    public JR<String> updateById(QuartzJobDto quartzJob) {
        return getRpcService().updateById(quartzJob);
    }

    @Override
    public JR<String> deleteByIds(List<String> ids) {
        return getRpcService().deleteByIds(ids);
    }
}
