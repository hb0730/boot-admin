package com.hb0730.rpc.basic.service;

import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasOrgDto;
import com.hb0730.rpc.basic.domain.query.BasOrgQuery;

import java.util.List;

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

    /**
     * 列表查询，默认只查询根节点
     *
     * @param query .
     * @return .
     */
    default JR<List<BasOrgDto>> listDefaultRootQuery(BasOrgQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    default JR<List<BasOrgDto>> list(BasOrgQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 保存
     *
     * @param dto .
     * @return .
     */
    default JR<String> save(BasOrgDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新
     *
     * @param dto .
     * @return .
     */
    default JR<String> updateById(BasOrgDto dto) {
        return JR.fail("暂未实现");
    }
}
