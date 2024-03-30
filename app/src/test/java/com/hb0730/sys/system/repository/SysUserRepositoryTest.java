package com.hb0730.sys.system.repository;

import com.hb0730.sys.system.domain.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysUserRepositoryTest {

    @Test
    void idSaveTest(@Autowired UserRepository repository) {
        SysUser user = new SysUser();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setNickname("管理员");
        user.setEnabled(true);
        repository.save(user);
    }
}