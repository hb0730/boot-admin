package com.hb0730.rpc.basic.service;

import com.hb0730.commons.JR;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface BasOrgRpcService {

    /**
     * 验证登录是否到有效期
     *
     * @param orgId .
     * @return .
     */
    default JR<String> checkOrgExpiredForLogin(String orgId) {
        return JR.fail("暂未实现");
    }
}
