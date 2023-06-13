package com.hb0730.boot.admin.job;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 依赖注入
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/io.html#io.quartz">quartz</a
 */
@Slf4j
public class SampleInjectBeanJob extends QuartzJobBean {
    private MyTestJobService myTestJobService;

    /**
     * 依赖注入
     * <p>
     * 为什么需要@Resource或者@Autowired
     *
     * @param myTestJobService .
     * @see org.springframework.scheduling.quartz.SpringBeanJobFactory#createJobInstance(TriggerFiredBundle)
     * getAutowireCapableBeanFactory
     * </p>
     */
    @Resource
    public void setMyTestJobService(MyTestJobService myTestJobService) {
        this.myTestJobService = myTestJobService;
    }

    @Override
    protected void executeInternal(@NotNull JobExecutionContext context) throws JobExecutionException {
        log.info("普通定时任务 SampleInjectBeanJob !  时间: {}", myTestJobService);
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String parameter = jobDataMap.getString("parameter");
        myTestJobService.start(parameter);
    }
}
