package com.hb0730.test;

/**
 * test rpc service
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/5
 */
public interface TestRpcService {

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
