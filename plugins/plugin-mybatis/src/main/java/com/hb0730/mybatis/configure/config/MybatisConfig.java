package com.hb0730.mybatis.configure.config;

import com.hb0730.mybatis.core.enums.Algorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * mybatis配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Data
@ConfigurationProperties(prefix = "zoom.mybatis")
public class MybatisConfig {

    /**
     * 是否注册乐观锁插件
     */
    private boolean optimisticLocker = true;
    /**
     * 是否注册分页插件
     */
    private boolean pagination = true;
    /**
     * 加密
     */
    private Encrypt encrypt = new Encrypt();


    /**
     * 加密
     */
    @Data
    public static class Encrypt {
        /**
         * 是否加密
         */
        private boolean enable = false;

        /**
         * 加密算法,默认AES
         * <p>
         * 如果时AES加密，则使用AES/CBC/PKCS5Padding 加密算法,则需要设置key和iv
         *
         * @see Algorithm
         */
        private Algorithm algorithm = Algorithm.AES;

        /**
         * 加密key
         */
        private String key;

        /**
         * 加密向量
         */
        private String iv;
    }
}
