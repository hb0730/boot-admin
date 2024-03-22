package com.hb0730.test;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/6
 */

public interface Test2RpcService {
    /**
     * say hello
     *
     * @param name name
     * @return hello
     */
    default String sayHello(String name) {
        return null;
    }
}
