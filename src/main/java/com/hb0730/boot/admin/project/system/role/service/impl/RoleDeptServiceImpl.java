package com.hb0730.boot.admin.project.system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.boot.admin.domain.service.impl.BaseServiceImpl;
import com.hb0730.boot.admin.project.system.role.mapper.IRoleDeptMapper;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleDeptEntity;
import com.hb0730.boot.admin.project.system.role.service.IRoleDeptService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色数据权限  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class RoleDeptServiceImpl extends BaseServiceImpl<IRoleDeptMapper, RoleDeptEntity> implements IRoleDeptService {

    @Override
    public boolean saveRoleDepts(@Nonnull Long roleId, @Nonnull Collection<Long> deptIds) {
        Assert.notNull(roleId, "角色id为空");
        Assert.notNull(deptIds, "部门id为空");
        if (CollectionUtils.isEmpty(deptIds)) {
            return false;
        }
        List<RoleDeptEntity> entities = new ArrayList<>(deptIds.size());
        for (Long deptId : deptIds) {
            RoleDeptEntity entity = new RoleDeptEntity();
            entity.setDeptId(deptId);
            entity.setRoleId(roleId);
            entities.add(entity);
        }
        return super.saveBatch(entities);
    }

    @Override
    public List<Long> findDeptIdByRoleId(@Nonnull Long roleId) {
        Assert.notNull(roleId, "角色id为空");
        LambdaQueryWrapper<RoleDeptEntity> queryWrapper = Wrappers.lambdaQuery(RoleDeptEntity.class).eq(RoleDeptEntity::getRoleId, roleId);
        List<RoleDeptEntity> entities = super.list(queryWrapper);
        return entities.parallelStream().map(RoleDeptEntity::getDeptId).collect(Collectors.toList());
    }

    @Override
    @Nullable
    public Map<Long, List<Long>> findDeptIdByRoleIds(@Nonnull Collection<Long> roleId) {
        Assert.notEmpty(roleId, "角色id为空");
        LambdaQueryWrapper<RoleDeptEntity> queryWrapper = Wrappers.lambdaQuery(RoleDeptEntity.class).in(RoleDeptEntity::getRoleId, roleId);
        List<RoleDeptEntity> entities = super.list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        return entities.parallelStream()
                .collect(
                        Collectors.groupingBy(
                                RoleDeptEntity::getRoleId,
                                Collectors.mapping(RoleDeptEntity::getDeptId, Collectors.toList())
                        )
                );
    }
}
