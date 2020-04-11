package com.hb0730.boot.admin;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 入口
 *
 * @author bing_huang
 * @since v1.0.0
 */
@SpringBootApplication
@EnableScheduling
@EnableMethodCache(basePackages = "com.hb0730.boot.admin")
@EnableCreateCacheAnnotation
public class BootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAdminApplication.class, args);
    }

}
