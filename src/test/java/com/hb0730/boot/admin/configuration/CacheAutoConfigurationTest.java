package com.hb0730.boot.admin.configuration;

import com.hb0730.boot.admin.cache.Cache;
import com.hb0730.boot.admin.cache.impl.local.InMemoryCacheStore;
import com.hb0730.boot.admin.cache.impl.remote.RedisSpringDataCache;
import com.hb0730.boot.admin.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import com.hb0730.boot.admin.cache.support.serial.impl.Jackson2JsonStringSerializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
//@ContextConfiguration(classes = CacheConfiguration.class)
@Import(value = CacheConfiguration.class)
public class CacheAutoConfigurationTest {
    @Resource(name = "bootCache")
    private Cache<String, String> cache;
    @Resource
    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        Map<String, Cache> beans = context.getBeansOfType(Cache.class);
        for (Cache value : beans.values()) {
            System.out.println(value.getClass());
        }
    }

    @Test
    public void cache() {
        cache.put("test", "test");
    }
}

@Configuration
class CacheConfiguration<K, V> {
    @Bean
    @ConditionalOnMissingBean(name = "bootCache")
    public Cache<K, V> bootCache(RedisConnectionFactory factory) {
        RedisSpringDataCacheConfig<K, V> config = new RedisSpringDataCacheConfig<>();
        config.setConnectionFactory(factory);
        config.setSerializer(Jackson2JsonStringSerializer.JSON_STRING_SERIALIZER);
        return new RedisSpringDataCache<K, V>(config);
    }

    @Bean("inMemoryCache")
    public Cache<K, V> inMemoryCache() {
        return new InMemoryCacheStore<K, V>();
    }
}
