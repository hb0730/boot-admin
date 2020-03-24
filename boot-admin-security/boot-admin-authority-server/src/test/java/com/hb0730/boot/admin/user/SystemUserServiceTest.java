package com.hb0730.boot.admin.user;

import com.hb0730.boot.admin.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.user.service.ISystemUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SystemUserServiceTest {
    @Autowired
    private ISystemUserService systemUserService;

    @Test
    public void save() {
        SystemUserEntity entity=new SystemUserEntity();
        entity.setUsername("test");
        entity.setNickName("测试");
        entity.setPassword("123456");
        systemUserService.save(entity);
    }
}
