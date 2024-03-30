package com.hb0730.sys.system.service;

import com.hb0730.base.BaseTest;
import com.hb0730.sys.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class IUserServiceTest extends BaseTest {


    @Test
    void findByUsername(@Autowired IUserService userService) {
        SysUser user = userService.findByUsername("admin");
        assertNotNull(user);
    }
}