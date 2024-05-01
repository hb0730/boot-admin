package com.hb0730.rpcservice;

import com.hb0730.base.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.domain.NoticeDto;
import com.hb0730.rpc.sys.domain.query.NoticeQuery;
import com.hb0730.rpc.sys.service.NoticeRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
@Slf4j
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
    public JR<String> close(List<String> ids) {
        return getRpcService().close(ids);
    }
}
