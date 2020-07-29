package com.hb0730.boot.admin.configuration.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.io.Serializable;
import java.time.Duration;

/**
 * @author bing_huang
 * @date 2020/07/28 7:47
 * @since V1.0
 */
@Data
@EqualsAndHashCode
@ToString
public class CacheProperties implements Serializable {
    private String cache = "memory";

    private int database = 0;
    /**
     * redis://user:password@example.com:6379
     */
    private String url;

    private String host = "localhost";

    private String password;

    private int port = 6379;

    private boolean ssl;
    /**
     * Connection timeout.
     */
    private Duration timeout;

    private final RedisProperties.Lettuce lettuce = new RedisProperties.Lettuce();

    /**
     * Lettuce client properties.
     */
    @Data
    public static class Lettuce {
        /**
         * Shutdown timeout.
         */
        private Duration shutdownTimeout = Duration.ofMillis(100);
        private RedisProperties.Pool pool;
    }

    /**
     * Pool properties.
     */
    @Data
    public static class Pool {
        private int maxIdle = 8;
        private int minIdle = 0;
        private int maxActive = 8;
        private Duration maxWait = Duration.ofMillis(-1);
        private Duration timeBetweenEvictionRuns;
    }
}
