package com.hb0730.sys.system.service;

import com.hb0730.rpc.sys.system.domain.query.UserQuery;
import com.hb0730.sys.system.domain.SysUser;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
public interface IUserService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    SysUser findByUsername(String username);

    /**
     * 账号是否存在
     *
     * @param username 用户名
     * @param id       需要排除的ID
     * @return .
     */
    Boolean existsByUsernameAndIdNot(String username, @Nullable Long id);

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    Page<SysUser> page(UserQuery query);

    /**
     * 查询用户
     *
     * @param query .
     * @return .
     */
    List<SysUser> list(UserQuery query);

    /**
     * 更新最后登录时间
     *
     * @param username .
     */
    void changeLastLoginTimeByUsername(String username);

    /**
     * 根据权限查询
     *
     * @param menuId .
     * @return .
     */
    List<SysUser> findByMenuId(Long menuId);

    /**
     * 根据角色查询
     *
     * @param roleId .
     * @return .
     */
    List<SysUser> findByRoleId(Long roleId);

    /**
     * 新增用户
     *
     * @param user .
     */
    void save(SysUser user);

    /**
     * 根据ID更新
     *
     * @param user .
     */
    void updateById(SysUser user);

    /**
     * 根据ID删除
     *
     * @param id .
     */
    void deleteById(Long id);

    /**
     * 重置密码
     *
     * @param id       用户ID
     * @param password 密码
     * @param operator 操作者
     */
    void restPassword(Long id, String password, String operator);
}
