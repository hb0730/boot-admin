package com.hb0730.boot.admin.project.system.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.permission.mapper.IPermissionMapper;
import com.hb0730.boot.admin.project.system.permission.model.dto.PermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.permission.model.query.PermissionParams;
import com.hb0730.boot.admin.project.system.permission.service.IPermissionService;
import com.hb0730.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * 权限  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class PermissionServiceImpl extends SuperBaseServiceImpl<Long, PermissionParams, PermissionDTO, PermissionEntity, IPermissionMapper> implements IPermissionService {

    @Override
    @Nonnull
    public QueryWrapper<PermissionEntity> query(@Nonnull PermissionParams params) {
        QueryWrapper<PermissionEntity> query = QueryWrapperUtils.getQuery(params);
        if (Objects.nonNull(params.getMenuId())) {
            query.eq(PermissionEntity.MENU_ID, params.getMenuId());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            query.like(PermissionEntity.NAME, params.getName());
        }
        if (StringUtils.isNotBlank(params.getPermission())) {
            query.eq(PermissionEntity.PERMISSION, params.getPermission());
        }
        return query;
    }
}
