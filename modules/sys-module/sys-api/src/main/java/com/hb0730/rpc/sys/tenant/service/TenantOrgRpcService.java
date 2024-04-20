package com.hb0730.rpc.sys.tenant.service;

import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.tenant.domain.TenantBasicConfigDto;
import com.hb0730.rpc.sys.tenant.domain.TenantOrgDto;
import com.hb0730.rpc.sys.tenant.domain.TenantSmallDto;
import com.hb0730.rpc.sys.tenant.domain.query.TenantQuery;
import jakarta.annotation.Nullable;

import java.util.List;

/**
 * 组织机构 rpc服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/28
 */
public interface TenantOrgRpcService {
    /*===================商户组织机构================*/

    /**
     * 商户识别码是否存在
     *
     * @param code .
     * @param id   .
     * @return .
     */
    default JR<Boolean> existsByCode(String code, @Nullable String id) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询商户组织机构
     *
     * @param query 查询条件
     * @return 组织机构
     */
    default JR<JsfPage<TenantOrgDto>> queryTenantOrganization(TenantQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询商户组织机构
     *
     * @param query 查询条件
     * @return 组织机构
     */
    default JR<List<TenantOrgDto>> list(TenantQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 保存商户组织机构
     *
     * @param dto 组织机构
     * @return 主键
     */
    default JR<String> saveTenantOrganization(TenantSmallDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新商户组织机构
     *
     * @param dto 组织机构
     * @return 主键
     */
    default JR<String> updateTenantOrganization(TenantSmallDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 重置密码
     *
     * @param id 主键
     * @return 主键
     */
    default JR<String> resetPassword(String id, String operator) {
        return JR.fail("暂未实现");
    }


    /**
     * 获取商户基础配置
     *
     * @param id 主键
     * @return 主键
     */
    default JR<TenantBasicConfigDto> getTenantOrgBasicConfig(String id) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新商户基础配置
     *
     * @param dto 配置
     * @return 主键
     */
    default JR<String> updateTenantOrgBasicConfig(TenantBasicConfigDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 删除商户组织机构
     *
     * @param id 主键
     * @return 主键
     */
    default JR<String> deleteTenantOrganization(String id) {
        return JR.fail("暂未实现");
    }
    /*===================商户组织机构================*/
}
