package com.hb0730.base.rpc.filter;

import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import com.hb0730.base.core.TenantContext;
import com.hb0730.base.core.UserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Slf4j
@AutoActive(providerSide = false, consumerSide = true)
@Extension("tenantClientFilter")
public class TenantClientFilter extends Filter {
    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        UserInfo userInfo = TenantContext.get();
        if (null != userInfo) {
            RpcInvokeContext.getContext().putRequestBaggage(
                    TenantContext.INVOKE_CTX_USER_ID,
                    userInfo.getId()
            );
            RpcInvokeContext.getContext().putRequestBaggage(
                    TenantContext.INVOKE_CTX_USERNAME,
                    userInfo.getUsername());
            RpcInvokeContext.getContext().putRequestBaggage(
                    TenantContext.INVOKE_CTX_SYS_CODE,
                    userInfo.getSysCode());
        }

        return invoker.invoke(request);
    }
}