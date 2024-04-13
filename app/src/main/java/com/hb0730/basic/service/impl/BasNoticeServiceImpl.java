package com.hb0730.basic.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.basic.domain.BasNoticeRecord;
import com.hb0730.basic.domain.BasUser;
import com.hb0730.basic.repository.BasNoticeRecordRepository;
import com.hb0730.basic.rpc.mapstruct.BasNoticeMapper;
import com.hb0730.basic.service.IBasNoticeService;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.basic.domain.BasNoticeDto;
import com.hb0730.rpc.basic.domain.query.BasNoticeQuery;
import com.hb0730.sys.system.domain.SysNotice;
import com.hb0730.sys.system.service.INoticeService;
import com.hb0730.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasNoticeServiceImpl implements IBasNoticeService {
    private final BasNoticeRecordRepository basNoticeRecordRepository;
    private final INoticeService noticeService;
    private final BasNoticeMapper basNoticeMapper;


    @Override
    public Integer countUnRead(String userId) {
        List<String> noticeIds = basNoticeRecordRepository.findNoticeIdByUserId(userId);
        if (CollectionUtil.isEmpty(noticeIds)) {
            return noticeService.countNotices();
        }
        return noticeService.countByIdNotIn(noticeIds);
    }

    @Override
    public JsfPage<BasNoticeDto> page(BasNoticeQuery query) {
        List<String> noticeIds = basNoticeRecordRepository.findNoticeIdByUserId(query.getUserId());
        query.setSorts("created desc");
        query.setEnabled(true);

        Specification<SysNotice> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        Page<SysNotice> noticePage = noticeService.page(specification, page);

        List<SysNotice> content = noticePage.getContent();

        List<BasNoticeDto> dtoList = basNoticeMapper.toDtoList(content);
        dtoList.forEach(
                notice -> isRead(notice, noticeIds)
        );

        return JsfPage.of(noticePage, dtoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reads(String userId, List<String> noticeId) {
        if (CollectionUtil.isEmpty(noticeId)) {
            List<String> readNoticeIds = basNoticeRecordRepository.findNoticeIdByUserId(userId);
            if (CollectionUtil.isEmpty(readNoticeIds)) {
                noticeId = noticeService.getIdsNotices();
            } else {
                noticeId = noticeService.getIdsByIdNotIn(readNoticeIds);
            }
        }
        List<BasNoticeRecord> notices = noticeId.stream().map(e -> {
            BasNoticeRecord noticeRecord = new BasNoticeRecord();
            BasUser basUser = new BasUser().setId(userId);
            noticeRecord.setUser(basUser);
            SysNotice sysNotice = new SysNotice().setId(e);
            noticeRecord.setNotice(sysNotice);
            noticeRecord.setReadTime(new Date());
            noticeRecord.setRead(true);
            return noticeRecord;
        }).toList();
        basNoticeRecordRepository.saveAll(notices);

    }

    private void isRead(BasNoticeDto notice, List<String> noticeIds) {
        if (noticeIds.contains(notice.getId())) {
            notice.setRead(true);
            return;
        }
        notice.setRead(false);
    }
}
