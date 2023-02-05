package com.hb0730.boot.admin.base.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PasswordUtilTest {

    @Test
    void encoder() {
        String password = PasswordUtil.encoder("123456");
        log.info(password);
    }
}
