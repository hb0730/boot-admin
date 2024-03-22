package com.hb0730.sys.service.impl;

import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.BootAdminException;
import com.hb0730.base.utils.QueryHelper;
import com.hb0730.biz.entity.system.Permission;
import com.hb0730.sys.bean.PermissionQuery;
import com.hb0730.sys.repository.PermissionRepository;
import com.hb0730.sys.repository.RoleRepository;
import com.hb0730.sys.service.IPermissionService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionServiceImpl implements IPermissionService {
    private final PermissionRepository permissionRepository;
    @Resource
    @Lazy
    private RoleRepository roleRepository;


    @Override
    public List<Permission> findByEnabledTrueOrderByRank() {
        return permissionRepository.findByEnabledIsTrueOrderByRank();
    }

    @Override
    public List<Permission> findByRoleIds(List<Integer> roleIds) {
        return permissionRepository.findByRoleIdsOrderByRank(roleIds);
    }

    @Override
    public List<Permission> listDefaultRootQueryOrderRank(PermissionQuery query) {
        QueryHelper.setFieldNull(query, Map.of("parentIdIsNull", "parentId"), "size", "current", "parentIdIsNull",
                "sorts");
        Specification<Permission> specification = FenixSpecification.ofBean(query);
        List<Sort.Order> orders = query.getSorts().orElse(List.of(Sort.Order.asc("rank")));
        return permissionRepository.findAll(specification, Sort.by(orders));
    }

    @Override
    public Permission findById(Integer id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Permission permission) {
        Integer parentId = permission.getParentId();

        // 如果父级id为0，则设置为null
        if (Integer.valueOf(0).equals(parentId)) {
            permission.setParentId(null);
        }

        permissionRepository.save(permission);
    }

    @Override
    public void updateById(Permission permission) {
        if (null == permission.getId()) {
            throw new BootAdminException("id不能为空");
        }
        if (permission.getId().equals(permission.getParentId())) {
            throw new BootAdminException("父级不能为自己");
        }
        permissionRepository.save(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
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
    public boolean hasChildren(Integer id) {
        return permissionRepository.existsByParentId(id);
    }
}
