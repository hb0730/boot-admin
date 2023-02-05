package com.hb0730.boot.admin.base.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class RsaUtilTest {

    @Test
    @DisplayName("公钥加密私钥解密")
    void encryptByPublicKey() {
        RsaUtil.RsaKeyPair keyPair = RsaUtil.generateKey();
        String test = "测试测试";
        String encryptData = RsaUtil.encryptByPublicKey(test, keyPair.getPublicKeyBase64());
        log.info("【公钥加密】加密后内容: {}", encryptData);
        String decryptData = RsaUtil.decryptByPrivateKey(encryptData, keyPair.getPrivateKeyBase64());
        log.info("【私钥解密】解密后的内容: {}", decryptData);
        Assertions.assertEquals(test, decryptData, "解密后的内容与原内容不一致");
    }


    @Test
    @DisplayName("私钥加密公钥解密")
    void encryptByPrivateKey() {
        RsaUtil.RsaKeyPair keyPair = RsaUtil.generateKey();
        String test = "测试123101923爱上";
        String encryptData = RsaUtil.encryptByPrivateKey(test, keyPair.getPrivateKeyBase64());
        log.info("【私钥加密】加密后内容: {}", encryptData);
        String decryptData = RsaUtil.decryptByPublicKey(encryptData, keyPair.getPublicKeyBase64());
        log.info("【公钥解密】解密后的内容: {}", decryptData);
        Assertions.assertEquals(test, decryptData, "解密后的内容与原内容不一致");
    }

    @Test
    void decryptByPrivateKey() {
        String data=
            "13a461ef6a0c3847c01824b5d8707f140c3f4214c96ac95783116839893e2bf50cb06ed3beb483066a8ecf8c94c4910b920c4df4db96981640c84175fc8369bfd57fe9ca2c31413f014cea4cec631b5875216cf7593e512723bc90fa2e3d84e6e1402ac50419c6e76bbf5d7aacffdbe0f3558032764e3bf6e4c07beb807a132a";
        RsaUtil.decryptByPrivateKey(data,"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMrQcDp20jeUyb5rLO3jg3R6UlA0/U6WdzSXn7T4Sz7Z/F5hxeYiJ+F7+WsXbhGF085O4xivW0eeSZkrEKiO/ssaXELfE106vS3J9izYQNKKMM92chalWWSb7nLNLjhHnERiZpZKTdjRmqDc1NeyO7UoY3dIX1vmetPClFKeZDZ9AgMBAAECgYA8MOQ91anJ6RR+uNwatdz7opnvR+qteiZiq2UwlkfunU8yy52qERT47Iw3Zjq6ZJdZsTvgxZo3hRieppNJEzc2k8tQNtSEGG/WqSVhz3E5QuFxE0nse7qpEG2vuJH6CoyXZ4T3dqL+AybUxxqXDcke+YEAZrVEPqPeUF1B1NgK2QJBAMxIXI/2nXA5teIBktt7jAoao6fsapsi+yrkvBNVKjTWZbb/dAUGp5Jh+2izkQAZZoDArZleIfUaL/F6oDvDYNcCQQD+KOfwcSGmm821hK+dYq7fjd3TYQF96toRNYPJLCALrvf3IcHnbWf486A+CuzSMbkZuVlCDfReubvY81cec3TLAkBF+ygSfWZz0qFXWjioDcvsjjGwThI7MSGgERnI+azqyuOvbBWfvybaw8wvkwxCX3E4/Ei8OH4jjCQOcna/4m+jAkB5oxAmBa7KMwTHW8JEe4cf3KCOWn3BSpWk6MyyidioXsuzzcckQDsjbU2Cg+4FRuDEi+1I7K7IavGZChTmsPDzAkAudsivcNXIiWJBVuBwywwnMxLytK856FW0yEf/ffIs4NKFXUubCuCer0sCCSVeYXdzCGv2nnPxce5oqfcerHK1");
    }
}
