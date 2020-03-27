package com.hb0730.boot.admin.project.org.service.impl;

import com.hb0730.boot.admin.project.org.service.ISystemOrgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SystemOrgServiceImplTest {

    @Autowired
    private ISystemOrgService systemOrgService;
    @Test
    public void getTreeAll() {

    }
}
