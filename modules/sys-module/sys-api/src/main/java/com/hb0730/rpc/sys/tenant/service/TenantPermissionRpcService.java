package com.hb0730.rpc.sys.tenant.service;

import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionDto;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionSmallDto;
import com.hb0730.rpc.sys.tenant.domain.query.PermissionQuery;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/27
 */
public interface TenantPermissionRpcService {

    /**
     * 查询,默认查询root节点，rank排序
     *
     * @param query 查询条件
     * @return 数据
     */
    default JR<List<TenantPermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询已启用的权限
     *
     * @return .
     */
    default JR<List<TenantPermissionDto>> findAllEnabled() {
        return JR.fail("暂未实现");
    }


    /**
     * 新增权限
     *
     * @param dto .
     * @return .
     */
    default JR<String> save(TenantPermissionSmallDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新权限
     *
     * @param dto .
     * @return .
     */
    default JR<String> updateById(TenantPermissionSmallDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 删除权限
     *
     * @param id .
     * @return .
     */
    default JR<String> deleteById(Long id) {
        return JR.fail("暂未实现");
    }
}
