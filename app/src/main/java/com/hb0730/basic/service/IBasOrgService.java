package com.hb0730.basic.service;

import com.hb0730.basic.domain.BasOrg;
import com.hb0730.rpc.basic.domain.query.BasOrgQuery;

import java.util.List;

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

    /**
     * 站点数量是否已经上线
     */
    boolean checkSiteNum(String orgId);

    /**
     * 账号数量是否已经上线
     */
    boolean checkAccountNum(String orgId);

    /**
     * 列表查询，默认只查询根节点
     *
     * @param query .
     * @return .
     */
    List<BasOrg> listDefaultRootQuery(BasOrgQuery query);

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    List<BasOrg> list(BasOrgQuery query);

    /**
     * 保存
     *
     * @param basOrg .
     */
    void save(BasOrg basOrg);

    /**
     * 更新
     *
     * @param basOrg .
     */
    void updateById(BasOrg basOrg);
}
