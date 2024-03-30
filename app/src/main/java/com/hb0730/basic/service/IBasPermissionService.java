package com.hb0730.basic.service;

import com.hb0730.basic.domain.BasPermission;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface IBasPermissionService {

    /**
     * 根据角色获取权限
     *
     * @param roleIds .
     * @return .
     */
    List<BasPermission> findByRoleIds(List<String> roleIds);
}
