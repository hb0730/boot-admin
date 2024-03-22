package com.hb0730;

import com.hb0730.base.BootAdminApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 主业务:核心模块
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
@SpringBootApplication
@EnableTransactionManagement
public class ServerApplication extends BootAdminApp {
    public static void main(String[] args) {
        bootstrap(ServerApplication.class, args);
    }
}
