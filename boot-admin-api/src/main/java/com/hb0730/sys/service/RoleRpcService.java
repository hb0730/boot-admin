package com.hb0730.sys.service;

import com.hb0730.biz.dto.sys.system.RoleDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.sys.bean.RoleQuery;
import jakarta.annotation.Nullable;

import java.util.List;

/**
 * 角色rpc服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/28
 */
public interface RoleRpcService {

    /**
     * 查询所有启用的角色
     *
     * @return 角色
     */
    default JR<List<RoleDto>> findAllEnabled() {
        return null;
    }

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 角色
     */
    default JR<List<RoleDto>> findByUserId(Integer userId) {
        return null;
    }

    /**
     * 清除用户角色缓存
     *
     * @param userId 用户id
     * @return .
     */
    default JR<String> clearUserRoleCache(Integer userId) {
        return null;
    }


    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return .
     */
    default JR<JsfPage<RoleDto>> page(RoleQuery query) {
        return null;
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return .
     */
    default JR<List<RoleDto>> list(RoleQuery query) {
        return null;
    }

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   角色ID
     * @return .
     */
    default JR<Boolean> existsByCode(String code, @Nullable Integer id) {
        return null;
    }

    /**
     * 新增
     *
     * @param roleDto 角色
     * @return .
     */
    default JR<String> add(RoleDto roleDto) {
        return null;
    }


    /**
     * 更新
     *
     * @param roleDto 角色
     * @return .
     */
    default JR<String> updateById(RoleDto roleDto) {
        return null;
    }

    /**
     * 删除
     *
     * @param id 角色id
     * @return .
     */
    default JR<String> deleteById(Integer id) {
        return null;
    }


    /**
     * 分配权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     * @return 是否成功
     */
    default JR<String> assignPermission(Integer roleId, List<Integer> permissionIds) {
        return null;
    }
}
