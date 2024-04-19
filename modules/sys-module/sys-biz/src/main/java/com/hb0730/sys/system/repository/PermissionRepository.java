package com.hb0730.sys.system.repository;

import com.hb0730.sys.system.domain.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限 Repository
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Repository
public interface PermissionRepository extends JpaRepository<SysPermission, Long>, JpaSpecificationExecutor<SysPermission> {

    /**
     * 根据角色获取权限
     *
     * @param roleIds .
     * @return .
     */
    @Query("SELECT p FROM SysPermission p JOIN p.roles r WHERE r.id IN ?1 ORDER BY p.rank")
    List<SysPermission> findByRoleIdsIn(List<Long> roleIds);

    /**
     * 根据角色id查询菜单与权限
     *
     * @param roleIds 角色id
     * @return 菜单与权限
     */
    @Query("select p from SysPermission p join SysRolePermission rp on p.id = rp.permissionId where rp.roleId in ?1 AND " +
            "p.enabled order by p.rank")
    List<SysPermission> findByRoleIdsOrderByRank(List<Integer> roleIds);


    /**
     * 查询已启用的权限,根据rank排序
     *
     * @return .
     */
    List<SysPermission> findByEnabledIsTrueOrderByRank();

    /**
     * 是否还有子类
     *
     * @param id .
     * @return .
     */
    boolean existsByParentId(Long id);
}
