package com.hb0730.sys.system.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.rpc.sys.system.domain.ProductDto;
import com.hb0730.sys.system.domain.SysProduct;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ProductMapper extends BaseMapper<ProductDto, SysProduct> {
}
