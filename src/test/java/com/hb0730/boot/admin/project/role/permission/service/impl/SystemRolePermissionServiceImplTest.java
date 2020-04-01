package com.hb0730.boot.admin.project.role.permission.service.impl;

import com.hb0730.boot.admin.project.system.role.permission.service.ISystemRolePermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SystemRolePermissionServiceImplTest {
    @Autowired
    private ISystemRolePermissionService systemRolePermissionService;
    @Test
    public void getPermissionIdsByRoleId() {
        Map<Long, Set<Long>> permissionIdsByRoleId = systemRolePermissionService.getPermissionIdsByRoleId(1243420190300942338L);
    }
}
