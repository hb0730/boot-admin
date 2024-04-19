package com.hb0730.sys.tenant.service;

import com.hb0730.sys.tenant.domain.TenantUser;

import java.util.Collection;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
public interface ITenantUserService {

    /**
     * 根据组织id查询用户id
     *
     * @param orgIds 组织id
     * @return 用户id
     */
    Set<String> findUserIdsByOrgIds(Collection<String> orgIds);

    /**
     * 根据用户id查询角色id
     *
     * @param userIds 用户id
     * @return 角色id
     */
    Set<String> findRoleIdsByIds(Collection<String> userIds);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    TenantUser findByUsername(String username);
}
