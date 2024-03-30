package com.hb0730.sys.tenant.service.impl;

import com.hb0730.sys.tenant.domain.TenantUser;
import com.hb0730.sys.tenant.repository.TenantUserRepository;
import com.hb0730.sys.tenant.service.ITenantUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TenantUserServiceImpl implements ITenantUserService {
    private final TenantUserRepository userRepository;

    @Override
    public Set<String> findUserIdsByOrgIds(Collection<String> orgIds) {
        return userRepository.getUserIdsByOrgIdIn(orgIds);
    }

    @Override
    public Set<String> findRoleIdsByIds(Collection<String> userIds) {
        return userRepository.getRoleIdsByIdIn(userIds);
    }

    @Override
    public TenantUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
