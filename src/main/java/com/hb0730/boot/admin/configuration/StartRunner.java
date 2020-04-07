package com.hb0730.boot.admin.configuration;

import com.hb0730.boot.admin.project.monitor.job.handler.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 * springBoot启动之后自动执行
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class StartRunner implements CommandLineRunner {
    @Autowired
    private JobHandler jobHandler;

    @Override
    public void run(String... args) throws Exception {
        // 初始化job
        jobHandler.init();
    }
}
