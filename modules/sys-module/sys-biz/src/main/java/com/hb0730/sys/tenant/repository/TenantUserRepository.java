package com.hb0730.sys.tenant.repository;

import com.hb0730.sys.tenant.domain.TenantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Repository
public interface TenantUserRepository extends JpaRepository<TenantUser, String>, JpaSpecificationExecutor<TenantUser> {

    /**
     * 根据组织id查询用户id
     *
     * @param orgIds 组织id
     * @return 用户id
     */
    @Query("select u.id from TenantUser u where u.org.id in ?1")
    Set<String> getUserIdsByOrgIdIn(Collection<String> orgIds);

    /**
     * 根据用户id查询角色id
     *
     * @param userIds 用户id
     * @return 角色id
     */
    @Query("select r.id from TenantUser u join u.roles r where u.id in ?1")
    Set<String> getRoleIdsByIdIn(Collection<String> userIds);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    TenantUser findByUsername(String username);
}
