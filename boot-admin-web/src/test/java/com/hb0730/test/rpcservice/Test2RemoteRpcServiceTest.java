package com.hb0730.test.rpcservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class Test2RemoteRpcServiceTest {

    @Test
    void sayHello(@Autowired Test2RemoteRpcService test2RemoteRpcService) {
        String res = test2RemoteRpcService.sayHello("hb0730");
        log.info("result: {}", res);
    }
}