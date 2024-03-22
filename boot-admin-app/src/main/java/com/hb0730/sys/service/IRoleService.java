package com.hb0730.sys.service;

import com.hb0730.biz.entity.system.Role;
import com.hb0730.sys.bean.RoleQuery;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/28
 */
public interface IRoleService {
    /**
     * 查询所有启用的角色
     *
     * @return 角色
     */
    List<Role> findAllEnabled();

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 角色
     */
    List<Role> findByUserId(Integer userId);

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   角色ID
     * @return .
     */
    boolean existsByCode(String code, @Nullable Integer id);

    /**
     * 保存
     *
     * @param role 角色
     */
    void save(Role role);

    /**
     * 更新角色
     *
     * @param role .
     */
    void updateById(Role role);

    /**
     * 根据ID删除
     *
     * @param id .
     */
    void deleteById(Integer id);


    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return .
     */
    Page<Role> page(RoleQuery query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return .
     */
    List<Role> list(RoleQuery query);


    /**
     * 赋予菜单与权限
     *
     * @param roleId        角色ID
     * @param permissionIds 菜单与权限ID
     */
    void assignPermission(Integer roleId, List<Integer> permissionIds);
}
