package com.hb0730.sys.tenant.repository;

import com.hb0730.sys.tenant.domain.TenantRole;
import com.hb0730.sys.tenant.domain.TenantRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Repository
public interface TenantRoleRepository extends JpaRepository<TenantRole, String>, JpaSpecificationExecutor<TenantRole> {

    /**
     * 根据角色ID查询角色与权限的映射关系
     *
     * @param roleIds 角色id
     * @return 角色与权限的映射关系
     */
    @Query("select rp from TenantRolePermission rp where rp.roleId in ?1")
    List<TenantRolePermission> findRolePermissionByRoleIdIn(Collection<String> roleIds);


    /**
     * 根据组织id查询系统角色id
     *
     * @param orgIds 组织id
     * @return 系统角色id
     */
    @Query("select r.id from TenantRole r where r.system = true and r.org.id in ?1")
    Set<String> getRoleIdsBySystemIsTrueAndOrgIdIn(Collection<String> orgIds);
}
