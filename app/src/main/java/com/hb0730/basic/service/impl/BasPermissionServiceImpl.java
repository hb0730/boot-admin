package com.hb0730.basic.service.impl;

import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.basic.domain.BasPermission;
import com.hb0730.basic.repository.BasPermissionRepository;
import com.hb0730.basic.service.IBasPermissionService;
import com.hb0730.rpc.basic.domain.query.BasPermissionQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasPermissionServiceImpl implements IBasPermissionService {
    private final BasPermissionRepository basPermissionRepository;

    @Override
    public List<BasPermission> findByRoleIds(List<String> roleIds) {
        return basPermissionRepository.findByRoleIds(roleIds);
    }

    @Override
    public List<BasPermission> list(BasPermissionQuery query) {
        Specification<BasPermission> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sorts = query.getSorts();
        return sorts.map(sort -> basPermissionRepository.findAll(specification, Sort.by(sort)))
                .orElseGet(() -> basPermissionRepository.findAll(specification));
    }
}
