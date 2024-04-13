package com.hb0730.rpc.sys.system.service;

import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.ProductDto;
import com.hb0730.rpc.sys.system.domain.query.ProductQuery;

import java.util.List;

/**
 * 产品rpc服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
public interface ProductRpcService {

    /**
     * 根据code查询
     *
     * @param code code
     * @param id   需要排除的id
     * @return .
     */
    default JR<Boolean> existsByCode(String code, Long id) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    default JR<List<ProductDto>> list(ProductQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 数据
     */
    default JR<JsfPage<ProductDto>> page(ProductQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 保存
     *
     * @param dto 数据
     * @return 数据
     */
    default JR<String> save(ProductDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID修改
     *
     * @param dto .
     * @return .
     */
    default JR<String> updateById(ProductDto dto) {
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
     * 赋权
     *
     * @param id      .
     * @param menuIds .
     * @return .
     */
    default JR<String> grant(Long id, List<Long> menuIds) {
        return JR.fail("暂未实现");
    }
}
