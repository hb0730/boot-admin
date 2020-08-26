package com.hb0730.boot.admin;

import com.hb0730.commons.spring.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 入口
 *
 * @author <a href="huangbing0730@gmail.com">bing_huang</a>
 * @since 3.0.0
 */
@SpringBootApplication
public class BootAdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootAdminApplication.class, args);
        SpringContextUtils.setApplicationContext(applicationContext);
    }

}
