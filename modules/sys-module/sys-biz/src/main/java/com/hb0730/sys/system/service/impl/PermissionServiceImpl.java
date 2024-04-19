package com.hb0730.sys.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.common.util.QueryHelper;
import com.hb0730.rpc.sys.system.domain.query.PermissionQuery;
import com.hb0730.sys.system.domain.SysPermission;
import com.hb0730.sys.system.repository.PermissionRepository;
import com.hb0730.sys.system.repository.RoleRepository;
import com.hb0730.sys.system.service.IPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionServiceImpl implements IPermissionService {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<SysPermission> getByRoleIdsIn(List<Long> roleIds) {
        return permissionRepository.findByRoleIdsIn(roleIds);
    }

    @Override
    public List<SysPermission> listDefaultRootQueryOrderRank(PermissionQuery query) {
        QueryHelper.setFieldNull(query, Map.of("parentIdIsNull", "parentId"), "size", "current", "parentIdIsNull",
                "sorts");
        Specification<SysPermission> specification = FenixSpecification.ofBean(query);
        List<Sort.Order> orders = query.getSorts().orElse(List.of(Sort.Order.asc("rank")));
        return permissionRepository.findAll(specification, Sort.by(orders));
    }

    @Override
    public List<SysPermission> findByEnabledTrueOrderByRank() {
        return permissionRepository.findByEnabledIsTrueOrderByRank();
    }


    @Override
    public void add(SysPermission permission) {
        Long parentId = permission.getParentId();

        // 如果父级id为0，则设置为null
        if (Long.valueOf(0).equals(parentId)) {
            permission.setParentId(null);
        }

        permissionRepository.save(permission);
    }

    @Override
    public void updateById(SysPermission permission) {
        if (null == permission.getId()) {
            throw new ServiceException("id不能为空");
        }
        if (permission.getId().equals(permission.getParentId())) {
            throw new ServiceException("父级不能为自己");
        }
        SysPermission _permission = permissionRepository.findById(permission.getId()).orElseThrow(() -> new ServiceException("权限不存在"));
        // 保证相关数据不会被修改
        BeanUtil.copyProperties(permission, _permission, CopyOptions.create().ignoreNullValue());
        permissionRepository.save(_permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        roleRepository.untiedMenuByMenuId(id);
        permissionRepository.deleteById(id);
    }

    /**
     * 判断是否有子菜单
     *
     * @param id 菜单id
     * @return 存在返回true
     */
    @Override
    public boolean hasChildren(Long id) {
        return permissionRepository.existsByParentId(id);
    }
}
