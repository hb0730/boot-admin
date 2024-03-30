package com.hb0730.basic.service;

import com.hb0730.basic.domain.BasOrg;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface IBasOrgService {
    /**
     * 根据组织id查询组织
     *
     * @param orgId .
     * @return .
     */
    BasOrg findByOrgId(String orgId);
}
