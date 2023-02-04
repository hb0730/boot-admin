package com.hb0730.boot.admin.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * SPRING 启动器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Slf4j
public class BootAdminApp implements ApplicationContextAware, ApplicationRunner {
    private ApplicationContext context;


    public static void bootstrap(Class<?> clazz, String... args) {
        log.debug("{} 启动开始 ...", clazz.getCanonicalName());
        SpringApplication application = new SpringApplication(clazz);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args);
        log.debug("{} 启动完成 ...", clazz.getCanonicalName());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //启动后做点啥
        doSomethingAfterBooted();

        //输出启动完成信息
        doShowBootedInfs();
    }

    /**
     * 启动后做点啥
     */
    protected void doSomethingAfterBooted() {
    }

    /**
     * 输出启动完成信息
     */
    protected void doShowBootedInfs() {
        Environment env = context.getEnvironment();
        log.info("启动完成 -> {}:{}",
            env.getProperty("server.servlet.context-path"),
            env.getProperty("server.port")
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
