package com.hb0730.sys.system.rpc;

import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.ProductDto;
import com.hb0730.rpc.sys.system.domain.query.ProductQuery;
import com.hb0730.rpc.sys.system.service.ProductRpcService;
import com.hb0730.sys.system.domain.SysProduct;
import com.hb0730.sys.system.rpc.mapstruct.ProductMapper;
import com.hb0730.sys.system.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductRpcServiceImpl extends BaseServerRpcService<ProductRpcService> implements ProductRpcService {
    private final ProductMapper productMapper;

    private final IProductService productService;


    @Override
    public JR<Boolean> existsByCode(String code, Long id) {
        return JR.okData(productService.existsByCode(code, id));
    }

    @Override
    public JR<List<ProductDto>> list(ProductQuery query) {
        List<SysProduct> list = productService.list(query);
        List<ProductDto> res = productMapper.toDtoList(list);
        return JR.okData(res);
    }

    @Override
    public JR<JsfPage<ProductDto>> page(ProductQuery query) {
        Page<SysProduct> page = productService.page(query);
        List<ProductDto> res = productMapper.toDtoList(page.getContent());
        return JR.okData(JsfPage.of(page, res));
    }

    @Override
    public JR<String> save(ProductDto dto) {
        SysProduct entity = productMapper.toEntity(dto);
        productService.save(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(ProductDto dto) {
        SysProduct entity = productMapper.toEntity(dto);
        productService.updateById(entity);
        return JR.ok();
    }

    @Override
    public JR<String> deleteById(Long id) {
        return productService.deleteById(id);
    }

    @Override
    public JR<String> grant(Long id, List<Long> menuIds) {
        productService.grantMenus(id, menuIds);
        return JR.ok();
    }
}
