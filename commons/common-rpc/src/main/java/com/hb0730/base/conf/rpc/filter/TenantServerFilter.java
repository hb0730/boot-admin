package com.hb0730.base.conf.rpc.filter;

import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import com.hb0730.base.tenant.TenantContext;
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
            String username = RpcInvokeContext.getContext().getRequestBaggage(
                    TenantContext.INVOKE_CTX_USERNAME);
            String sysCode = RpcInvokeContext.getContext().getRequestBaggage(
                    TenantContext.INVOKE_CTX_SYS_CODE);

            if (username != null && sysCode != null) {
                log.info("user context username: {}, sysCode: {}", username, sysCode);
                TenantContext.UserInfo userInfo = new TenantContext.UserInfo(username, sysCode);
                TenantContext.set(userInfo);
            }
            return invoker.invoke(request);
        } catch (Exception e) {
            log.error("tenant server filter error", e);
            throw e;
        } finally {
            TenantContext.remove();
        }
    }
}
