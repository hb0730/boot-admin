package com.hb0730.sys.tenant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.rpc.sys.tenant.domain.query.PermissionQuery;
import com.hb0730.sys.tenant.domain.TenantOrg;
import com.hb0730.sys.tenant.domain.TenantPermission;
import com.hb0730.sys.tenant.domain.TenantRole;
import com.hb0730.sys.tenant.domain.TenantRolePermission;
import com.hb0730.sys.tenant.repository.TenantPermissionRepository;
import com.hb0730.sys.tenant.service.ITenantOrgService;
import com.hb0730.sys.tenant.service.ITenantPermissionService;
import com.hb0730.sys.tenant.service.ITenantRoleService;
import com.hb0730.sys.tenant.service.ITenantUserService;
import com.hb0730.util.QueryHelper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TenantPermissionServiceImpl implements ITenantPermissionService {
    private final TenantPermissionRepository permissionRepository;
    @Lazy
    @Resource
    private ITenantOrgService organizationService;
    /**
     * 用户服务
     */
    @Lazy
    @Resource
    private ITenantUserService basUserService;

    @Lazy
    @Resource
    private ITenantRoleService roleService;

    @Override
    public List<TenantPermission> listDefaultRootQueryOrderRank(PermissionQuery query) {
        QueryHelper.setFieldNull(query, Map.of("parentIdIsNull", "parentId"), "size", "current", "parentIdIsNull",
                "sorts");
        Specification<TenantPermission> specification = FenixSpecification.ofBean(query);
        List<Sort.Order> orders = query.getSorts().orElse(List.of(Sort.Order.asc("rank")));
        return permissionRepository.findAll(specification, Sort.by(orders));
    }

    @Override
    public List<TenantPermission> findByEnabledTrueOrderByRank() {
        return permissionRepository.findByEnabledIsTrueOrderByRank();
    }

    @Override
    public List<TenantPermission> findByProductId(Long productId) {
        return permissionRepository.findByProductId(productId);
    }

    @Override
    public void add(TenantPermission permission) {
        Long parentId = permission.getParentId();

        // 如果父级id为0，则设置为null
        if (Long.valueOf(0).equals(parentId)) {
            permission.setParentId(null);
        }

        permissionRepository.save(permission);
    }

    @Override
    public void updateById(TenantPermission permission) {
        if (null == permission.getId()) {
            throw new ServiceException("id不能为空");
        }
        if (permission.getId().equals(permission.getParentId())) {
            throw new ServiceException("父级不能为自己");
        }
        // 如果父级id为0，则设置为null
        if (Long.valueOf(0).equals(permission.getParentId())) {
            permission.setParentId(null);
        }
        TenantPermission _permission = permissionRepository.findById(permission.getId()).orElseThrow(() -> new ServiceException("权限不存在"));
        // 保证相关数据不会被修改
        BeanUtil.copyProperties(permission, _permission, CopyOptions.create().ignoreNullValue());
        permissionRepository.save(_permission);
    }

    @Override
    public void deleteById(Long id) {
//        roleRepository.untiedMenuByMenuId(id);
        permissionRepository.deleteById(id);
    }

    @Override
    public boolean hasChildren(Long id) {
        return permissionRepository.existsByParentId(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantMenus(Long productId, List<Long> permissionIds) {
        // 查询有此产品的机构
        List<TenantOrg> organizations = organizationService.findByProductId(productId);
        if (organizations.isEmpty()) {
            return;
        }
        List<String> orgIds = organizations.stream().map(TenantOrg::getId).toList();
        List<TenantPermission> permissions = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            permissions = permissionRepository.findAllById(permissionIds);
        }
        this.checkPermission(permissions, orgIds);
        // 查询由此产品的角色
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkPermission(List<TenantPermission> permissions, List<String> orgIds) {
        if (CollectionUtil.isEmpty(orgIds)) {
            return;
        }
        // 根据 商户id 查询商户下的用户信息
        Set<String> userIds = basUserService.findUserIdsByOrgIds(orgIds);
        // 用户信息ID 获取 角色ID集合
        Set<String> roleIds = basUserService.findRoleIdsByIds(userIds);
        // 获取所有角色的权限
        List<TenantRolePermission> rolePermissions = roleService.findRolePermissionByRoleIdIn(roleIds);
        // 重合的权限用于重新赋给用户
        List<Long> newPerList = new ArrayList<>();
        // 根据 商户id 查询管理员 角色ids
        Set<String> adminRoleIds = roleService.getSystemRoleIdsByOrgIds(orgIds);
        // 删除修改后角色下所有的旧权限 并添加新权限集合
        for (String roleId : roleIds) {
            // 是管理员的角色
            if (adminRoleIds.contains(roleId)) {
                // 删除角色与权限关系
                permissionRepository.deleteByRoleId(roleId);
                // 添加新权限
                TenantRole tenantRole = getAdminRolePermission(permissions, roleId);
                roleService.saveRolePermission(tenantRole);
            } else {
                // 非管理员角色

                if (CollectionUtil.isNotEmpty(permissions)) {
                    // 循环获取重合权限id
                    for (TenantRolePermission rolePermission : rolePermissions) {
                        for (TenantPermission permission : permissions) {
                            if (permission.getId().equals(rolePermission.getPermissionId())
                                    && !adminRoleIds.contains(rolePermission.getRoleId())) {
                                if (roleId.equals(rolePermission.getRoleId())) {
                                    newPerList.add(permission.getId());
                                }
                            }
                        }
                    }
                }
                //重新赋权
                TenantRole tenantRole = getRolePermission(newPerList, roleId);
                roleService.saveRolePermission(tenantRole);
                newPerList.clear();
            }
        }
    }

    /**
     * 组装权限
     *
     * @param newPerList 新权限
     * @param roleId     角色
     * @return 角色权限
     */
    private TenantRole getAdminRolePermission(List<TenantPermission> newPerList, String roleId) {
        TenantRole tenantRole = roleService.findById(roleId).orElseThrow(() -> new ServiceException("角色不存在"));
        tenantRole.setPermissions(newPerList);
        return tenantRole;
    }

    private TenantRole getRolePermission(List<Long> newPerList, String roleId) {
        TenantRole tenantRole = roleService.findById(roleId).orElseThrow(() -> new ServiceException("角色不存在"));
        List<TenantPermission> permissions = new ArrayList<>(newPerList.size());
        for (Long permissionId : newPerList) {
            TenantPermission permission = new TenantPermission();
            permission.setId(permissionId);
            permissions.add(permission);
        }
        tenantRole.setPermissions(permissions);
        return tenantRole;
    }
}
