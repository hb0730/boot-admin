package com.hb0730.boot.admin.project.system.role.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.project.system.role.org.mapper.ISystemRoleOrgMapper;
import com.hb0730.boot.admin.project.system.role.org.model.entity.SystemRoleOrgEntity;
import com.hb0730.boot.admin.project.system.role.org.service.ISystemRoleOrgService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色组织(数据范围)  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
@Service
public class SystemRoleOrgServiceImpl extends ServiceImpl<ISystemRoleOrgMapper, SystemRoleOrgEntity> implements ISystemRoleOrgService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrgIdsByRoleId(@NonNull Long roleId, List<Long> orgIds) {
        QueryWrapper<SystemRoleOrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemRoleOrgEntity.ROLE_ID, roleId);
        List<SystemRoleOrgEntity> entities = super.list(queryWrapper);
        // 新增
        if (CollectionUtils.isEmpty(entities)) {
            if (CollectionUtils.isEmpty(orgIds)) {
                return true;
            }
            return addNew(roleId, orgIds);
        }
        List<Long> list = entities.parallelStream().map(SystemRoleOrgEntity::getOrgId).collect(Collectors.toList());
        List<Long> updateList = Lists.newArrayList();
        List<Long> saveList = Lists.newArrayList();
        orgIds.forEach((orgId) -> {
            if (list.contains(orgId)) {
                updateList.add(orgId);
            } else {
                saveList.add(orgId);
            }
        });
        //多余的
        list.removeAll(orgIds);
        saveOrUpdate(roleId, updateList);
        saveOrUpdate(roleId, saveList);
        updateState(roleId, list, SystemConstants.NOT_USE);
        return true;

    }

    /**
     * <p>
     * 新增角色数据范围
     * </P>
     *
     * @param roleId 角色id
     * @param orgIds 组织id
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addNew(@NonNull Long roleId, @NonNull List<Long> orgIds) {
        List<SystemRoleOrgEntity> e1 = Lists.newArrayList();
        orgIds.forEach((orgId) -> {
            SystemRoleOrgEntity entity = new SystemRoleOrgEntity();
            entity.setRoleId(roleId);
            entity.setOrgId(orgId);
            entity.setIsEnabled(SystemConstants.USE);
            e1.add(entity);
        });
        return super.saveBatch(e1);
    }

    /**
     * <p>
     * 更新组织角色状态
     * </p>
     *
     * @param roleId 角色id
     * @param orgIds 组织id
     * @param state  状态
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateState(@NonNull Long roleId, @NonNull List<Long> orgIds, int state) {
        List<SystemRoleOrgEntity> entities = Lists.newArrayList();
        orgIds.forEach((orgId) -> {
            QueryWrapper<SystemRoleOrgEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(SystemRoleOrgEntity.ORG_ID, orgId);
            queryWrapper.eq(SystemRoleOrgEntity.ROLE_ID, roleId);
            SystemRoleOrgEntity entity = super.getOne(queryWrapper, false);
            if (!Objects.isNull(entity)) {
                entity.setIsEnabled(state);
            }
            entities.add(entity);
        });
        if (!CollectionUtils.isEmpty(entities)) {
            super.updateBatchById(entities);
        }
        return true;
    }

    /**
     * <p>
     * 新增或者修改
     * </p>
     *
     * @param roleId 角色id
     * @param orgIds 组织id
     * @return 是否成功
     */
    public boolean saveOrUpdate(@NonNull Long roleId, @NonNull List<Long> orgIds) {
        List<Long> updateOrg = Lists.newArrayList();
        List<Long> saveOrg = Lists.newArrayList();

        orgIds.forEach((orgId) -> {
            QueryWrapper<SystemRoleOrgEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(SystemRoleOrgEntity.ROLE_ID, roleId);
            queryWrapper.eq(SystemRoleOrgEntity.ORG_ID, orgId);
            List<SystemRoleOrgEntity> entities = super.list(queryWrapper);
            if (!CollectionUtils.isEmpty(entities)) {
                updateOrg.add(orgId);
            } else {
                saveOrg.add(orgId);
            }
        });
        if (!CollectionUtils.isEmpty(saveOrg)) {
            addNew(roleId, saveOrg);
        }
        if (!CollectionUtils.isEmpty(updateOrg)) {
            updateState(roleId, updateOrg, SystemConstants.USE);
        }
        return true;
    }
}
