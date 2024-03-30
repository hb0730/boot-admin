package com.hb0730.base;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
public class BaseTest {


    @BeforeAll
    @DisplayName("初始化 rpc 端口")
    static void setRpcBlotPort() {
        System.setProperty("sofa.boot.rpc.bolt-port", RandomUtil.randomNumbers(4));
    }
}
