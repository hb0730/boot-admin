package com.hb0730.sys.base.define.cache;


import com.hb0730.cache.core.define.CacheKeyBuilder;
import com.hb0730.cache.core.define.CacheKeyDefine;
import com.hb0730.cache.core.define.struct.RedisCacheStruct;
import com.hb0730.security.UserInfo;

import java.util.concurrent.TimeUnit;

import static com.hb0730.base.pool.ConstPool.LOGIN_USER_CACHE_PREFIX;


/**
 * 用户缓存定义
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
public interface UserCacheKeyDefine {
    /**
     * 用户信息
     * <p>
     * key: user:info:{id}
     * desc: 用户信息 {id}
     * type: UserInfo
     * struct: STRING
     * timeout: 8小时
     */
    CacheKeyDefine USER_INFO = CacheKeyBuilder
            .create()
            .key(LOGIN_USER_CACHE_PREFIX + ":{}")
            .desc("用户信息 {id or username}")
            .type(UserInfo.class)
            .struct(RedisCacheStruct.STRING)
            .timeout(8, TimeUnit.HOURS)
            .build();

    /**
     * 用户登录失败次数
     * <p>
     * key: login:failure:account:{username}
     * desc: 用户登录失败次数 {username}
     * type: Integer
     * struct: STRING
     * timeout: 1天
     */
    CacheKeyDefine LOGIN_FAILURE = CacheKeyBuilder.create()
            .key("login:failure:account:{}")
            .desc("用户登录失败次数 {username}")
            .type(Integer.class)
            .struct(RedisCacheStruct.STRING)
            .timeout(1, TimeUnit.DAYS)
            .build();

    CacheKeyDefine LOGIN_TOKEN = CacheKeyBuilder
            .create()
            .key("user:token:{}:{}")
            .desc("用户登录 token ${id} ${time}")
            .type(String.class)
            .struct(RedisCacheStruct.STRING)
            .timeout(1, TimeUnit.DAYS)
            .build();

    CacheKeyDefine LOGIN_REFRESH = new CacheKeyBuilder()
            .key("user:refresh:{}:{}")
            .desc("用户刷新 token ${id} ${time}")
            .type(String.class)
            .struct(RedisCacheStruct.STRING)
            .timeout(32, TimeUnit.HOURS)
            .build();


    CacheKeyDefine LOGIN_PHONE_CODE = new CacheKeyBuilder()
            .key("user:phone:code:{}")
            .desc("用户手机号验证码 ${phone}")
            .type(String.class)
            .struct(RedisCacheStruct.STRING)
            .timeout(5, TimeUnit.MINUTES)
            .build();


}
