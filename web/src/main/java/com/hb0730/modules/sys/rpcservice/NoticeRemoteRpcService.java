package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.NoticeDto;
import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;
import com.hb0730.rpc.sys.system.service.NoticeRpcService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Service
public class NoticeRemoteRpcService extends BaseRemoteRpcService<NoticeRpcService> implements NoticeRpcService {
    @Override
    protected String getAppName() {
        return "sys";
    }

    @Override
    public JR<JsfPage<NoticeDto>> page(NoticeQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<String> save(NoticeDto noticeDto) {
        return getRpcService().save(noticeDto);
    }

    @Override
    public JR<String> updateById(NoticeDto noticeDto) {
        return getRpcService().updateById(noticeDto);
    }

    @Override
    public JR<String> deleteByIds(List<String> id) {
        return getRpcService().deleteByIds(id);
    }
}
