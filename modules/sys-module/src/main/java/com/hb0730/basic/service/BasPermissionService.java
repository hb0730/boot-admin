package com.hb0730.basic.service;

import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.basic.domain.dto.BasPermissionDto;
import com.hb0730.basic.domain.entity.BasOrg;
import com.hb0730.basic.domain.entity.BasPermission;
import com.hb0730.basic.domain.entity.BasRole;
import com.hb0730.basic.mapper.BasPermissionMapper;
import com.hb0730.basic.service.mapstruct.BasPermissionMapstruct;
import com.hb0730.mybatis.core.service.BaseService;
import com.hb0730.sys.domain.dto.PermissionDto;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasPermissionService extends BaseService<BasPermissionMapper, BasPermission> {
    @Lazy
    @Resource
    private BasRoleService basRoleService;
    @Lazy
    @Resource
    private BasOrgService basOrgService;
    private final BasPermissionMapstruct mapstruct;

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    public List<BasPermission> findByRoleIds(List<String> roleIds) {
        return baseMapper.listByRoleIds(roleIds);
    }

    /**
     * 用户路由
     *
     * @param userId .
     * @return .
     */
    public List<PermissionDto> userRoutes(String userId) {
        List<BasRole> roles = basRoleService.findByUserId(userId);
        if (CollectionUtil.isEmpty(roles)) {
            return null;
        }
        List<String> roleIds = CollectionUtil.listToList(roles, BasRole::getId);
        List<BasPermission> basPermissions = findByRoleIds(roleIds);
        return mapstruct.toPermissionDtoList(basPermissions);
    }

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    public List<BasPermission> listByRoleIds(List<String> roleIds) {
        return baseMapper.listByRoleIds(roleIds);
    }

    /**
     * 根据用户id查询权限
     *
     * @param userId 用户id
     * @return 权限
     */
    public List<BasPermission> listByUserId(String userId) {
        List<BasRole> roles = basRoleService.findByUserId(userId);
        if (CollectionUtil.isEmpty(roles)) {
            return null;
        }
        List<String> roleIds = CollectionUtil.listToList(roles, BasRole::getId);
        return findByRoleIds(roleIds);
    }

    /**
     * 根据产品ID获取权限
     *
     * @param productId 产品ID
     * @return 权限
     */
    public List<BasPermission> findByProductId(String productId) {
        return baseMapper.findByProductId(productId);
    }


    /**
     * 根据产品ID获取权限
     *
     * @param sysCode 产品ID
     * @return 权限
     */
    public List<BasPermissionDto> list(String sysCode) {
        BasOrg org = basOrgService.getTopOrg(sysCode);
        if (org == null) {
            return null;
        }
        List<BasPermission> res = baseMapper.findByProductId(org.getProductId());
        return mapstruct.toDtoList(res);
    }


}
