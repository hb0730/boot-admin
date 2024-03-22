package com.hb0730.security.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.boot.test.context.SpringBootTest
@Slf4j
class UserDetailServiceImplTest {

    @Test
    void loadUserByUsername(@Autowired UserDetailServiceImpl userDetailService) {
        log.info("{}", userDetailService.loadUserByUsername("admin"));
    }
}