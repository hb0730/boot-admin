package com.hb0730.boot.admin.project.system.permission.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.permission.model.dto.PermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.permission.model.query.PermissionParams;

/**
 * 权限  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IPermissionService extends IBaseService<Long, PermissionParams, PermissionDTO, PermissionEntity> {
}
