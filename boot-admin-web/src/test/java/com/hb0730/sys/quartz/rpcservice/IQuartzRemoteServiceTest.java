package com.hb0730.sys.quartz.rpcservice;

import com.hb0730.base.R;
import com.hb0730.base.enums.sys.JobStatusEnums;
import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class IQuartzRemoteServiceTest {

    @Test
    void add(@Autowired IQuartzRemoteService quartzRemoteService) {
        QuartzJobDto dto = new QuartzJobDto();
        dto.setJobName("test");
        dto.setJobClassName("com.hb0730.job.SimpleJob");
        dto.setCronExpression("0/1 * * * * ?");
        dto.setStatus(JobStatusEnums.ENABLE.getValue());
        dto.setParameter("hello");
        R<String> res = quartzRemoteService.add(dto);
        log.info("{}", res);
    }

    @Test
    void executeJob(@Autowired IQuartzRemoteService quartzRemoteService) {
        R<String> res = quartzRemoteService.executeJob(1);
        log.info("{}", res);

    }
}