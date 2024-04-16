package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.ProductDto;
import com.hb0730.rpc.sys.system.domain.query.ProductQuery;
import com.hb0730.rpc.sys.system.service.ProductRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/27
 */
@Service
@Slf4j
public class ProductRemoteRpcService extends BaseRemoteRpcService<ProductRpcService> implements ProductRpcService {
    @Override
    protected String getAppName() {
        return "job";
    }

    @Override
    public JR<Boolean> existsByCode(String code, Long id) {
        return getRpcService().existsByCode(code, id);
    }

    @Override
    public JR<List<ProductDto>> list(ProductQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<JsfPage<ProductDto>> page(ProductQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<String> save(ProductDto dto) {
        return getRpcService().save(dto);
    }

    @Override
    public JR<String> updateById(ProductDto dto) {
        return getRpcService().updateById(dto);
    }

    @Override
    public JR<String> deleteById(Long id) {
        return getRpcService().deleteById(id);
    }

    @Override
    public JR<String> grant(Long id, List<Long> menuIds) {
        return getRpcService().grant(id, menuIds);
    }
}
