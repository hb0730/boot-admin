package com.hb0730.sys.service;

import com.hb0730.biz.dto.sys.system.PermissionDto;
import com.hb0730.biz.dto.sys.system.PermissionSaveDto;
import com.hb0730.commons.JR;
import com.hb0730.sys.bean.PermissionQuery;

import java.util.List;

/**
 * 菜单与权限 RPC 服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
public interface PermissionRpcService {

    /**
     * 根据用户id查询菜单与权限
     *
     * @param userId 用户id
     * @return 权限
     */
    default JR<List<PermissionDto>> findByUserId(Integer userId) {
        return null;
    }

    /**
     * 查询所有已启用的菜单与权限
     *
     * @return 菜单与权限
     */
    default JR<List<PermissionDto>> findAllEnabled() {
        return null;
    }

    /**
     * 查询,默认查询root节点，rank排序
     *
     * @param query 查询条件
     * @return 数据
     */
    default JR<List<PermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        return null;
    }

    /**
     * 清除用户权限缓存
     *
     * @param userId 用户id
     * @return .
     */
    default JR<String> clearUserPermissionCache(Integer userId) {
        return null;
    }

    /**
     * 添加
     *
     * @param dto .
     * @return .
     */
    default JR<String> add(PermissionSaveDto dto) {
        return null;
    }

    /**
     * 更新
     *
     * @param dto .
     * @return .
     */
    default JR<String> updateById(PermissionSaveDto dto) {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     * @return .
     */
    default JR<String> delete(Integer id) {
        return null;
    }
}
