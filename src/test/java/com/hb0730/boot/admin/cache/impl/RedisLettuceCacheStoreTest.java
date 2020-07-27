package com.hb0730.boot.admin.cache.impl;

import com.hb0730.boot.admin.cache.Cache;
import com.hb0730.boot.admin.cache.impl.remote.RedisLettuceCacheStore;
import com.hb0730.boot.admin.cache.support.redis.lettuce.LettuceCodec;
import com.hb0730.boot.admin.cache.support.redis.lettuce.RedisLettuceCacheConfig;
import com.hb0730.boot.admin.cache.support.serial.GlobalSerializeMap;
import com.hb0730.boot.admin.cache.support.serial.impl.Jackson2JsonStringSerializer;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class RedisLettuceCacheStoreTest {
    @Autowired
    private LettuceConnectionFactory connectionFactory;
    RedisLettuceCacheConfig<String, String> config = null;
    private static boolean useSerializer = false;

    //    @PostConstruct
    @Before
    public void init() {
        GlobalSerializeMap.register();
        RedisURI uri = createRedisURIAndApplySettings(connectionFactory.getHostName(), connectionFactory.getPort());
        RedisClient client = connectionFactory.getClientConfiguration().getClientResources() //
                .map(clientResources -> RedisClient.create(clientResources, uri)) //
                .orElseGet(() -> RedisClient.create(uri));
        StatefulRedisConnection<byte[], byte[]> statefulRedisConnection = client.connect(new LettuceCodec());
        config = new RedisLettuceCacheConfig<String, String>(client, statefulRedisConnection);
        if (useSerializer) {
            config.setSerializer(GlobalSerializeMap.get(Jackson2JsonStringSerializer.IDENTITY_NUMBER));
        }
    }

    @Test
    public void putSync() {
        Cache<String, String> cache = new RedisLettuceCacheStore<>(config);
        cache.put("test", "test");
    }

    @Test
    public void getSync() {
        Cache<String, String> cache = new RedisLettuceCacheStore<>(config);
        Optional<String> test = cache.get("test");
        System.out.println(test.orElseGet(() -> "爲空"));
    }

    @Test
    public void putIfAbsentSync() {
        Cache<String, String> cache = new RedisLettuceCacheStore<>(config);
        cache.putIfAbsent("test", "test2", 200L, TimeUnit.MINUTES);
        Optional<String> test = cache.get("test");
        System.out.println(test.orElseGet(() -> "爲空"));
    }

    @Test
    public void deleteSync(){
        Cache<String, String> cache = new RedisLettuceCacheStore<>(config);
        cache.delete("test");
    }
    @Test
    public void putIfAbsentSync2(){
        Cache<String, String> cache = new RedisLettuceCacheStore<>(config);
        cache.putIfAbsent("test", "test2", 200L, TimeUnit.MINUTES);
        Optional<String> test = cache.get("test");
        System.out.println(test.orElseGet(() -> "爲空"));
    }


    private RedisURI createRedisURIAndApplySettings(String host, int port) {

        RedisURI.Builder builder = RedisURI.Builder.redis(host, port);
        builder.withPassword(connectionFactory.getPassword());
//        builder.withClientName(connectionFactory.getClientName());
        builder.withDatabase(connectionFactory.getDatabase());
        builder.withSsl(connectionFactory.isUseSsl());
        builder.withVerifyPeer(connectionFactory.isVerifyPeer());
        builder.withStartTls(connectionFactory.isStartTls());
        builder.withTimeout(connectionFactory.getClientConfiguration().getCommandTimeout());

        return builder.build();
    }
}
