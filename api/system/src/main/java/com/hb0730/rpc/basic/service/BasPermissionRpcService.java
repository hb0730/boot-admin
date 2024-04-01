package com.hb0730.rpc.basic.service;

import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasPermissionDto;
import com.hb0730.rpc.basic.domain.query.BasPermissionQuery;
import com.hb0730.rpc.sys.system.domain.PermissionDto;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface BasPermissionRpcService {
    /**
     * 根据角色id查询权限
     *
     * @param roleIds 角色id
     * @return 权限
     */
    default JR<List<BasPermissionDto>> findByRoleIds(List<String> roleIds) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据用户id查询权限
     *
     * @param userId 用户id
     * @return 权限
     */
    default JR<List<PermissionDto>> findByUserId(String userId, String sysCode) {
        return JR.fail("暂未实现");
    }

    /**
     * 清除用户权限的缓存
     *
     * @param userId 用户id
     * @return 结果
     */
    default JR<String> clearUserRoutesCache(String userId, String sysCode) {
        return JR.fail("暂未实现");
    }


    /**
     * 查询权限列表
     *
     * @param query 查询条件
     * @return 权限列表
     */
    default JR<List<BasPermissionDto>> list(BasPermissionQuery query) {
        return JR.fail("暂未实现");
    }
}
