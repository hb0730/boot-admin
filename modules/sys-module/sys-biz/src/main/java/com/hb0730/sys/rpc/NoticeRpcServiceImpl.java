package com.hb0730.sys.rpc;

import cn.hutool.core.bean.BeanUtil;
import com.hb0730.base.rpc.server.BaseServerRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.domain.NoticeDto;
import com.hb0730.rpc.sys.domain.query.NoticeQuery;
import com.hb0730.rpc.sys.service.NoticeRpcService;
import com.hb0730.sys.rpc.mapstruct.NoticeRpcMapstruct;
import com.hb0730.sys.service.SysNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeRpcServiceImpl extends BaseServerRpcService<NoticeRpcService> implements NoticeRpcService {
    private final SysNoticeService noticeService;
    private final NoticeRpcMapstruct mapstruct;

    @Override
    public JR<List<NoticeDto>> list(NoticeQuery query) {
        com.hb0730.sys.domain.query.NoticeQuery _query = BeanUtil.toBean(query, com.hb0730.sys.domain.query.NoticeQuery.class);
        List<com.hb0730.sys.domain.dto.NoticeDto> list = noticeService.list(_query);
        List<NoticeDto> res = mapstruct.toDtoList(list);
        return JR.okData(res);

    }

    @Override
    public JR<String> close(List<String> ids) {
        noticeService.closeByIds(ids);
        return JR.ok();
    }
}
