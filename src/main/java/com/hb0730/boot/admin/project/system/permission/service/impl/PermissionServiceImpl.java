package com.hb0730.boot.admin.project.system.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.permission.mapper.IPermissionMapper;
import com.hb0730.boot.admin.project.system.permission.model.dto.PermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.permission.model.query.PermissionParams;
import com.hb0730.boot.admin.project.system.permission.service.IPermissionService;
import com.hb0730.commons.spring.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 权限  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class PermissionServiceImpl extends SuperBaseServiceImpl<Long, PermissionParams, PermissionDTO, PermissionEntity, IPermissionMapper> implements IPermissionService {

    @Override
    public Page<PermissionDTO> getPermissionByMenuId(@Nonnull Long menuId, Long pageNum, Long pageSize) {
        Assert.notNull(menuId, "菜单id不为空" );
        PermissionParams params = new PermissionParams();
        params.setPageNum(pageNum);
        params.setPageSize(pageSize);
        QueryWrapper<PermissionEntity> query = super.query(params);
        query.eq(PermissionEntity.MENU_ID, menuId);
        return super.page(params);
    }

    @Override
    public List<PermissionDTO> getPermissionByMenuId(@Nonnull Long menuId) {
        Assert.notNull(menuId, "菜单id不为空" );
        LambdaQueryWrapper<PermissionEntity> query = Wrappers.lambdaQuery(PermissionEntity.class)
                .eq(PermissionEntity::getMenuId, menuId);
        List<PermissionEntity> entities = super.list(query);
        return BeanUtils.transformFromInBatch(entities, PermissionDTO.class);
    }
}
