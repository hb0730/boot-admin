package com.hb0730.boot.admin.project.menu.permission.service.impl;

import com.hb0730.boot.admin.project.menu.permission.service.ISystemMenuPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SystemMenuPermissionServiceImplTest {
    @Autowired
    private ISystemMenuPermissionService systemMenuPermissionService;

    @Test
    public void getPermissionByMenuIdTest() {
        systemMenuPermissionService.getPermissionByMenuId(1243026675188297730L, 1, 10);
    }
    @Test
    public void getPermissionIdByMenuId() {
        Map<Long, Set<Long>> permissionIdByMenuId = systemMenuPermissionService.getPermissionIdByMenuId();
    }
}
