package com.hb0730.boot.admin.project.system.permission.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.permission.model.dto.PermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.permission.model.query.PermissionParams;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 权限  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IPermissionService extends IBaseService<Long, PermissionParams, PermissionDTO, PermissionEntity> {

    /**
     * 根据菜单id获取对应的权限
     *
     * @param menuId   菜单id
     * @param pageNum     页数
     * @param pageSize 数量
     * @return 权限信息
     */
    Page<PermissionDTO> getPermissionByMenuId(@Nonnull Long menuId, Long pageNum, Long pageSize);

    /**
     * 根据菜单id获取对应的权限
     *
     * @param menuId 菜单id
     * @return 权限信息
     */
    List<PermissionDTO> getPermissionByMenuId(@Nonnull Long menuId);
}
