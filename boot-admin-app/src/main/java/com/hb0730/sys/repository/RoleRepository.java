package com.hb0730.sys.repository;

import com.hb0730.biz.entity.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色-Repository
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    /**
     * 查询所有启用的角色
     *
     * @return 角色
     */
    List<Role> findByEnabledIsTrue();

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 角色
     */
    @Query("select r from Role r join UserRole ur on r.id = ur.roleId where ur.userId = ?1 and r.enabled")
    List<Role> findByUserId(Integer userId);


    /**
     * 根据菜单id查询角色
     *
     * @param menuId 菜单id
     * @return 角色
     */
    @Query("SELECT r FROM Role r,RolePermission rp WHERE rp.permissionId = ?1 AND rp.roleId = r.id")
    List<Role> findByMenuId(String menuId);

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   角色ID
     * @return .
     */
    boolean existsByCodeAndIdNot(String code, Integer id);

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
    @Query("DELETE FROM RolePermission WHERE permissionId = ?1")
    void untiedMenuByMenuId(Integer menuId);

    /**
     * 根据角色id删除角色权限
     *
     * @param roleId 角色id
     */
    @Modifying
    @Query("DELETE FROM RolePermission WHERE roleId = ?1")
    void untiedMenuByRoleId(Integer roleId);


    /**
     * 根据角色id删除角色权限
     *
     * @param roleId 角色id
     */
    @Modifying
    @Query("DELETE FROM RolePermission WHERE roleId = ?1")
    void clearRolePermissionByRoleId(Integer roleId);
}
