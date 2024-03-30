package com.hb0730.basic.service;

import com.hb0730.basic.domain.BasRole;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface IBasRoleService {

    /**
     * 根据用户id查询角色
     *
     * @param userId .
     * @return .
     */
    List<BasRole> findByUserId(String userId);
}
