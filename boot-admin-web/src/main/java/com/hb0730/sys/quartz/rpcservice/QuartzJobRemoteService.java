package com.hb0730.sys.quartz.rpcservice;

import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.conf.rpc.remote.BaseRemoteService;
import com.hb0730.sys.bean.QuartzQuery;
import com.hb0730.sys.service.QuartzJobRcpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/21
 */
@Service
@Slf4j
public class QuartzJobRemoteService extends BaseRemoteService<QuartzJobRcpService> implements QuartzJobRcpService {

    @Override
    public JR<JsfPage<QuartzJobDto>> page(QuartzQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<List<QuartzJobDto>> list(QuartzQuery query) {
        return getRpcService().list(query);
    }
}
