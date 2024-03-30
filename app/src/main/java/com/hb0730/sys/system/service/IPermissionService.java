package com.hb0730.sys.system.service;

import com.hb0730.rpc.sys.system.domain.query.PermissionQuery;
import com.hb0730.sys.system.domain.SysPermission;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
public interface IPermissionService {

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds .
     * @return .
     */
    List<SysPermission> getByRoleIdsIn(List<Long> roleIds);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<SysPermission> listDefaultRootQueryOrderRank(PermissionQuery query);

    /**
     * 查询已启用的权限
     *
     * @return .
     */
    List<SysPermission> findByEnabledTrueOrderByRank();

    /**
     * 新增权限
     *
     * @param permission .
     */
    void add(SysPermission permission);

    /**
     * 根据ID修改
     *
     * @param permission .
     */
    void updateById(SysPermission permission);

    /**
     * 根据ID删除
     *
     * @param id .
     */
    void deleteById(Long id);

    /**
     * 是否还有子类
     *
     * @param id .
     * @return .
     */
    boolean hasChildren(Long id);
}
