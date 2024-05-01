package com.hb0730.opentelemetry.rpc;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */

import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.api.incubator.semconv.rpc.RpcClientAttributesExtractor;
import io.opentelemetry.instrumentation.api.incubator.semconv.rpc.RpcClientMetrics;
import io.opentelemetry.instrumentation.api.incubator.semconv.rpc.RpcServerAttributesExtractor;
import io.opentelemetry.instrumentation.api.incubator.semconv.rpc.RpcServerMetrics;
import io.opentelemetry.instrumentation.api.incubator.semconv.rpc.RpcSpanNameExtractor;
import io.opentelemetry.instrumentation.api.instrumenter.Instrumenter;
import io.opentelemetry.instrumentation.api.instrumenter.InstrumenterBuilder;
import io.opentelemetry.instrumentation.api.instrumenter.SpanNameExtractor;
import io.opentelemetry.instrumentation.api.semconv.network.NetworkAttributesExtractor;
import io.opentelemetry.instrumentation.api.semconv.network.ServerAttributesExtractor;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/23
 */
public class SofaRpcTelemetryBuilder {
    private static final String INSTRUMENTATION_NAME = "com.hb0730.sofa-rpc-otel";
    private final OpenTelemetry openTelemetry;

    SofaRpcTelemetryBuilder(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
    }


    public SofaRpcTelemetry build() {
        SofaRpcAttributesGetter rpcAttributes = SofaRpcAttributesGetter.INSTANCE;
        SpanNameExtractor<SofaRequest> spanNameExtractor = RpcSpanNameExtractor.create(rpcAttributes);
        SofaRpcServerNetworkGetter serverNetwork = new SofaRpcServerNetworkGetter();
        SofaRpcClientNetworkGetter clientNetwork = new SofaRpcClientNetworkGetter();

        // Create server and client instrumenters
        InstrumenterBuilder<SofaRequest, SofaResponse> serverInstrumenterBuilder =
                Instrumenter.<SofaRequest, SofaResponse>builder(
                        openTelemetry,
                        INSTRUMENTATION_NAME,
                        spanNameExtractor
                ).addAttributesExtractor(
                        RpcServerAttributesExtractor.create(rpcAttributes)
                ).addAttributesExtractor(
                        NetworkAttributesExtractor.create(serverNetwork)
                ).addOperationMetrics(RpcServerMetrics.get());

        InstrumenterBuilder<SofaRequest, SofaResponse> clientInstrumenterBuilder =
                Instrumenter.<SofaRequest, SofaResponse>builder(
                        openTelemetry,
                        INSTRUMENTATION_NAME,
                        spanNameExtractor
                ).addAttributesExtractor(
                        RpcClientAttributesExtractor.create(rpcAttributes)
                ).addAttributesExtractor(
                        ServerAttributesExtractor.create(clientNetwork)
                ).addAttributesExtractor(
                        NetworkAttributesExtractor.create(clientNetwork)
                ).addOperationMetrics(
                        RpcClientMetrics.get()
                );


        return new SofaRpcTelemetry(
                serverInstrumenterBuilder.buildServerInstrumenter(
                        SofaRpcHeadersGetter.INSTANCE
                ),
                clientInstrumenterBuilder.buildClientInstrumenter(
                        SofaRpcHeadersSetter.INSTANCE
                )
        );
    }
}