package com.hb0730.rpc.basic.service;

import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasUserDto;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface BasUserRpcService {
    /**
     * 根据用户名查询系统编码
     *
     * @param username 用户名
     * @return 系统编码
     */
    default JR<String> getSysCodeByUsername(String username) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    default JR<BasUserDto> findByUsername(String username) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新最后登录时间
     *
     * @param username 用户名
     * @return .
     */
    default JR<String> updateLastLoginTime(String username) {
        return JR.fail("暂未实现");
    }
}
