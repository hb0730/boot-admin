package com.hb0730.sys.service;

import com.hb0730.biz.entity.system.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class IUserServiceTest {

    @Test
    void findByUsername(@Autowired IUserService userService) {
        User user = userService.findByUsername("admin");
        log.info("user:{}", user);
    }
}