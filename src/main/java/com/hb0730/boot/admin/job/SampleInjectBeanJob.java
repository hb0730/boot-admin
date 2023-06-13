package com.hb0730.boot.admin.job;

import jakarta.validation.constraints.NotNull;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 依赖注入
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/io.html#io.quartz">quartz</a
 */
public class SampleInjectBeanJob extends QuartzJobBean {
    @Override
    protected void executeInternal(@NotNull JobExecutionContext context) throws JobExecutionException {

    }
}
