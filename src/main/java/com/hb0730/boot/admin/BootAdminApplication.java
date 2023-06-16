package com.hb0730.boot.admin;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.hb0730.boot.admin.base.BootAdminApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@SpringBootApplication
@EnableSpringUtil
@EnableAsync
public class BootAdminApplication extends BootAdminApp {
    public static void main(String[] args) {
        bootstrap(BootAdminApplication.class, args);
    }

    @Override
    protected void doSomethingAfterBooted() {

    }
}
