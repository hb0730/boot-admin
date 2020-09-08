package com.hb0730.boot.admin.task.quartz.utils;

import com.hb0730.boot.admin.task.domain.JobInfo;
import com.hb0730.commons.spring.SpringContextUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ScheduleUtilsTest {
    @Autowired
    private SchedulerFactoryBean factoryBean;

    @Autowired
    private ApplicationContext applicationContext;


    @Before
    public void init() {
        SpringContextUtils.setApplicationContext(applicationContext);
    }

    @Test
    public void createScheduleJobTest() throws SchedulerException, InterruptedException {
        Scheduler scheduler = factoryBean.getScheduler();
        JobInfo entity = new JobInfo();
        entity.setBeanName("taskTest");
        entity.setBeanMethod("params");
        entity.setMethodParams("{\"java.lang.String\":\"test\"}");
        entity.setCron("0/10 * * * * ?");
        entity.setId(1L);
        entity.setGroup("default");
        entity.setIsEnabled(1);
        ScheduleUtils.createScheduleJob(scheduler, entity);
        Thread.sleep(3 * 1000L);
        scheduler.deleteJob(ScheduleUtils.getJobKey(entity.getId(), entity.getGroup()));
    }
}
