package com.hb0730.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.basic.domain.dto.BasNoticeDto;
import com.hb0730.basic.domain.entity.BasNoticeRecord;
import com.hb0730.basic.domain.query.BasNoticeQuery;
import com.hb0730.basic.mapper.BasNoticeRecordMapper;
import com.hb0730.basic.service.mapstruct.BasNoticeMapstruct;
import com.hb0730.common.api.JsfPage;
import com.hb0730.query.mybatis.plus.QueryHelper;
import com.hb0730.sys.domain.entity.SysNotice;
import com.hb0730.sys.service.SysNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasNoticeService extends ServiceImpl<BasNoticeRecordMapper, BasNoticeRecord> {
    private final SysNoticeService sysNoticeService;
    private final BasNoticeMapstruct mapstruct;

    /**
     * 查询未读消息数量
     *
     * @param userId 用户id
     * @return 未读消息数量
     */
    public Integer countUnread(String userId) {
        List<String> noticeIds = baseMapper.findNoticeIdByUserId(userId);
        if (CollectionUtil.isEmpty(noticeIds)) {
            return sysNoticeService.countNotices();
        }
        return sysNoticeService.countNotInIds(noticeIds);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<BasNoticeDto> page(BasNoticeQuery query) {
        List<String> noticeIds = baseMapper.findNoticeIdByUserId(query.getUserId());
        query.setSorts("created desc");
        query.setEnabled(true);
        QueryWrapper<SysNotice> queryWrapper = QueryHelper.ofBean(query);
        IPage<SysNotice> page = QueryHelper.toPage(query);
        page = sysNoticeService.page(page, queryWrapper);
        List<BasNoticeDto> res = mapstruct.toDtoList(page.getRecords());
        res.forEach(
                notice -> isRead(notice, noticeIds)
        );
        return QueryHelper.toJsfPage(page, res);
    }


    /**
     * 标记已读
     *
     * @param userId    用户ID
     * @param noticeIds 公告ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void reads(String userId, List<String> noticeIds) {
        if (CollectionUtil.isEmpty(noticeIds)) {
            List<String> readNoticeIds = baseMapper.findNoticeIdByUserId(userId);
            if (CollectionUtil.isEmpty(readNoticeIds)) {
                noticeIds = sysNoticeService.getIds();
            } else {
                noticeIds = sysNoticeService.getIdsNotInIds(readNoticeIds);
            }
        }
        List<BasNoticeRecord> notices = noticeIds.stream().map(e -> {
            BasNoticeRecord noticeRecord = new BasNoticeRecord();
            noticeRecord.setUserId(userId);
            noticeRecord.setNoticeId(e);
            noticeRecord.setReadTime(new Date());
            noticeRecord.setRead(true);
            return noticeRecord;
        }).toList();

        saveBatch(notices);
    }

    private void isRead(BasNoticeDto notice, List<String> noticeIds) {
        if (noticeIds.contains(notice.getId())) {
            notice.setRead(true);
            return;
        }
        notice.setRead(false);
    }

}
