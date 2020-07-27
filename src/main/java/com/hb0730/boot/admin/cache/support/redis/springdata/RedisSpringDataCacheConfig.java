package com.hb0730.boot.admin.cache.support.redis.springdata;

import com.hb0730.boot.admin.cache.config.CacheConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author bing_huang
 * @date 2020/07/20 7:40
 * @since V1.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class RedisSpringDataCacheConfig<K, V> extends CacheConfig<K, V> {

    private RedisConnectionFactory connectionFactory;
}
