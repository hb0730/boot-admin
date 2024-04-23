package com.hb0730.sofa.rpc.tracer;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import com.hb0730.base.AppUtil;
import com.hb0730.sofa.rpc.tracer.otel.SofaRpcTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.instrumentation.api.instrumenter.Instrumenter;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端链路追踪
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/23
 */
@Extension("clientTracerFilter")
@AutoActive(consumerSide = true)
@Slf4j
public class ClientTracerFilter extends Filter {
    private final Instrumenter<SofaRequest, SofaResponse> clientInstrumenter;

    public ClientTracerFilter() {
        //  GlobalOpenTelemetry.get() 无法确保 AutoConfiguredOpenTelemetrySdk
//        clientInstrumenter=SofaRpcTelemetry.create(GlobalOpenTelemetry.get()).getClientInstrumenter();
        clientInstrumenter = SofaRpcTelemetry.create(
                AppUtil.getBean(OpenTelemetry.class)
        ).getClientInstrumenter();
    }

    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        Context parentContext = Context.current();
        if (!clientInstrumenter.shouldStart(parentContext, request)) {
            return invoker.invoke(request);
        }
        Context context = clientInstrumenter.start(parentContext, request);
        SofaResponse response;
        try (Scope ignored = context.makeCurrent()) {
            response = invoker.invoke(request);
        } catch (Throwable t) {
            clientInstrumenter.end(context, request, null, t);
            throw t;
        }

        Object result = response.getAppResponse();
        if (result instanceof Throwable) {
            clientInstrumenter.end(context, request, response, (Throwable) result);
        } else {
            clientInstrumenter.end(context, request, response, null);
        }
        return response;

    }
}
