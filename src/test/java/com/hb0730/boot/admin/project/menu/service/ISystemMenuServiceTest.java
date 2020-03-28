package com.hb0730.boot.admin.project.menu.service;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ISystemMenuServiceTest {
    @Autowired
    private ISystemMenuService systemMenuService;

    @Test
    public void getTreeAllTest(){
        systemMenuService.getTreeMenuAll(-1);
    }
}
