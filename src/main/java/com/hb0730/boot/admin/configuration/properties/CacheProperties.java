package com.hb0730.boot.admin.configuration.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author bing_huang
 * @date 2020/07/28 7:47
 * @since V1.0
 */
@Data
@EqualsAndHashCode
@ToString
public class CacheProperties implements Serializable {
    private String cache = "memory";
    private String redisPassword = "";
    private Integer redisPort = 6379;
    private String redisHost = "localhost";
    private ArrayList<String> cacheRedisNodes = new ArrayList<>();
}
