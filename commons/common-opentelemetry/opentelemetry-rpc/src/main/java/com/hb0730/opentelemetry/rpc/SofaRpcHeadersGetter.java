package com.hb0730.opentelemetry.rpc;

import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import io.opentelemetry.context.propagation.TextMapGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
enum SofaRpcHeadersGetter implements TextMapGetter<SofaRequest> {
    INSTANCE;

    @Override
    public Iterable<String> keys(SofaRequest request) {
        List<String> keys = new ArrayList<>();
        RpcInvokeContext context = RpcInvokeContext.peekContext();
        if (context != null) {
            Map<String, String> allRequestBaggage = context.getAllRequestBaggage();
            if (allRequestBaggage != null) {
                keys.addAll(allRequestBaggage.keySet());
            }
        }
        keys.addAll(request.getRequestProps().keySet());
        return keys;

    }

    @Override
    public String get(SofaRequest request, String key) {
        // 先从prop
        String value = null;
        if (null != request) {
            Object obj = request.getRequestProp(key);
            if (obj != null) {
                value = obj.toString();
            }
        }

        if (null == value) {
            RpcInvokeContext context = RpcInvokeContext.peekContext();
            if (null != context) {
                value = context.getRequestBaggage(key);
            }
        }
        return value;
    }
}