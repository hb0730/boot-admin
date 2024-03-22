package com.hb0730.test.rpcservice;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.hb0730.test.TestRpcService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/5
 */
@Service
public class TestRemoteRpcService implements TestRpcService {
    @SofaReference(binding = @SofaReferenceBinding(bindingType = "bolt"))
    private TestRpcService testRpcService;

    @Override
    public String sayHello(String name) {
        return this.testRpcService.sayHello(name);
    }
}
