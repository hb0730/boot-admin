package com.hb0730.base.rpc.filter;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务异常日志打印
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@Slf4j
@AutoActive(providerSide = true, consumerSide = false)
@Extension("serverExceptionLogPrintFilter")
public class ServerExceptionLogPrintFilter extends Filter {
    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        SofaResponse response = invoker.invoke(request);
        Object appResponse = response.getAppResponse();
        if (appResponse instanceof Throwable) {
            log.error("服务异常", (Throwable) appResponse);
        }
        return response;
    }
}
