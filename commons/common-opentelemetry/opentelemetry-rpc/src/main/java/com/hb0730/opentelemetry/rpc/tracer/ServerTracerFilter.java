package com.hb0730.opentelemetry.rpc.tracer;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import com.hb0730.base.AppUtil;
import com.hb0730.opentelemetry.rpc.SofaRpcTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.instrumentation.api.instrumenter.Instrumenter;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端链路追踪
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/23
 */
@AutoActive(providerSide = true)
@Extension("serverTracerFilter")
@Slf4j
public class ServerTracerFilter extends Filter {
    private final Instrumenter<SofaRequest, SofaResponse> serverInstrumenter;

    public ServerTracerFilter() {
        Instrumenter<SofaRequest, SofaResponse> _serverInstrumenter = null;
        try {

            SofaRpcTelemetry sofaRpcTelemetry = AppUtil.getBean(SofaRpcTelemetry.class);
            _serverInstrumenter = sofaRpcTelemetry.getServerInstrumenter();
        } catch (Exception ignore) {
        }
        serverInstrumenter = _serverInstrumenter;
    }


    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        if (null == serverInstrumenter) {
            return invoker.invoke(request);
        }
        Context parentContext = Context.current();
        if (!serverInstrumenter.shouldStart(parentContext, request)) {
            return invoker.invoke(request);
        }
        Context context = serverInstrumenter.start(parentContext, request);
        SofaResponse response;
        try (var ignored = context.makeCurrent()) {
            response = invoker.invoke(request);
        } catch (Throwable t) {
            serverInstrumenter.end(context, request, null, t);
            throw t;
        }

        Object result = response.getAppResponse();
        if (result instanceof Throwable) {
            serverInstrumenter.end(context, request, response, (Throwable) result);
        } else {
            serverInstrumenter.end(context, request, response, null);
        }
        return response;

    }
}