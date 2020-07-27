package com.hb0730.boot.admin.cache.impl;

import com.hb0730.boot.admin.cache.impl.remote.RedisLettuceCacheStore;
import com.hb0730.boot.admin.cache.support.redis.lettuce.LettuceCodec;
import com.hb0730.boot.admin.cache.support.redis.lettuce.RedisLettuceCacheConfig;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class RedisLettuceCacheStoreTest {
    @Autowired
    private LettuceConnectionFactory connectionFactory;
    RedisLettuceCacheConfig config = null;

    @PostConstruct
    public void init() {
        RedisURI uri = createRedisURIAndApplySettings(connectionFactory.getHostName(), connectionFactory.getPort());
        RedisClient client = connectionFactory.getClientConfiguration().getClientResources() //
                .map(clientResources -> RedisClient.create(clientResources, uri)) //
                .orElseGet(() -> RedisClient.create(uri));
        StatefulRedisConnection<byte[], byte[]> statefulRedisConnection = client.connect(new LettuceCodec());
        config = new RedisLettuceCacheConfig(client, statefulRedisConnection);
    }

    @Test
    public void putSync() {
        RedisLettuceCacheStore<String, String> cache = new RedisLettuceCacheStore<>(config);
        cache.put("test", "test");
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
