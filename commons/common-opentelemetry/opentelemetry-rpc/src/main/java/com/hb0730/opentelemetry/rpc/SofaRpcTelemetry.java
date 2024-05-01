package com.hb0730.opentelemetry.rpc;

import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.api.instrumenter.Instrumenter;
import lombok.Getter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
@Getter
public class SofaRpcTelemetry {


    private final Instrumenter<SofaRequest, SofaResponse> serverInstrumenter;

    private final Instrumenter<SofaRequest, SofaResponse> clientInstrumenter;

    SofaRpcTelemetry(Instrumenter<SofaRequest, SofaResponse> serverInstrumenter,
                     Instrumenter<SofaRequest, SofaResponse> clientInstrumenter) {
        this.serverInstrumenter = serverInstrumenter;
        this.clientInstrumenter = clientInstrumenter;
    }

    /**
     * Create a new {@link SofaRpcTelemetry} instance.
     *
     * @param openTelemetry the OpenTelemetry instance to use.
     * @return a new {@link SofaRpcTelemetry} instance.
     */
    public static SofaRpcTelemetry create(OpenTelemetry openTelemetry) {
        return builder(openTelemetry).build();
    }

    /**
     * Create a new {@link SofaRpcTelemetryBuilder} instance.
     *
     * @param openTelemetry the OpenTelemetry instance to use.
     * @return a new {@link SofaRpcTelemetryBuilder} instance.
     */
    public static SofaRpcTelemetryBuilder builder(OpenTelemetry openTelemetry) {
        return new SofaRpcTelemetryBuilder(openTelemetry);
    }


}