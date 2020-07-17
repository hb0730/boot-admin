package com.hb0730.boot.admin.project.system.menu.permission.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.menu.permission.model.entity.SystemMenuPermissionEntity;
import com.hb0730.boot.admin.project.system.menu.permission.model.vo.PermissionParams;
import com.hb0730.boot.admin.project.system.permission.model.vo.SystemPermissionVO;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 菜单权限  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
public interface ISystemMenuPermissionService extends IBaseService<Long, PermissionParams, SystemPermissionVO, SystemMenuPermissionEntity> {

    /**
     * <p>
     * 根据菜单id获取权限信息
     * </p>
     *
     * @param menuId 菜单id
     * @return 权限信息
     */
    List<SystemPermissionVO> getPermissionByMenuId(@NonNull Long menuId);


    /**
     * <p>
     * 根据菜单id获取权限信息(分页)
     * </p>
     *
     * @param menuId 菜单id
     * @param params 过滤条件
     * @return 是否成功
     */
    Page<SystemPermissionVO> getPermissionByMenuId(@NonNull Long menuId, PermissionParams params);

    /**
     * <p>
     * 保存权限
     * </p>
     *
     * @param menuId         菜单id
     * @param permissionInfo 权限info
     * @return 是否成功
     */
    boolean save(@NonNull Long menuId, SystemPermissionVO permissionInfo);

    /**
     * <p>
     * 根据权限id修改权限信息
     * </p>
     *
     * @param permissionId 权限id
     * @param permissionVO 权限信息
     * @return 是否成功
     */
    boolean updatePermissionById(@NonNull Long permissionId, SystemPermissionVO permissionVO);

    /**
     * 根据权限id删除
     *
     * @param permissionId 权限id
     * @return 是否成功
     */
    boolean deleteByPermissionId(@NonNull Long permissionId);

    /**
     * 获取根据菜单id获取id
     *
     * @return 键值对方式的menu, permission
     */
    Map<Long, Set<Long>> getPermissionIdByMenuId();

    /**
     * <p>
     * 根据权限id获取菜单权限信息
     * </p>
     *
     * @param permissionIds 权限id
     * @return 菜单权限信息
     */
    List<SystemMenuPermissionEntity> getMenuPermissionByPermissionIds(Collection<Long> permissionIds);
}
