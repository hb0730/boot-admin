package com.hb0730.boot.admin.project.system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.domain.service.SuperBaseService;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.system.role.mapper.ISystemRoleMapper;
import com.hb0730.boot.admin.project.system.role.model.entity.SystemRoleEntity;
import com.hb0730.boot.admin.project.system.role.model.vo.RoleParams;
import com.hb0730.boot.admin.project.system.role.model.vo.SystemRoleVO;
import com.hb0730.boot.admin.project.system.role.service.ISystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统角色  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
@Service
public class SystemRoleServiceImpl extends SuperBaseService<RoleParams, SystemRoleVO, ISystemRoleMapper, SystemRoleEntity> implements ISystemRoleService {

    @Override
    @NonNull
    public QueryWrapper<SystemRoleEntity> query(@NonNull RoleParams params) {
        QueryWrapper<SystemRoleEntity> query = QueryWrapperUtils.getQuery(params);
        if (StringUtils.isNotBlank(params.getEnname())) {
            query.like(SystemRoleEntity.ENNAME, params.getEnname());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            query.like(SystemRoleEntity.NAME, params.getName());
        }
        if (Objects.nonNull(params.getIsEnabled())) {
            query.eq(SystemRoleEntity.IS_ENABLED, params.getIsEnabled());
        } else if (!Objects.isNull(params.getIsAll()) && !Objects.equals(params.getIsAll(), SystemConstants.IS_ALL)) {
            query.eq(SystemRoleEntity.IS_ENABLED, SystemConstants.ENABLED);
        }
        return query;
    }

    @Override
    public List<SystemRoleVO> list(@NonNull RoleParams params) {
        QueryWrapper<SystemRoleEntity> query = query(params);
        List<SystemRoleEntity> entities = super.list(query);
        return BeanUtils.transformFromInBatch(entities, SystemRoleVO.class);
    }

    @Override
    public Page<SystemRoleVO> page(@NonNull RoleParams params) {
        QueryWrapper<SystemRoleEntity> query = query(params);
        Page<SystemRoleEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, SystemRoleVO.class);
    }
}
