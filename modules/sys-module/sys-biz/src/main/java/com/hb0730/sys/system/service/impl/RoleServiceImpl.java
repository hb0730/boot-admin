package com.hb0730.sys.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.common.util.PageUtil;
import com.hb0730.jpa.specification.SpecificationUtil;
import com.hb0730.rpc.sys.system.domain.query.RoleQuery;
import com.hb0730.sys.system.domain.SysPermission;
import com.hb0730.sys.system.domain.SysRole;
import com.hb0730.sys.system.repository.RoleRepository;
import com.hb0730.sys.system.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<SysRole> getByUserId(Long userId) {
        return roleRepository.findByUserId(userId);
    }

    @Override
    public List<SysRole> findAllEnabled() {
        return roleRepository.findByEnabledIsTrue();
    }

    @Override
    public List<SysRole> findByUserId(Long userId) {
        return roleRepository.findByUserId(userId);
    }

    @Override
    public boolean existsByCode(String code, Long id) {
        if (id == null) {
            return roleRepository.existsByCode(code);
        }
        return roleRepository.existsByCodeAndIdNot(code, id);
    }

    @Override
    public void save(SysRole role) {
        roleRepository.save(role);
    }

    @Override
    public void updateById(SysRole role) {
        if (role.getId() == null) {
            throw new IllegalArgumentException("id不能为空");
        }
        SysRole _role = roleRepository.findById(role.getId()).orElseThrow(() -> new ServiceException("角色不存在"));
        // 更新
        BeanUtil.copyProperties(role, _role, CopyOptions.create().ignoreNullValue());
        roleRepository.save(_role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Page<SysRole> page(RoleQuery query) {
        Specification<SysRole> specification = SpecificationUtil.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return roleRepository.findAll(specification, page);
    }

    @Override
    public List<SysRole> list(RoleQuery query) {
        Specification<SysRole> specification = SpecificationUtil.ofBean(query);
        return roleRepository.findAll(specification);
    }


    @Override
    public void assignPermission(Long roleId, List<Long> permissionIds) {
        SysRole role = roleRepository.findById(roleId).orElseThrow(() -> new ServiceException("角色不存在"));
        role.setId(roleId);
        List<SysPermission> permissions = permissionIds.stream().map(permissionId -> {
            SysPermission permission = new SysPermission();
            permission.setId(permissionId);
            return permission;
        }).collect(Collectors.toList());
        role.setPermissions(permissions);

        roleRepository.save(role);
    }
}
