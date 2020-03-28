package com.hb0730.boot.admin.project.role.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.project.role.org.mapper.ISystemRoleOrgMapper;
import com.hb0730.boot.admin.project.role.org.model.entity.SystemRoleOrgEntity;
import com.hb0730.boot.admin.project.role.org.service.ISystemRoleOrgService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        UpdateWrapper<SystemRoleOrgEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(SystemRoleOrgEntity.IS_ENABLED, SystemConstants.NOT_USE);
        updateWrapper.eq(SystemRoleOrgEntity.ROLE_ID, roleId);
        super.update(updateWrapper);
        if (!CollectionUtils.isEmpty(orgIds)) {
            List<SystemRoleOrgEntity> entities = Lists.newArrayList();
            orgIds.forEach((orgId) -> {
                SystemRoleOrgEntity entity = new SystemRoleOrgEntity();
                entity.setRoleId(roleId);
                entity.setOrgId(orgId);
                entity.setIsEnabled(SystemConstants.USE);
                entities.add(entity);
            });
            super.saveBatch(entities);
        }
        return true;
    }
}
