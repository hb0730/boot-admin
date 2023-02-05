package com.hb0730.boot.admin.base.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * ras util
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/5
 */
@UtilityClass
public class RsaUtil {

    /**
     * 公钥加密，私钥解密
     *
     * @param text            待加密的信息
     * @param publicKeyBase64 公钥
     * @return base64后的结果
     */
    public String encryptByPublicKey(String text, String publicKeyBase64) {
        RSA rsa = new RSA();
        PublicKey publicKey = KeyUtil.generateRSAPublicKey(Base64.decode(publicKeyBase64));
        rsa.setPublicKey(publicKey);
        return rsa.encryptBase64(text, KeyType.PublicKey);
    }

    /**
     * 私钥解密
     *
     * @param text             待解密的信息，base64
     * @param privateKeyBase64 私钥
     * @return 解密后的结果
     */
    public String decryptByPrivateKey(String text, String privateKeyBase64) {
        RSA rsa = new RSA();
        PrivateKey privateKey = KeyUtil.generateRSAPrivateKey(Base64.decode(privateKeyBase64));
        rsa.setPrivateKey(privateKey);
        return rsa.decryptStr(text, KeyType.PrivateKey);
    }

    /**
     * 私钥解密，公钥解密
     *
     * @param text             待解密信息
     * @param privateKeyBase64 私钥
     * @return 加密后的结果, base64类型
     */
    public String encryptByPrivateKey(String text, String privateKeyBase64) {
        RSA rsa = new RSA();
        PrivateKey privateKey = KeyUtil.generateRSAPrivateKey(Base64.decode(privateKeyBase64));
        rsa.setPrivateKey(privateKey);
        return rsa.encryptBase64(text, KeyType.PrivateKey);
    }

    /**
     * 公钥解密
     *
     * @param text            待解密信息,base64
     * @param publicKeyBase64 公钥
     * @return 解密后的结果
     */
    public String decryptByPublicKey(String text, String publicKeyBase64) {
        RSA rsa = new RSA();
        PublicKey publicKey = KeyUtil.generateRSAPublicKey(Base64.decode(publicKeyBase64));
        rsa.setPublicKey(publicKey);
        return rsa.decryptStr(text, KeyType.PublicKey);
    }

    /**
     * 构建RSA密钥对
     *
     * @return .
     */
    public RsaKeyPair generateKey() {
        KeyPair keyPair = KeyUtil.generateKeyPair("RSA", 1024);

        String publicBase64Key = KeyUtil.toBase64(keyPair.getPublic());
        String privateBase64Key = KeyUtil.toBase64(keyPair.getPrivate());
        return new RsaKeyPair(publicBase64Key, privateBase64Key);
    }


    /**
     * RSA密钥对对象
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RsaKeyPair {
        private String publicKeyBase64;
        private String privateKeyBase64;

    }
}
