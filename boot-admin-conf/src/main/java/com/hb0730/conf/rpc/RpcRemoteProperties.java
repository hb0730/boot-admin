package com.hb0730.conf.rpc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "boot.admin.rpc.remote")
public class RpcRemoteProperties {
    /**
     * Quartz Job 查询远程服务
     */
    private Job job = new Job();
    /**
     * RPC 服务
     */
    private Service service = new Service();


    /**
     * Job 查询远程服务
     */
    @Data
    public static class Job {
        private boolean enabled = false;
        /**
         * RPC接口地址
         */
        private String server = "http://127.0.0.1:9004";

        public String getServer() {
            if (server == null) {
                throw new IllegalArgumentException("server is null");
            }
            return server;
        }

        /**
         * RPC接口密钥
         */
        private String secret = "dummy";
    }

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
