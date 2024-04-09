package com.hb0730.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "boot.admin.security")
public class SecurityProperties {
    /**
     * token header 名称
     * 默认: X-Access-Token
     */
    private String tokenHeader = "X-Access-Token";
    /**
     * 租户
     */
    private String tenantHeader = "X-Tenant";
    /**
     * token header 前缀
     * 默认: ""
     */
    private String tokenHeaderPrefix;
    /**
     * token 有效期，单位秒
     * 默认2小时
     */
    private int tokenValidity = 2 * 60 * 60;

    /**
     * 获取token有效期
     *
     * @return token有效期
     */
    public int getTokenValidity() {
        Assert.isTrue(tokenValidity > 0, "tokenValidity must be greater than 0");
        return tokenValidity;
    }

    /**
     * 是否延迟token
     * 默认true
     */
    private boolean delayToken = true;

    /**
     * token 续期检查,单位秒
     * 默认: 30分钟
     */
    private Integer tokenDetect = 60 * 30;
    /**
     * token 缓存前缀
     * 默认: access_token
     */
    private String tokenCachePrefix = "access_token";
    /**
     * jwt 秘钥
     */
    private String jwtSecret = "e05b35d375212afc4831827cf06ab5451662aebefdbccf35b9fd64dc91f4ad0efdb4b9579ee0f0d13b37e5b19cf57a47cf5ffead55364340838936ab38c603892efda59e80d0edb7a96f8c0a542d9b2193506b47a7213d3aad33409726295de49e002d2277b5b363e748c88893ecb44704253d9fd2e93061ba83e6c1530256facfb390071d8a934343120494cacac6ad17ff1dc5b59be4f4e788434f2abed9948476638b1f8bf2cf7d4a90f17b7704d4f55907dc8b4d1ab077d2aeff0729297d3e9fd432232018cca90feb1b958a34e6d29d5ada81f82a5c927d2e8da390d74ca6abf0293cdafcf24d5cffac604e7540f3c281f405bc8e8e3e704bcb88435566";

    /**
     * 登录配置
     */
    private Login login = new Login();

    @Data
    public static class Login {
        /**
         * 登录私钥 base64
         */
        private String rsaPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDOC1acff0tIjciaf+VewsqOb4YoZE5r7aDxHQOva8TjogCE0aZc8CZHAFZEJOrH7I2FHyBQbJasz1gCZjIi37ke3cY6M9elmYqqmoHvflwiPSIWIewg9gng6MhMRvP+iUM/YtZ2tdivz3Enr2X5ju4LMcPQ2NoHZPeBlBpIqOCpVGj04c68weduGA/87owQpCD7rex2QHBhVZ9WXntQN+FQKymLNB+blbaEKnSjbeEgrl+hVEqdXXN7gi/6EM5G9qxnZc6Q/VIpo4yXYENpCfkdrAiMxMvUqriSFHV/sRmI9qR/DGLgA4WFdpKNnAnK5CmBawhXUV9zrWfTNAgVnFPAgMBAAECggEARsuSiNapRPa6pvJwxPWwEmQgsungskdzmLbJgb8fBsM0bDIfUd550KHUvLt7YZLUcsx7MxZVp+BqODNT8aQsh2j8tOxZRBC9Le+72sklicsk6iwxAy9p97m9NUxgWAeE73+2f7s3wlAfcw1v619KKbAI/W+UGlP/WIwm2Nj0AZEaJxEa9hXU5OYWYHi8FTUfgqTNrPNUqYguFZ9cJZKj0W/ImPktUtSEfsyGXIzNFenrBNXBE3xPLjByvQdElAPVQkkyFaDtLE6Vp8jYzA5RFwJWzZO/7C+4TNOfncq71XEmLxNzBJvsREsxqsMz2Vpw00saeA0qQp6q6W5Bwfrs8QKBgQDfbGADc6wJLUTVTiDmDldvMvpVABeKvOYCZ4vnZq8W3u9F0YbMm0SRu4UixHg2i9LmbR7FN/lDMMAPR3rrQvdwrxNyUtlbk7OyFvacQmRC4+oz3L+cCabr5k1Xy23b1ZPFFPPmFOX+9/s6d3U4OZHkGfnEzPTWQfrhstAPeQbHEwKBgQDsFkNbUKuSp+WIjQQjwmhnpULfDD0u7kjippQBBMaQa1d+sj/aUshe7DFiKayHjqDaSjZj0wveF/FebiZW2wMLPT4JuTPcynAwRs1GAjdDmOVG6FukcNJqsM+/efmkGbnm9xc7bsrMSj5Mt/ulhBKoUihxFRDYvM5UqWBFmKNIVQKBgGgK+N9Co4+WdCJ9Apb/gLn43Fhu9YeXFelyvNTinZCh+nzh6MspVxIiaApbwbts+6ZwlE61FS+z6Qx0dgpOohnofb7UVAHwU1MQnIEB7dy0dCD4oIYSfJgIJGZJBnzEBc9WRYoUm1JTurtsW7r2HDlD22tJIo8QJLur4HYEHyLfAoGAdwzi+nE7k8DGpo01wTKPo3RavE9XqwrOBj0p9TmLR9hZTrJkMJv+Hp/MZ0vMjK8HvgweUUhje6NnO88f1jyq1EgVuKGD3ZrX6ABMEzma1KONOqyBSfL6qIJwwIWptW6jqCyBsp6z9UCbGeTNKTHolqiXbC7LSAAtq9OdkHrgG+ECgYA+g06MOZsXf/3LeNghRKHQlF1j5yYhiCoHeQydu0n5juAQ4BaSSXX8jwXJG0MSRbsf+k6Ml30pTg5iGWgXGczX0h4CTyt7aZF8w3DiUlOVdcgUjVoA9Sp3W+th8JqO1weV5FcnA6g8GVsz/WFwn3OLJEzR/9/j6TirJrkHeN0gQg==";
        /**
         * 登录公钥 base64
         */
        private String rsaPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzgtWnH39LSI3Imn/lXsLKjm+GKGROa+2g8R0Dr2vE46IAhNGmXPAmRwBWRCTqx+yNhR8gUGyWrM9YAmYyIt+5Ht3GOjPXpZmKqpqB735cIj0iFiHsIPYJ4OjITEbz/olDP2LWdrXYr89xJ69l+Y7uCzHD0NjaB2T3gZQaSKjgqVRo9OHOvMHnbhgP/O6MEKQg+63sdkBwYVWfVl57UDfhUCspizQfm5W2hCp0o23hIK5foVRKnV1ze4Iv+hDORvasZ2XOkP1SKaOMl2BDaQn5HawIjMTL1Kq4khR1f7EZiPakfwxi4AOFhXaSjZwJyuQpgWsIV1Ffc61n0zQIFZxTwIDAQAB";
    }

    /**
     * 获取token header 名称
     *
     * @return header+空格
     */
    public String getHeaderStartWith() {
        return tokenHeaderPrefix == null ? "" : tokenHeaderPrefix + " ";
    }
}