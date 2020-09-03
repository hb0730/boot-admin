package com.hb0730.boot.admin.project.system.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleDeptEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 角色数据权限  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IRoleDeptService extends IService<RoleDeptEntity> {

    /**
     * 保存角色与数据权限
     *
     * @param roleId  角色id
     * @param deptIds 部门id
     * @return 是否成功
     */
    boolean saveRoleDepts(@Nonnull Long roleId, @Nonnull Collection<Long> deptIds);

    /**
     * 根据角色id查询部门id
     *
     * @param roleId 角色id
     * @return 部门id
     */
    List<Long> findDeptIdByRoleId(@Nonnull Long roleId);
    /**
     * 根据角色id查询部门id
     *
     * @param roleId 角色id
     * @return 部门id
     */
    @Nullable
    Map<Long, List<Long>> findDeptIdByRoleIds(@Nonnull Collection<Long> roleId);
}
