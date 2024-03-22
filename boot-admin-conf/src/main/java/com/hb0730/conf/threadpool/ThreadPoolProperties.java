package com.hb0730.conf.threadpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/29
 */
@ConfigurationProperties(prefix = "boot.admin.thread-pool")
@Data
public class ThreadPoolProperties {
    /**
     * 核心线程池大小
     */
    private int corePoolSize = 50;

    /**
     * 最大可创建的线程数
     */
    private int maxPoolSize = 200;

    /**
     * 队列最大长度
     */
    private int queueCapacity = 1000;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds = 300;

}
