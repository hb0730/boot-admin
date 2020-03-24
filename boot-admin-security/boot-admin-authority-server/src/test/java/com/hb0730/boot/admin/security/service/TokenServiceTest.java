package com.hb0730.boot.admin.security.service;

import com.google.common.collect.Maps;
import com.hb0730.cloud.admin.commons.utils.SecurityConstants;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

public class TokenServiceTest {


    @Test
    public void ceateToken() {
        UUID uuid = UUID.randomUUID();
        String s = extractKey(uuid.toString());
    }

    /**
     * <p>
     * 生成token
     * </p>
     *
     * @return token令牌
     */
    private String extractKey(String key) {
        Map<String, String> maps = Maps.newHashMap();
        maps.put("secret", "test");
        maps.put(SecurityConstants.LOGIN_USER_KEY, key);
        return createToken(maps);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param values 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, String> values) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(values.toString().getBytes(StandardCharsets.UTF_8));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", nsae);
        }
    }
}
