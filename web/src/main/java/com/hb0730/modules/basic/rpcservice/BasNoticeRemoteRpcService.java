package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.basic.domain.BasNoticeDto;
import com.hb0730.rpc.basic.domain.query.BasNoticeQuery;
import com.hb0730.rpc.basic.service.BasNoticeRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
@Service
@Slf4j
public class BasNoticeRemoteRpcService extends BaseRemoteRpcService<BasNoticeRpcService> implements BasNoticeRpcService {

    @Override
    public JR<Integer> countUnRead(String userId) {
        return getRpcService().countUnRead(userId);
    }

    @Override
    public JR<JsfPage<BasNoticeDto>> page(BasNoticeQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<String> read(String userId, List<String> noticeIds) {
        return getRpcService().read(userId, noticeIds);
    }
}
