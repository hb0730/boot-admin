package com.hb0730.sys.tenant.service.impl;

import com.hb0730.sys.tenant.domain.TenantRole;
import com.hb0730.sys.tenant.domain.TenantRolePermission;
import com.hb0730.sys.tenant.repository.TenantRoleRepository;
import com.hb0730.sys.tenant.service.ITenantRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TenantRoleServiceImpl implements ITenantRoleService {
    private final TenantRoleRepository roleRepository;

    @Override
    public List<TenantRolePermission> findRolePermissionByRoleIdIn(Collection<String> roleIds) {
        return roleRepository.findRolePermissionByRoleIdIn(roleIds);
    }

    @Override
    public Set<String> getSystemRoleIdsByOrgIds(Collection<String> orgIds) {
        return roleRepository.getRoleIdsBySystemIsTrueAndOrgIdIn(orgIds);
    }

    @Override
    public Optional<TenantRole> findById(String roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public void saveRolePermission(TenantRole tenantRole) {
        roleRepository.save(tenantRole);
    }
}
