package com.hb0730;

import com.hb0730.base.BootAdminApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@SpringBootApplication
public class ApiServiceApplication extends BootAdminApp {
    public static void main(String[] args) {
        bootstrap(ApiServiceApplication.class, args);
    }
}
