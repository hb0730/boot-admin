package com.hb0730.base.conf.filter;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import com.hb0730.base.TenantContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Slf4j
@AutoActive(providerSide = true, consumerSide = false)
@Extension("tenantServerFilter")
public class TenantServerFilter extends Filter {
    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        try {
            Object sysCode = request.getRequestProp("sysCode");
            if (null != sysCode) {
                TenantContext.setTenant((String) sysCode);
            }
            return invoker.invoke(request);
        } finally {
            TenantContext.clear();
        }

    }
}
