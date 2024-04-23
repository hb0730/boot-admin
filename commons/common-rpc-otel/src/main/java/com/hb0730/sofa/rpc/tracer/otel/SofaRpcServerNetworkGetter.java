package com.hb0730.sofa.rpc.tracer.otel;

import com.alipay.sofa.rpc.context.RpcInternalContext;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import io.opentelemetry.instrumentation.api.semconv.network.NetworkAttributesGetter;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/23
 */
public class SofaRpcServerNetworkGetter implements NetworkAttributesGetter<SofaRequest, SofaResponse> {
    @Override
    public InetSocketAddress getNetworkLocalInetSocketAddress(SofaRequest request, @Nullable SofaResponse sofaResponse) {
        RpcInternalContext context = RpcInternalContext.getContext();
        if (context != null) {
            return context.getLocalAddress();
        }
        return null;
    }

    @Override
    public InetSocketAddress getNetworkPeerInetSocketAddress(SofaRequest request, @Nullable SofaResponse sofaResponse) {
        RpcInternalContext context = RpcInternalContext.getContext();
        if (context != null) {
            return context.getRemoteAddress();
        }
        return null;
    }
}
