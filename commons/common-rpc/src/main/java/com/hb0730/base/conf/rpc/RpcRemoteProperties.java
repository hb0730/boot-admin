package com.hb0730.base.conf.rpc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "boot.admin.rpc.remote")
public class RpcRemoteProperties {
    /**
     * RPC 服务
     */
    private Service service = new Service();

    /**
     * 远程 服务
     */
    @Data
    public static class Service {
        /**
         * RPC接口地址 直连模式 bolt协议
         */
        private String boltServer = "127.0.0.1:12200";
    }
}
