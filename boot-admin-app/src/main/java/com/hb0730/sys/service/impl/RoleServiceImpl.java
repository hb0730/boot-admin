package com.hb0730.sys.service.impl;

import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.BadRequestException;
import com.hb0730.base.utils.PageUtil;
import com.hb0730.biz.entity.system.Permission;
import com.hb0730.biz.entity.system.Role;
import com.hb0730.sys.bean.RoleQuery;
import com.hb0730.sys.repository.RoleRepository;
import com.hb0730.sys.service.IRoleService;
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
 * @date 2024/2/28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAllEnabled() {
        return roleRepository.findByEnabledIsTrue();
    }

    @Override
    public List<Role> findByUserId(Integer userId) {
        return roleRepository.findByUserId(userId);
    }

    @Override
    public boolean existsByCode(String code, Integer id) {
        if (id == null) {
            return roleRepository.existsByCode(code);
        }
        return roleRepository.existsByCodeAndIdNot(code, id);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void updateById(Role role) {
        if (role.getId() == null) {
            throw new IllegalArgumentException("id不能为空");
        }
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Page<Role> page(RoleQuery query) {
        Specification<Role> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return roleRepository.findAll(specification, page);
    }

    @Override
    public List<Role> list(RoleQuery query) {
        Specification<Role> specification = FenixSpecification.ofBean(query);
        return roleRepository.findAll(specification);
    }


    @Override
    public void assignPermission(Integer roleId, List<Integer> permissionIds) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new BadRequestException("角色不存在"));
        role.setId(roleId);
        List<Permission> permissions = permissionIds.stream().map(permissionId -> {
            Permission permission = new Permission();
            permission.setId(permissionId);
            return permission;
        }).collect(Collectors.toList());
        role.setPermissions(permissions);

        roleRepository.save(role);
    }
}
