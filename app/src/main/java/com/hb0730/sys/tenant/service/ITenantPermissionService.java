package com.hb0730.sys.tenant.service;

import com.hb0730.rpc.sys.tenant.domain.query.PermissionQuery;
import com.hb0730.sys.tenant.domain.TenantPermission;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
public interface ITenantPermissionService {
    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<TenantPermission> listDefaultRootQueryOrderRank(PermissionQuery query);

    /**
     * 查询已启用的权限
     *
     * @return .
     */
    List<TenantPermission> findByEnabledTrueOrderByRank();

    /**
     * 查询产品对于的权限
     *
     * @param productId .
     * @return .
     */
    List<TenantPermission> findByProductId(Long productId);

    /**
     * 新增权限
     *
     * @param permission .
     */
    void add(TenantPermission permission);

    /**
     * 根据ID修改
     *
     * @param permission .
     */
    void updateById(TenantPermission permission);

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

    /**
     * 授权菜单
     *
     * @param productId     产品id
     * @param permissionIds 权限id
     */
    void grantMenus(Long productId, List<Long> permissionIds);

    /**
     * 检查权限
     *
     * @param permissions 权限,最新产品的权限
     * @param orgIds      涉及的商户
     */
    void checkPermission(List<TenantPermission> permissions, List<String> orgIds);
}
