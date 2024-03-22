package com.hb0730.test.remote;

import com.hb0730.conf.rpc.rpc.BaseRpcService;
import com.hb0730.test.Test2RpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/6
 */
@Service
@Slf4j
public class Test2RpcServiceImpl extends BaseRpcService<Test2RpcService> implements Test2RpcService {
    @Override
    public String sayHello(String name) {
        log.info("sayHello: {}", name);
        return "hello " + name + " 2";
    }
}
