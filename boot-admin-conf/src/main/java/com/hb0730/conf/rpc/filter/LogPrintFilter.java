package com.hb0730.conf.rpc.filter;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/15
 */
@Extension("logPrintFilter")
@Slf4j
@AutoActive(providerSide = true, consumerSide = false)
public class LogPrintFilter extends Filter {
    @Override
    public boolean needToLoad(FilterInvoker invoker) {
        return super.needToLoad(invoker);
    }

    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        SofaResponse response = invoker.invoke(request);
        Object appResponse = response.getAppResponse();
        if (appResponse instanceof Throwable) {
            log.error("调用异常", (Throwable) appResponse);
        }
        // 打印堆栈
        return response;
    }
}
