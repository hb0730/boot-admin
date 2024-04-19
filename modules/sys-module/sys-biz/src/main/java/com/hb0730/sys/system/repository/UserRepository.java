package com.hb0730.sys.system.repository;

import com.hb0730.sys.system.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 管理端 用户 Repository
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    SysUser findByUsername(String username);

    /**
     * 修改最后登录时间
     *
     * @param username 用户名
     */
    @Modifying
    @Query("update SysUser u set u.lastLoginTime = ?2 where u.username = ?1")
    void updateLastLoginTimeByUsername(String username, Date lastLoginTime);

    /**
     * 重置密码
     *
     * @param id       用户id
     * @param password 密码
     * @param operator 操作人
     */
    @Modifying
    @Query("update SysUser u set u.password = ?2 , u.lastPwdResetTime = now(),u.modified=now(), u.modifiedBy = ?3 where u.id" +
            " = ?1")
    void resetPwd(Long id, String password, String operator);

    /**
     * 根据菜单id查询用户
     *
     * @param menuId 菜单id
     * @return 用户
     */
    @Query("select u from SysUser u,SysUserRole ur,SysRolePermission rp WHERE rp.permissionId = ?1 AND rp.roleId = ur.roleId " +
            "AND ur.userId = u.id")
    List<SysUser> findByMenuId(Long menuId);

    /**
     * 根据角色id查询用户
     *
     * @param roleId 角色id
     * @return 用户
     */

    List<SysUser> findByRolesId(Long roleId);

    /**
     * 根据角色id删除用户权限
     *
     * @param roleId 角色id
     */
    @Modifying
    @Query("DELETE FROM SysUserRole WHERE roleId = ?1")
    void untiedUserByRoleId(Long roleId);

    /**
     * 根据用户id删除用户权限
     *
     * @param userId 用户id
     */
    @Modifying
    @Query("DELETE FROM SysUserRole WHERE userId = ?1")
    void untiedUserByUserId(Long userId);


    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @param id       需要排除的id
     * @return .
     */
    boolean existsByUsernameAndIdNot(String username, Long id);

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @return .
     */
    boolean existsByUsername(String username);
}
