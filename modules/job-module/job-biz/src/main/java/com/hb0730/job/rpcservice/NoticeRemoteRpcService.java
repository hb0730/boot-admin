package com.hb0730.job.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.system.domain.NoticeDto;
import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;
import com.hb0730.rpc.sys.system.service.NoticeRpcService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/25
 */
@Service
public class NoticeRemoteRpcService extends BaseRemoteRpcService<NoticeRpcService> implements NoticeRpcService {
    @Override
    protected String getAppName() {
        return "sys";
    }

    @Override
    public JR<List<NoticeDto>> list(NoticeQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<String> closeNoticeByIds(List<String> ids) {
        return getRpcService().closeNoticeByIds(ids);
    }
}
