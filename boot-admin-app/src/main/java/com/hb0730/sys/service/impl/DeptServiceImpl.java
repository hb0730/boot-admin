package com.hb0730.sys.service.impl;

import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.utils.PageUtil;
import com.hb0730.biz.entity.system.Dept;
import com.hb0730.sys.bean.DeptQuery;
import com.hb0730.sys.repository.DeptRepository;
import com.hb0730.sys.service.IDeptService;
import jakarta.annotation.Nullable;
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
 * @date 2024/3/18
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DeptServiceImpl implements IDeptService {
    private final DeptRepository deptRepository;

    @Override
    public Boolean existsByCodeAndIdNot(String code, @Nullable Integer id) {
        if (null == id) {
            return deptRepository.existsByCode(code);
        }
        return deptRepository.existsByCodeAndIdNot(code, id);
    }

    @Override
    public List<Dept> list(DeptQuery query) {
        Specification<Dept> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sortsOption = query.getSorts();
        return sortsOption.map(orders -> deptRepository.findAll(specification, Sort.by(orders))).orElseGet(() -> deptRepository.findAll(specification));
    }

    @Override
    public Page<Dept> page(DeptQuery query) {
        Specification<Dept> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return deptRepository.findAll(specification, page);
    }

    @Override
    public void save(Dept dept) {
        deptRepository.save(dept);
    }

    @Override
    public void update(Dept dept) {
        if (dept.getId() == null) {
            throw new IllegalArgumentException("id不能为空");
        }
        deptRepository.save(dept);
    }


    @Override
    public void delete(Integer id) {
        deptRepository.deleteById(id);
    }
}
