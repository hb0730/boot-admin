package com.hb0730.basic.rpc;

import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.basic.service.IBasNoticeService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.basic.domain.BasNoticeDto;
import com.hb0730.rpc.basic.domain.query.BasNoticeQuery;
import com.hb0730.rpc.basic.service.BasNoticeRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasNoticeRpcServiceImpl extends BaseServerRpcService<BasNoticeRpcService> implements BasNoticeRpcService {
    private final IBasNoticeService basNoticeService;

    @Override
    public JR<Integer> countUnRead(String userId) {
        Integer res = basNoticeService.countUnRead(userId);
        return JR.okData(res);
    }

    @Override
    public JR<JsfPage<BasNoticeDto>> page(BasNoticeQuery query) {
        JsfPage<BasNoticeDto> res = basNoticeService.page(query);
        return JR.okData(res);
    }

    @Override
    public JR<String> read(String userId, List<String> noticeIds) {
        basNoticeService.reads(userId, noticeIds);
        return JR.okData("ok");
    }


}
