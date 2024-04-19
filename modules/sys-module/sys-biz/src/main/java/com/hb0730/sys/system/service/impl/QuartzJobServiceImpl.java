package com.hb0730.sys.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.common.util.PageUtil;
import com.hb0730.rpc.sys.system.domain.query.QuartzJobQuery;
import com.hb0730.sys.system.domain.SysQuartzJob;
import com.hb0730.sys.system.repository.QuartzJobRepository;
import com.hb0730.sys.system.service.IQuartzJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuartzJobServiceImpl implements IQuartzJobService {
    private final QuartzJobRepository quartzJobRepository;

    @Override
    public Page<SysQuartzJob> page(QuartzJobQuery query) {
        Specification<SysQuartzJob> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return quartzJobRepository.findAll(specification, page);
    }

    @Override
    public SysQuartzJob getById(String id) {
        return quartzJobRepository.findById(id).orElse(null);
    }

    @Override
    public void save(SysQuartzJob quartzJob) {
        quartzJobRepository.save(quartzJob);
    }

    @Override
    public void updateById(SysQuartzJob quartzJob) {
        String id = quartzJob.getId();
        SysQuartzJob job = quartzJobRepository.findById(id).orElseThrow(() -> new ServiceException("定时任务不存在"));
        BeanUtil.copyProperties(quartzJob, job, CopyOptions.create().setIgnoreNullValue(true));
        quartzJobRepository.save(job);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        quartzJobRepository.deleteAllByIdInBatch(ids);
    }
}
