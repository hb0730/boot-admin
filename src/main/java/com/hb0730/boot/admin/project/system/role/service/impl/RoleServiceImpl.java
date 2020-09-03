package com.hb0730.boot.admin.project.system.role.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.role.mapper.IRoleMapper;
import com.hb0730.boot.admin.project.system.role.model.dto.RoleDTO;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleEntity;
import com.hb0730.boot.admin.project.system.role.model.query.RoleParams;
import com.hb0730.boot.admin.project.system.role.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class RoleServiceImpl extends SuperBaseServiceImpl<Long, RoleParams, RoleDTO, RoleEntity, IRoleMapper> implements IRoleService {

}
