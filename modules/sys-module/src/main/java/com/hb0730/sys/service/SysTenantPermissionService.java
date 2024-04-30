package com.hb0730.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.basic.domain.entity.BasOrg;
import com.hb0730.basic.domain.entity.BasRolePermission;
import com.hb0730.basic.service.BasOrgService;
import com.hb0730.basic.service.BasRoleService;
import com.hb0730.basic.service.BasUserService;
import com.hb0730.mybatis.core.service.BaseService;
import com.hb0730.query.mybatis.plus.QueryHelper;
import com.hb0730.sys.domain.dto.PermissionDto;
import com.hb0730.sys.domain.dto.PermissionSaveDto;
import com.hb0730.sys.domain.entity.SysTenantPermission;
import com.hb0730.sys.domain.query.PermissionQuery;
import com.hb0730.sys.mapper.SysTenantPermissionMapper;
import com.hb0730.sys.service.mapstruct.SysPermissionMapstruct;
import com.hb0730.sys.service.mapstruct.SysPermissionSaveMapstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysTenantPermissionService extends BaseService<SysTenantPermissionMapper, SysTenantPermission> {
    @Lazy
    @Resource
    private BasOrgService basOrgService;
    @Lazy
    @Resource
    private BasUserService basUserService;
    @Lazy
    @Resource
    private BasRoleService basRoleService;
    private final SysPermissionSaveMapstruct permissionSaveMapstruct;
    private final SysPermissionMapstruct permissionMapstruct;

    /**
     * 查询所有启用的权限
     *
     * @return 权限
     */
    public List<PermissionDto> findAllEnabled() {
        LambdaQueryWrapper<SysTenantPermission> queryWrapper = Wrappers.lambdaQuery(SysTenantPermission.class)
                .eq(SysTenantPermission::getEnabled, true)
                .orderByAsc(SysTenantPermission::getRank);

        List<SysTenantPermission> permissions = list(queryWrapper);
        return permissionMapstruct.tenantToDtoList(permissions);
    }

    /**
     * 根据条件查询菜单与权限
     *
     * @param query .
     * @return .
     */
    public List<PermissionDto> listByQuery(PermissionQuery query) {
        QueryWrapper<SysTenantPermission> queryWrapper = QueryHelper.ofBean(query);
        List<SysTenantPermission> permissions = list(queryWrapper);
        return permissionMapstruct.tenantToDtoList(permissions);
    }

    /**
     * 保存
     *
     * @param dto .
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(PermissionSaveDto dto) {
        SysTenantPermission permission = permissionSaveMapstruct.toTenant(dto);
        save(permission);
    }

    /**
     * 修改
     *
     * @param dto .
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(PermissionSaveDto dto) {
        SysTenantPermission permission = permissionSaveMapstruct.toTenant(dto);
        if (null == permission.getId()) {
            throw new ServiceException("id不能为空");
        }
        SysTenantPermission entity = getById(permission.getId());

        BeanUtil.copyProperties(permission, entity, CopyOptions.create().setIgnoreNullValue(true));
        updateById(entity);
    }

    /**
     * 删除
     *
     * @param id .
     */
    public void deleteById(String id) {
        boolean hasChildren = baseMapper.hasChildren(id);
        if (hasChildren) {
            throw new ServiceException("请先删除子节点");
        }
        removeById(id);
    }

    /**
     * 根据产品ID查询权限
     *
     * @param productId 产品ID
     * @return 权限
     */
    public List<SysTenantPermission> findByProductId(String productId) {
        return baseMapper.findByProductId(productId);
    }


    /**
     * 授权菜单
     *
     * @param productId     产品ID
     * @param permissionIds 权限ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void grantMenus(String productId, List<String> permissionIds) {
        // 查询已经分配的机构
        List<BasOrg> organizations = basOrgService.listByProduct(productId);
        if (CollectionUtil.isEmpty(organizations)) {
            return;
        }
        List<String> sysCodes = organizations.stream().map(BasOrg::getSysCode).toList();
        List<String> orgIds = organizations.stream().map(BasOrg::getId).toList();
        List<SysTenantPermission> permissions = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            permissions = listByIds(permissionIds);
        }
        checkPermission(permissions, orgIds, sysCodes);
    }

    /**
     * 检查权限
     *
     * @param permissions 权限
     * @param orgIds      机构ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void checkPermission(List<SysTenantPermission> permissions, List<String> orgIds, List<String> sysCodes) {
        if (CollectionUtil.isEmpty(orgIds)) {
            return;
        }
        // 根据 商户id 查询商户下的用户信息
//        Set<String> userIds = basUserService.findUserIdsByOrgIds(orgIds);
        // 根据用户id查询角色Id
//        Set<String> roleIds = basUserService.findRoleIdsByUserIds(userIds);
        // 查询商户下所有的角色ID
        Set<String> roleIds = basRoleService.findRoleIdsBySysCodes(sysCodes);

        // 获取所有角色的权限
        List<BasRolePermission> rolePermissions = basRoleService.findRolePermissionsByRoleIds(roleIds);
        // 重合的权限用于重新赋给用户
        List<String> newPerList = new ArrayList<>();
        // 根据 商户id 查询管理员 角色ids
        Set<String> adminRoleIds = basRoleService.findAdminRoleIdsByOrgIds(orgIds);
        // 删除修改后角色下所有的旧权限 并添加新权限集合
        for (String roleId : roleIds) {
            // 是管理员的角色
            if (adminRoleIds.contains(roleId)) {
                // 删除角色与权限关系
                baseMapper.deleteRolePermission(roleId);
                // 添加新权限
                baseMapper.saveRolePermission(roleId, CollectionUtil.listToList(permissions, SysTenantPermission::getId));
            } else {
                // 非管理员角色
                if (CollectionUtil.isNotEmpty(permissions)) {
                    // 循环获取重合权限id
                    for (BasRolePermission rolePermission : rolePermissions) {
                        for (SysTenantPermission permission : permissions) {
                            if (permission.getId().equals(rolePermission.getPermissionId())
                                    && !adminRoleIds.contains(rolePermission.getRoleId())) {
                                if (roleId.equals(rolePermission.getRoleId())) {
                                    newPerList.add(permission.getId());
                                }
                            }
                        }
                    }
                }
                // 删除角色与权限关系
                baseMapper.deleteRolePermission(roleId);
                //重新赋权
                baseMapper.saveRolePermission(roleId, newPerList);
                newPerList.clear();
            }
        }
    }
}
