package com.hb0730.boot.admin.project.system.menu.permission.handler;

import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.project.system.permission.model.dto.SystemPermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.SystemPermissionEntity;
import com.hb0730.boot.admin.project.system.permission.service.ISystemPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class PermissionHandle {
    @Autowired
    private ISystemPermissionService systemPermissionService;

    /**
     * <p>
     * 根据id获取权限信息
     * </p>
     *
     * @param ids id集
     * @return 权限信息
     */
    public List<SystemPermissionDTO> getPermissionByIds(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        List<SystemPermissionEntity> entities = systemPermissionService.listByIds(ids);
        return BeanUtils.transformFromInBatch(entities, SystemPermissionDTO.class);
    }

    /**
     * <p>
     * 权限保存
     * </P>
     *
     * @param entity 权限信息
     * @return 是否成功
     */
    public boolean savePermission(SystemPermissionEntity entity) {
        if (Objects.isNull(entity)) {
            throw new BaseException("权限信息为空");
        }
        String mark = entity.getMark();
        if (StringUtils.isBlank(mark)) {
            throw new BaseException("权限标识符为空");
        }
        return systemPermissionService.save(entity);
    }

    /**
     * <p>
     * 根据id修改权限
     * </p>
     *
     * @param entity 权限信息
     * @return 是否成功
     */
    public boolean updateById(SystemPermissionEntity entity) {
        if (Objects.isNull(entity)) {
            throw new BaseException("权限信息为空");
        }
        if (Objects.isNull(entity.getId())) {
            throw new BaseException("权限id为空");
        }
        SystemPermissionEntity e1 = systemPermissionService.getById(entity.getId());
        BeanUtils.updateProperties(entity, e1);
        return systemPermissionService.updateById(e1);
    }

    /**
     * <p>
     * 根据id删除
     * </p>
     *
     * @param id 权限id
     * @return 是否成功
     */
    public boolean deleteById(@NonNull Long id) {
        return systemPermissionService.removeById(id);
    }
}
