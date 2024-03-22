package com.hb0730.sys.service;

import com.hb0730.biz.entity.system.User;
import com.hb0730.sys.bean.UserQuery;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
public interface IUserService {

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @param id       需要排除的ID
     * @return 是否存在
     */
    Boolean existsByUsernameAndIdNot(String username, @Nullable Integer id);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页
     */
    Page<User> page(UserQuery query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 分页
     */
    List<User> list(UserQuery query);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByUsername(String username);

    /**
     * 修改最后登录时间
     *
     * @param username 用户名
     */
    void changeLastLoginTimeByUsername(String username);

    /**
     * 根据权限ID获取用户
     *
     * @param menuId 权限ID
     * @return 用户
     */
    List<User> findByMenuId(Integer menuId);

    /**
     * 根据角色ID查询用户
     *
     * @param roleId 角色ID
     * @return 用户
     */
    List<User> findByRoleId(Integer roleId);

    /**
     * 保存
     *
     * @param user 用户
     */
    void save(User user);

    /**
     * 根据ID更新
     *
     * @param user .
     */
    void updateById(User user);

    /**
     * 根据ID删除
     *
     * @param id .
     */
    void deleteById(Integer id);

    /**
     * 重置密码
     *
     * @param id       用户ID
     * @param password 密码
     * @param operator 操作人
     */
    void restPassword(Integer id, String password, String operator);
}
