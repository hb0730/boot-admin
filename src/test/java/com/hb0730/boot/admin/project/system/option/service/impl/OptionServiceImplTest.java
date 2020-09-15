package com.hb0730.boot.admin.project.system.option.service.impl;

import com.hb0730.boot.admin.project.system.option.service.IOptionService;
import com.hb0730.commons.spring.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Slf4j
public class OptionServiceImplTest {
    @Autowired
    private IOptionService service;
    @Autowired
    private ApplicationContext context;

    @Before
    public void init() {
        SpringContextUtils.setApplicationContext(context);
    }

    @Test
    public void listOptionsTest() {
        Map<String, Object> stringObjectMap = service.listOptions();
        Assert.assertNotNull(stringObjectMap);
    }
}
