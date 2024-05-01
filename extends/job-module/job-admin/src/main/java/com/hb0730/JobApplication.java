package com.hb0730;

import com.hb0730.base.BootAdminApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
@SpringBootApplication
public class JobApplication extends BootAdminApp {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }
}
