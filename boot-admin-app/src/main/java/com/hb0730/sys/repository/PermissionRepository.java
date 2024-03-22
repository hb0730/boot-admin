package com.hb0730.sys.repository;

import com.hb0730.biz.entity.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单与权限-Repository
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

    /**
     * 查询所有已启用的菜单与权限
     *
     * @return 菜单与权限
     */
    List<Permission> findByEnabledIsTrueOrderByRank();

    /**
     * 根据角色id查询菜单与权限
     *
     * @param roleIds 角色id
     * @return 菜单与权限
     */
    @Query("select p from Permission p join RolePermission rp on p.id = rp.permissionId where rp.roleId in ?1 AND " +
            "p.enabled order by p.rank")
    List<Permission> findByRoleIdsOrderByRank(List<Integer> roleIds);

    /**
     * 根据父id查询是否存在
     *
     * @param parentId 父id
     * @return 是否存在
     */
    boolean existsByParentId(Integer parentId);
}
