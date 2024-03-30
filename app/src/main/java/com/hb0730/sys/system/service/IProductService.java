package com.hb0730.sys.system.service;

import com.hb0730.commons.JR;
import com.hb0730.rpc.sys.system.domain.query.ProductQuery;
import com.hb0730.sys.system.domain.SysProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
public interface IProductService {

    /**
     * 根据code查询
     *
     * @param code code
     * @param id   需要排除的id
     * @return .
     */
    Boolean existsByCode(String code, Long id);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<SysProduct> list(ProductQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 数据
     */
    Page<SysProduct> page(ProductQuery query);

    /**
     * 保存
     *
     * @param product 产品
     */
    void save(SysProduct product);

    /**
     * 更新<br>
     * <b>不判断能否是否启用与禁用</b>
     *
     * @param product 产品
     */
    void updateById(SysProduct product);

    /**
     * 删除
     *
     * @param id 产品id
     */
    JR<String> deleteById(Long id);

    /**
     * 授权菜单<br>
     *
     * <b>不判断能否授权</b>
     *
     * @param id      产品id
     * @param menuIds 菜单id
     */
    void grantMenus(Long id, List<Long> menuIds);
}
