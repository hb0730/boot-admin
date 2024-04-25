package com.hb0730.sys.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.common.util.PageUtil;
import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;
import com.hb0730.sys.system.domain.SysNotice;
import com.hb0730.sys.system.repository.NoticeRepository;
import com.hb0730.sys.system.service.INoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements INoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public Integer countByIdNotIn(List<String> ids) {
        return noticeRepository.countByIdNotInAndEnabledIsTrue(ids);
    }

    @Override
    public List<String> getIdsByIdNotIn(List<String> ids) {
        return noticeRepository.getIdsByIdNotIn(ids);
    }

    @Override
    public List<String> getIdsNotices() {
        return noticeRepository.getIdsNotices();
    }

    @Override
    public int countNotices() {
        return noticeRepository.countByEnabledIsTrue();
    }

    @Override
    public Page<SysNotice> page(Specification<SysNotice> specification, Pageable pageable) {
        return noticeRepository.findAll(specification, pageable);
    }

    @Override
    public Page<SysNotice> page(NoticeQuery query) {
        Specification<SysNotice> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return noticeRepository.findAll(specification, page);
    }

    @Override
    public List<SysNotice> list(NoticeQuery query) {
        Specification<SysNotice> specification = FenixSpecification.ofBean(query);
        return noticeRepository.findAll(specification);
    }

    @Override
    public void save(SysNotice sysNotice) {
        noticeRepository.save(sysNotice);
    }

    @Override
    public void updateById(SysNotice sysNotice) {
        if (sysNotice.getId() == null) {
            throw new ServiceException("id不能为空");
        }
        SysNotice notice = noticeRepository.findById(sysNotice.getId()).orElseThrow(() -> new ServiceException("公告不存在"));
        BeanUtil.copyProperties(
                sysNotice,
                notice,
                CopyOptions.create().ignoreNullValue()
        );
        noticeRepository.save(notice);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        noticeRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeNoticeByIds(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        noticeRepository.updateEnabledIsFalseByIdIn(ids);
    }
}
