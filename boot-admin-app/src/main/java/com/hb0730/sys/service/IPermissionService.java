package com.hb0730.sys.service;

import com.hb0730.biz.entity.system.Permission;
import com.hb0730.sys.bean.PermissionQuery;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
public interface IPermissionService {
    /**
     * 查询所有已启用的菜单与权限
     *
     * @return 菜单与权限
     */
    List<Permission> findByEnabledTrueOrderByRank();

    /**
     * 根据角色id查询菜单与权限
     *
     * @param roleIds 角色id
     * @return 菜单与权限
     */
    List<Permission> findByRoleIds(List<Integer> roleIds);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<Permission> listDefaultRootQueryOrderRank(PermissionQuery query);


    /**
     * 根据id查询
     *
     * @param id id
     * @return 权限
     */
    Permission findById(Integer id);

    /**
     * 添加
     *
     * @param permission 权限
     */
    void add(Permission permission);

    /**
     * 修改
     *
     * @param permission 权限
     */
    void updateById(Permission permission);

    /**
     * 删除
     *
     * @param id 权限id
     */
    void delete(Integer id);

    /**
     * 是否有子级
     *
     * @param id id
     * @return true/false
     */
    boolean hasChildren(Integer id);
}
