package com.hb0730.boot.admin;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 入口
 *
 * @author <a href="huangbing0730@gmail.com">bing_huang</a>
 * @since 3.0.0
 */
@SpringBootApplication
@EnableAsync
@EnableSpringUtil
public class BootAdminApplication {

    public static void main(String[] args) {
       SpringApplication.run(BootAdminApplication.class, args);
    }

}
