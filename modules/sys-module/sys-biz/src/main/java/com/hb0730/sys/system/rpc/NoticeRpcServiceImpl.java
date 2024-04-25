package com.hb0730.sys.system.rpc;

import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.NoticeDto;
import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;
import com.hb0730.rpc.sys.system.service.NoticeRpcService;
import com.hb0730.sys.system.domain.SysNotice;
import com.hb0730.sys.system.rpc.mapstruct.NoticeMapper;
import com.hb0730.sys.system.service.INoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/3
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeRpcServiceImpl extends BaseServerRpcService<NoticeRpcService> implements NoticeRpcService {
    private final INoticeService noticeService;
    private final NoticeMapper noticeMapper;

    @Override
    public JR<JsfPage<NoticeDto>> page(NoticeQuery query) {
        Page<SysNotice> page = noticeService.page(query);
        List<NoticeDto> res = noticeMapper.toDtoList(page.getContent());
        return JR.okData(JsfPage.of(page, res));
    }

    @Override
    public JR<List<NoticeDto>> list(NoticeQuery query) {
        List<SysNotice> list = noticeService.list(query);
        return JR.okData(noticeMapper.toDtoList(list));
    }

    @Override
    public JR<String> save(NoticeDto noticeDto) {
        SysNotice notice = noticeMapper.toEntity(noticeDto);
        noticeService.save(notice);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(NoticeDto noticeDto) {
        SysNotice notice = noticeMapper.toEntity(noticeDto);
        noticeService.updateById(notice);
        return JR.ok();
    }

    @Override
    public JR<String> deleteByIds(List<String> ids) {
        noticeService.deleteByIds(ids);
        return JR.ok();
    }

    @Override
    public JR<String> closeNoticeByIds(List<String> ids) {
        noticeService.closeNoticeByIds(ids);
        return JR.ok();
    }
}
