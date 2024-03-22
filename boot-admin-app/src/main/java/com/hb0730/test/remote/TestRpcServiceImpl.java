package com.hb0730.test.remote;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.hb0730.test.TestRpcService;
import org.springframework.stereotype.Service;

/**
 * test rpc service
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/5
 */
@Service
@SofaService(bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class TestRpcServiceImpl implements TestRpcService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
