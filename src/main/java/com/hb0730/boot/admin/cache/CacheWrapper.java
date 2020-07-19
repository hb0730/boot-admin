package com.hb0730.boot.admin.cache;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * cache entity
 *
 * @author bing_huang
 * @date 2020/07/18 13:28
 * @since V1.0
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CacheWrapper<V> implements Serializable {
    /**
     * Cache data
     */
    private V data;

    /**
     * Expired time.
     */
    private Date expireAt;

    /**
     * Create time.
     */
    private Date createAt;
}
