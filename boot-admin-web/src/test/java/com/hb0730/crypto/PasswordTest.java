package com.hb0730.crypto;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/23
 */
@Slf4j
class PasswordTest {

    @Test
    void pwdTest() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("Admin123456");
        log.info("pwd: {}", pwd);
    }
}
