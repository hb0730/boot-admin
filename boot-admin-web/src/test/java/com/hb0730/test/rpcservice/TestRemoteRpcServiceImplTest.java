package com.hb0730.test.rpcservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TestRemoteRpcServiceImplTest {

    @Test
    void sayHello(@Autowired TestRemoteRpcService testRemoteRpcService) {
        String res = testRemoteRpcService.sayHello("hb0730");
        log.info("result: {}", res);
    }
}