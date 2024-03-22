package com.hb0730.sys.service.impl;

import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.utils.PageUtil;
import com.hb0730.biz.entity.quartz.QuartzJob;
import com.hb0730.sys.bean.QuartzQuery;
import com.hb0730.sys.repository.QuartzRepository;
import com.hb0730.sys.service.IQuartzService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/21
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuartzService implements IQuartzService {
    private final QuartzRepository quartzRepository;

    @Override
    public Page<QuartzJob> page(QuartzQuery query) {
        Specification<QuartzJob> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return quartzRepository.findAll(specification, page);
    }

    @Override
    public List<QuartzJob> list(QuartzQuery query) {
        Specification<QuartzJob> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sorts = query.getSorts();
        return sorts.map(orders -> quartzRepository.findAll(specification, Sort.by(orders))).orElseGet(() -> quartzRepository.findAll(specification));
    }
}
