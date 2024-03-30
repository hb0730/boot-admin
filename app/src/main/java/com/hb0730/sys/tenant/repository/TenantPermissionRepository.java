package com.hb0730.sys.tenant.repository;

import com.hb0730.sys.tenant.domain.TenantPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Repository
public interface TenantPermissionRepository extends JpaRepository<TenantPermission, Long>, JpaSpecificationExecutor<TenantPermission> {

    /**
     * 根据角色id查询权限
     *
     * @param roleIds 角色id
     * @return 权限
     */
    @Query("SELECT p FROM TenantPermission p, TenantRolePermission rp WHERE p.id = rp.permissionId AND rp.roleId IN ?1")
    List<TenantPermission> findByRoleIdIn(List<String> roleIds);

    /**
     * 根据角色id删除权限
     *
     * @param roleId 角色id
     */
    @Modifying
    @Query("DELETE FROM TenantRolePermission rp WHERE rp.roleId = ?1")
    void deleteByRoleId(String roleId);

    /**
     * 根据角色id查询菜单与权限
     *
     * @param roleIds 角色id
     * @return 菜单与权限
     */
    @Query("select p from TenantPermission p join TenantRolePermission rp on p.id = rp.permissionId where rp.roleId " +
            "in ?1 AND " +
            "p.enabled order by p.rank")
    List<TenantPermission> findByRoleIdsOrderByRank(List<Integer> roleIds);


    /**
     * 查询已启用的权限,根据rank排序
     *
     * @return .
     */
    List<TenantPermission> findByEnabledIsTrueOrderByRank();

    /**
     * 是否还有子类
     *
     * @param id .
     * @return .
     */
    boolean existsByParentId(Long id);

    /**
     * 根据产品ID查询权限
     *
     * @param productId 产品ID
     * @return 权限
     */
    @Query("SELECT p from TenantPermission p, SysProductPermission pp WHERE p.id = pp.permissionId AND pp.productId = ?1 ORDER BY p" +
            ".rank")
    List<TenantPermission> findByProductId(Long productId);
}
