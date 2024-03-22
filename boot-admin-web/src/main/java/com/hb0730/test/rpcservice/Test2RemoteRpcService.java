package com.hb0730.test.rpcservice;

import com.hb0730.conf.rpc.remote.BaseRemoteService;
import com.hb0730.test.Test2RpcService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/5
 */
@Service
public class Test2RemoteRpcService extends BaseRemoteService<Test2RpcService> implements Test2RpcService {
    @Override
    public String sayHello(String name) {
        return getRpcService().sayHello(name);
    }
}
