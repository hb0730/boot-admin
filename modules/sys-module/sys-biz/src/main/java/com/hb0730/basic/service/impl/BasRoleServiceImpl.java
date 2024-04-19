package com.hb0730.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.basic.domain.BasPermission;
import com.hb0730.basic.domain.BasRole;
import com.hb0730.basic.repository.BasRoleRepository;
import com.hb0730.basic.service.IBasRoleService;
import com.hb0730.common.util.PageUtil;
import com.hb0730.rpc.basic.domain.query.BasRoleQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasRoleServiceImpl implements IBasRoleService {
    private final BasRoleRepository basRoleRepository;

    @Override
    public List<BasRole> findByIds(List<String> ids) {
        return basRoleRepository.findAllById(ids);
    }

    @Override
    public List<BasRole> findByUserId(String userId) {
        return basRoleRepository.findByUserId(userId);
    }

    @Override
    public Boolean existsByCode(String code, String sysCode, String id) {
        if (id == null) {
            return basRoleRepository.existsByCode(code, sysCode);
        }
        return basRoleRepository.existsByCode(code, sysCode, id);
    }

    @Override
    public Page<BasRole> page(BasRoleQuery query) {
        Specification<BasRole> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return basRoleRepository.findAll(specification, page);
    }

    @Override
    public List<BasRole> list(BasRoleQuery query) {
        Specification<BasRole> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sorts = query.getSorts();
        return sorts.map(sort -> basRoleRepository.findAll(specification, Sort.by(sort)))
                .orElseGet(() -> basRoleRepository.findAll(specification));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BasRole basRole) {
        basRole.setSystem(false);
        basRoleRepository.save(basRole);
    }

    @Override
    public void updateById(BasRole basRole) {
        if (basRole.getId() == null) {
            throw new ServiceException("id不能为空");
        }
        BasRole role = basRoleRepository.findById(basRole.getId()).orElseThrow(
                () -> new ServiceException("角色不存在"));
        if (Boolean.TRUE.equals(role.getSystem())) {
            throw new ServiceException("系统角色不能修改");
        }
        BeanUtil.copyProperties(basRole, role, CopyOptions.create().ignoreNullValue());
        basRoleRepository.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        BasRole role = basRoleRepository.findById(id).orElseThrow(
                () -> new ServiceException("角色不存在"));

        if (Boolean.TRUE.equals(role.getSystem())) {
            throw new ServiceException("系统角色不能删除");
        }
        basRoleRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantPermission(String id, List<Long> permissionIds) {
        BasRole role = basRoleRepository.findById(id).orElseThrow(
                () -> new ServiceException("角色不存在"));

        if (CollectionUtil.isEmpty(permissionIds)) {
            role.setPermissions(null);
        } else {
            List<BasPermission> permissionList = permissionIds.stream().map(e -> {
                BasPermission permission = new BasPermission();
                permission.setId(e);
                return permission;
            }).collect(Collectors.toList());
            role.setPermissions(permissionList);
        }
        basRoleRepository.save(role);
    }
}
