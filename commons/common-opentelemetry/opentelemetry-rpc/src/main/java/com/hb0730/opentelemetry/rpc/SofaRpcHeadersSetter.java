package com.hb0730.opentelemetry.rpc;

import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import io.opentelemetry.context.propagation.TextMapSetter;
import jakarta.annotation.Nullable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
enum SofaRpcHeadersSetter implements TextMapSetter<SofaRequest> {
    INSTANCE;

    @Override
    public void set(@Nullable SofaRequest request, String key, String value) {
        if (request != null) {
            request.addRequestProp(key, value);
        }

        RpcInvokeContext context = RpcInvokeContext.peekContext();
        if (null != context) {
            context.putRequestBaggage(key, value);
        }
    }
}