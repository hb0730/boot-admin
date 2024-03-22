package com.hb0730;

import com.hb0730.base.BootAdminApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */
@SpringBootApplication
public class WebServerApplication extends BootAdminApp {

    /**
     * 启动类
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        bootstrap(WebServerApplication.class, args);
    }
}
