package com.hb0730;

import com.hb0730.base.BootAdminApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/31
 */
@SpringBootApplication
public class JobServerApplication extends BootAdminApp {
    public static void main(String[] args) {
        bootstrap(JobServerApplication.class, args);
    }
}
