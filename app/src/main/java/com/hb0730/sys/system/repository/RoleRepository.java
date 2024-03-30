package com.hb0730.sys.system.repository;

import com.hb0730.sys.system.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色 Repository
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Repository
public interface RoleRepository extends JpaRepository<SysRole, Long>, JpaSpecificationExecutor<SysRole> {

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 角色
     */
    @Query("select r from SysRole r join SysUserRole ur on r.id = ur.roleId where ur.userId = ?1 and r.enabled")
    List<SysRole> findByUserId(Long userId);

    /**
     * 查询所有启用的角色
     *
     * @return 角色
     */
    List<SysRole> findByEnabledIsTrue();


    /**
     * 根据菜单id查询角色
     *
     * @param menuId 菜单id
     * @return 角色
     */
    @Query("SELECT r FROM SysRole r,SysRolePermission rp WHERE rp.permissionId = ?1 AND rp.roleId = r.id")
    List<SysRole> findByMenuId(String menuId);

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   角色ID
     * @return .
     */
    boolean existsByCodeAndIdNot(String code, Long id);

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @return .
     */
    boolean existsByCode(String code);

    /**
     * 根据菜单id删除角色权限
     *
     * @param menuId 菜单id
     */
    @Modifying
    @Query("DELETE FROM SysRolePermission WHERE permissionId = ?1")
    void untiedMenuByMenuId(Long menuId);

    /**
     * 根据角色id删除角色权限
     *
     * @param roleId 角色id
     */
    @Modifying
    @Query("DELETE FROM SysRolePermission WHERE roleId = ?1")
    void untiedMenuByRoleId(Long roleId);


    /**
     * 根据角色id删除角色权限
     *
     * @param roleId 角色id
     */
    @Modifying
    @Query("DELETE FROM SysRolePermission WHERE roleId = ?1")
    void clearRolePermissionByRoleId(Long roleId);
}
