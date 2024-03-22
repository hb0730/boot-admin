package com.hb0730.crypto;

import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/23
 */
@Slf4j
public class RsaTest {

    @Test
    void generateKeyPair() {
        KeyPair keyPair = KeyUtil.generateKeyPair("RSA", 2048);
        String privateKeyBase64 = KeyUtil.toBase64(keyPair.getPrivate());
        String publicKeyBase64 = KeyUtil.toBase64(keyPair.getPublic());
        log.info("privateKeyBase64:{}", privateKeyBase64);
        log.info("publicKeyBase64:{}", publicKeyBase64);
    }

    @Test
    @DisplayName("公钥加密")
    void publicKeyEncrypt() {
        String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvvLhQlDRMH8kKSNIEsP08VkK8bm2fceN" +
                "/Klkr0ncMGMACr9GSmIJXaJ4oHPCp+SJUNl2fHvAiRPieUeLuE9UxHtIUMoJIvyvB6tdWBzaHe/3jZEOwc19uvu+XTEXdGt8aQkgYKC1Mka34s6V1POzHbaQ6e7qwvqq54TU+MxtIrCH77TKFlhhzTh3gWH6kdYM4btVhIMtxF/GvNRZUDURAHvroFk1n3jQ/yrhEwKwWH+iZvyFLLdEQjesI2cekyegh5Yn2ubEi49H1vpbQd3z4w6DWx9UPd1DwbP18sX1i6y431FarfWCxTggrUKfcRsuEMBvRloBNW45Vfg/CP6j3wIDAQAB";
        String privateKeyBase64 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+8uFCUNEwfyQpI0gSw" +
                "/TxWQrxubZ9x438qWSvSdwwYwAKv0ZKYgldonigc8Kn5IlQ2XZ8e8CJE+J5R4u4T1TEe0hQygki/K8Hq11YHNod7/eNkQ7BzX26+75dMRd0a3xpCSBgoLUyRrfizpXU87MdtpDp7urC+qrnhNT4zG0isIfvtMoWWGHNOHeBYfqR1gzhu1WEgy3EX8a81FlQNREAe+ugWTWfeND/KuETArBYf6Jm/IUst0RCN6wjZx6TJ6CHlifa5sSLj0fW+ltB3fPjDoNbH1Q93UPBs/XyxfWLrLjfUVqt9YLFOCCtQp9xGy4QwG9GWgE1bjlV+D8I/qPfAgMBAAECggEAHTS5D5Oc63YG6ePjmjsus9uSuPZW21YyeXrPU6BQdXYvA3XOIoJsPE4ldSU/mL5z4lHgtZmYEV9hPHuc+0vnA0Z4ASNl0lwjyWCEnn2LPxj9HEIbp7juijMHIunnVH88QDSNJzptfIskhSRj9nZv5xONhrpsXfZJPbArgwKBjtR9QaN/eV6Urb0C1a0vFtMDgJs1nWq3FNb7lGFdwdsrvsgJ2MGjxHh58IWwTZc7/ttQbP1E6pPSCm5hgKPjHZugg4H/p6kR+29gAcsy7ICu/F+pto4rBBJuIqgN/YCD75ACVsaOufEPZlBkqlFtqEHn1+pwApybD6TY5JEXLpadwQKBgQD7v4zXMxX1ofTMKsNr1FjjrXGYVc6hz2LQrtyr3JipuHOa1QsdhEZt4IHykQqIzp8TUN2OALHe/D//EG997N/F4zEonLQ66AG7qmCuw802K37B0n2m0kZ8qGoe70YD6/a5JCDPn3gRuzyEryy+cCS4FQ1wR1Kkyg4fzhdjS+fOSwKBgQDCLHWO2yMCOe51IK5fPOynlFiy83Jwl0JqbNhdE1t+hdd4NKPJ52Xp8EqFAApMKKS260ZZPpgtIf+LLwY97gaF4DFuJAb6TYD/K7OAKjTQlrPDawJ65gkif+XqfEjypLfKB1/Ncco6TMwiJ5voHI5Cp6twQlVDLkoFRJwdVIn0PQKBgG8fHgvfgNHrqyJzvc7a7GGTLZEQTyUdZYC3rrAtrzU7NcwiPcXJnUzizHlFjGDW02CzURhdNiY3MghkONJcvGmc3xYOFX/q6NjtW7/Ircw7ZrgnMU9xgauEq1L8SutwPHd8ev8qZNVu0EfBCv8EgTDAGi51TQVwKsvfLHOoDaoBAoGAUr+RpbybkcJQbdfsl6emDtB4cGKxHWxIoIQcPKYYMrYlGhQMzOlxFB4UE9Ptj4EumMlxJG05etpnRdBXb6L1Yq4lQpPV6WuIErZz+6WA9neSyi/bUbago/QvbhtjFwy9SjqUSEA691dtEw0gMu2gbqV3uRJ7tXKPlukjQpiZZd0CgYEAhkGpbHWqGUOBqEGMhGm0Sf31c/uwse7kL06l+THuiMKaaOKdE4PuKisWQXvfq3myNJ+J6jNgosHrrEWkZU8MTuhg7qF8JrvFYORcwf6vpf8DchIF3IwP/T+0VWQWiFnk3+SdDlp+DuDfpdAMbZ7LWdAz0KwYHtQyx3ALuS2hAbc=";
        String data = "admin123";

        String encryptBase64 = SecureUtil.rsa(privateKeyBase64, publicKeyBase64).encryptBase64(data, KeyType.PublicKey);
        log.info("encryptBase64:{}", encryptBase64);
    }

    @Test
    @DisplayName("私钥解密")
    void privateKeyDecryptBase64() {
        String data = "dUZdGx0YLbIKgpA8eBYfPBqBia+5r+UtCZXBdNMefNClms8pAVMdhQ2Nmf+TCW8gzCyYZId+o/sk2loKDbeQ/KAVLQUqhD0WGUBBVlj+w91Y4mykvJ2cdKu0obc6jjmKrW4PeaLIxL4rCkypFoHzXNekKGo9Vxc3yILz7/kJc82FPwzGPJE6777mr57332QD3bH+Mpk+us1IazUsUKYqtqnCAK7lNeh/S1dRXNnrz3PeyRIhkG8jpcCZa3gGoECp+YS2mu2GL46wxxdOmwkoW2q6LZRwfi8KDvXwFE7WycW93gryXeS4snqhj1AnjaqAJoPICYkqGS7cH+9LYIT5/g==";
        String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvvLhQlDRMH8kKSNIEsP08VkK8bm2fceN" +
                "/Klkr0ncMGMACr9GSmIJXaJ4oHPCp+SJUNl2fHvAiRPieUeLuE9UxHtIUMoJIvyvB6tdWBzaHe/3jZEOwc19uvu+XTEXdGt8aQkgYKC1Mka34s6V1POzHbaQ6e7qwvqq54TU+MxtIrCH77TKFlhhzTh3gWH6kdYM4btVhIMtxF/GvNRZUDURAHvroFk1n3jQ/yrhEwKwWH+iZvyFLLdEQjesI2cekyegh5Yn2ubEi49H1vpbQd3z4w6DWx9UPd1DwbP18sX1i6y431FarfWCxTggrUKfcRsuEMBvRloBNW45Vfg/CP6j3wIDAQAB";
        String privateKeyBase64 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+8uFCUNEwfyQpI0gSw" +
                "/TxWQrxubZ9x438qWSvSdwwYwAKv0ZKYgldonigc8Kn5IlQ2XZ8e8CJE+J5R4u4T1TEe0hQygki/K8Hq11YHNod7/eNkQ7BzX26+75dMRd0a3xpCSBgoLUyRrfizpXU87MdtpDp7urC+qrnhNT4zG0isIfvtMoWWGHNOHeBYfqR1gzhu1WEgy3EX8a81FlQNREAe+ugWTWfeND/KuETArBYf6Jm/IUst0RCN6wjZx6TJ6CHlifa5sSLj0fW+ltB3fPjDoNbH1Q93UPBs/XyxfWLrLjfUVqt9YLFOCCtQp9xGy4QwG9GWgE1bjlV+D8I/qPfAgMBAAECggEAHTS5D5Oc63YG6ePjmjsus9uSuPZW21YyeXrPU6BQdXYvA3XOIoJsPE4ldSU/mL5z4lHgtZmYEV9hPHuc+0vnA0Z4ASNl0lwjyWCEnn2LPxj9HEIbp7juijMHIunnVH88QDSNJzptfIskhSRj9nZv5xONhrpsXfZJPbArgwKBjtR9QaN/eV6Urb0C1a0vFtMDgJs1nWq3FNb7lGFdwdsrvsgJ2MGjxHh58IWwTZc7/ttQbP1E6pPSCm5hgKPjHZugg4H/p6kR+29gAcsy7ICu/F+pto4rBBJuIqgN/YCD75ACVsaOufEPZlBkqlFtqEHn1+pwApybD6TY5JEXLpadwQKBgQD7v4zXMxX1ofTMKsNr1FjjrXGYVc6hz2LQrtyr3JipuHOa1QsdhEZt4IHykQqIzp8TUN2OALHe/D//EG997N/F4zEonLQ66AG7qmCuw802K37B0n2m0kZ8qGoe70YD6/a5JCDPn3gRuzyEryy+cCS4FQ1wR1Kkyg4fzhdjS+fOSwKBgQDCLHWO2yMCOe51IK5fPOynlFiy83Jwl0JqbNhdE1t+hdd4NKPJ52Xp8EqFAApMKKS260ZZPpgtIf+LLwY97gaF4DFuJAb6TYD/K7OAKjTQlrPDawJ65gkif+XqfEjypLfKB1/Ncco6TMwiJ5voHI5Cp6twQlVDLkoFRJwdVIn0PQKBgG8fHgvfgNHrqyJzvc7a7GGTLZEQTyUdZYC3rrAtrzU7NcwiPcXJnUzizHlFjGDW02CzURhdNiY3MghkONJcvGmc3xYOFX/q6NjtW7/Ircw7ZrgnMU9xgauEq1L8SutwPHd8ev8qZNVu0EfBCv8EgTDAGi51TQVwKsvfLHOoDaoBAoGAUr+RpbybkcJQbdfsl6emDtB4cGKxHWxIoIQcPKYYMrYlGhQMzOlxFB4UE9Ptj4EumMlxJG05etpnRdBXb6L1Yq4lQpPV6WuIErZz+6WA9neSyi/bUbago/QvbhtjFwy9SjqUSEA691dtEw0gMu2gbqV3uRJ7tXKPlukjQpiZZd0CgYEAhkGpbHWqGUOBqEGMhGm0Sf31c/uwse7kL06l+THuiMKaaOKdE4PuKisWQXvfq3myNJ+J6jNgosHrrEWkZU8MTuhg7qF8JrvFYORcwf6vpf8DchIF3IwP/T+0VWQWiFnk3+SdDlp+DuDfpdAMbZ7LWdAz0KwYHtQyx3ALuS2hAbc=";
        String decryptStr = SecureUtil.rsa(privateKeyBase64, publicKeyBase64).decryptStr(data, KeyType.PrivateKey);
        log.info("decryptStr:{}", decryptStr);
    }
}
