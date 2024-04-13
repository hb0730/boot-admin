package com.hb0730.rpc.basic.service;

import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.basic.domain.BasRoleDto;
import com.hb0730.rpc.basic.domain.query.BasRoleQuery;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface BasRoleRpcService {

    /**
     * 根据用户id查询角色
     *
     * @param userId .
     * @return .
     */
    default JR<List<BasRoleDto>> findByUserId(String userId) {
        return JR.fail("暂未实现");
    }


    /**
     * code是否存在
     *
     * @param code    角色标识
     * @param sysCode 商户识别码
     * @param id      需要排除的id
     * @return .
     */
    default JR<Boolean> existsByCode(String code, String sysCode, String id) {
        return JR.fail("暂未实现");
    }

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    default JR<JsfPage<BasRoleDto>> page(BasRoleQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    default JR<List<BasRoleDto>> list(BasRoleQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 新增角色
     *
     * @param dto .
     * @return .
     */
    default JR<String> save(BasRoleDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID修改
     *
     * @param dto .
     * @return .
     */
    default JR<String> updateById(BasRoleDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID删除
     *
     * @param id .
     * @return .
     */
    default JR<String> delete(String id) {
        return JR.fail("暂未实现");
    }

    /**
     * 赋权
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     * @return .
     */
    default JR<String> grant(String roleId, List<Long> permissionIds) {
        return JR.fail("暂未实现");
    }
}
