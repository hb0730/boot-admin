package com.hb0730.boot.admin.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Objects;
import java.util.Set;

import static com.hb0730.boot.admin.token.ITokenService.LOGIN_TOKEN_KEY_PREFIX;

/**
 * redis 测试
 *
 * @author bing_huang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class RedisTest {
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void listTest(){
        Set<String> keys = redisTemplate.keys(LOGIN_TOKEN_KEY_PREFIX + "*");
        Assert.assertNotNull(keys);
    }

    @Test
    public void getValueByListTest(){
        Set<String> keys = redisTemplate.keys(LOGIN_TOKEN_KEY_PREFIX + "*");
        for (String key : Objects.requireNonNull(keys)) {
            Object o = redisTemplate.opsForValue().get(key);
            Assert.assertNotNull(o);
        }
    }
}
