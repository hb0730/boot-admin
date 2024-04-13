package com.hb0730.rpc.sys.system.service;

import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import com.hb0730.rpc.sys.system.domain.PermissionSaveDto;
import com.hb0730.rpc.sys.system.domain.query.PermissionQuery;

import java.util.List;

/**
 * 权限 RPC 服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
public interface PermissionRpcService {
    /**
     * 根据角色ID获取权限
     *
     * @param roleId .
     * @return .
     */
    default JR<List<PermissionDto>> findByRoleIds(List<Long> roleId) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据用户获取权限菜单
     *
     * @param userId .
     * @return .
     */
    default JR<List<PermissionDto>> findByUserId(Long userId) {
        return JR.fail("暂未实现");
    }

    /**
     * 清理用户路由缓存
     *
     * @param userId .
     * @return .
     */
    default JR<String> clearUserRouteCache(Long userId) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询,默认查询root节点，rank排序
     *
     * @param query 查询条件
     * @return 数据
     */
    default JR<List<PermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询已启用的权限
     *
     * @return .
     */
    default JR<List<PermissionDto>> findAllEnabled() {
        return JR.fail("暂未实现");
    }

    /**
     * 新增权限
     *
     * @param dto .
     * @return .
     */
    default JR<String> add(PermissionSaveDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID更新
     *
     * @param dto .
     * @return .
     */
    default JR<String> updateById(PermissionSaveDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID删除
     *
     * @param id .
     * @return .
     */
    default JR<String> deleteById(Long id) {
        return JR.fail("暂未实现");
    }
}
