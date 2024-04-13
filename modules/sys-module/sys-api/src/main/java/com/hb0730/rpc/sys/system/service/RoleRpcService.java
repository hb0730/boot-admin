package com.hb0730.rpc.sys.system.service;

import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.RoleDto;
import com.hb0730.rpc.sys.system.domain.query.RoleQuery;
import jakarta.annotation.Nullable;

import java.util.List;

/**
 * 角色 RPC 服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
public interface RoleRpcService {

    /**
     * 根据用户获取角色
     *
     * @param userId .
     * @return .
     */
    default JR<List<RoleDto>> findByUserId(Long userId) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询已启用的角色
     *
     * @return .
     */
    default JR<List<RoleDto>> findAllEnabled() {
        return JR.fail("暂未实现");
    }

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    default JR<JsfPage<RoleDto>> page(RoleQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    default JR<List<RoleDto>> list(RoleQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   需要排除的ID
     * @return .
     */
    default JR<Boolean> existsByCode(String code, @Nullable Long id) {
        return JR.fail("暂未实现");
    }

    /**
     * 新增角色
     *
     * @param roleDto .
     * @return .
     */
    default JR<String> add(RoleDto roleDto) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID更新
     *
     * @param roleDto .
     * @return .
     */
    default JR<String> updateById(RoleDto roleDto) {
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

    /**
     * 角色赋权
     *
     * @param roleId        .
     * @param permissionIds .
     * @return .
     */
    default JR<String> assignPermission(Long roleId, List<Long> permissionIds) {
        return JR.fail("暂未实现");
    }
}
