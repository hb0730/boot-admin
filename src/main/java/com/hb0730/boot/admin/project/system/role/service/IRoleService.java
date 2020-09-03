package com.hb0730.boot.admin.project.system.role.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.role.model.dto.RoleDTO;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleEntity;
import com.hb0730.boot.admin.project.system.role.model.query.RoleParams;

/**
 * 角色  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IRoleService extends IBaseService<Long, RoleParams, RoleDTO, RoleEntity> {

}
