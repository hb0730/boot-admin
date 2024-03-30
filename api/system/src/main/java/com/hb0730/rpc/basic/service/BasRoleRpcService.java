package com.hb0730.rpc.basic.service;

import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasRoleDto;

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
}
