package com.hb0730.boot.admin.configuration;

import com.hb0730.boot.admin.project.monitor.job.handler.JobHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

///**
// * <p>
// * springBoot启动之后自动执行
// * </P>
// *
// * @author bing_huang
// * @since V1.0
// */
//@Component
//@Deprecated(since = "v1.0")
//public class StartRunner implements CommandLineRunner {
//    @Autowired
//    private JobHandler jobHandler;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // 初始化job
//        jobHandler.init();
//    }
//}

/**
 * springBoot启动自动执行
 *
 * @author bing_huang
 * @since v2.0
 */
@Component
@AllArgsConstructor
public class StartRunner implements ApplicationListener<ContextRefreshedEvent> {
    private final JobHandler jobHandler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        jobHandler.init();
    }
}
