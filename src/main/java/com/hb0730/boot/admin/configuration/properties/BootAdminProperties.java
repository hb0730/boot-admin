package com.hb0730.boot.admin.configuration.properties;

import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.commons.constant.enums.TokenTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "boot.admin")
public class BootAdminProperties {
    /**
     * 项目名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 实例演示开关
     */
    private boolean demoEnabled = false;

    /**
     * 文件上传类型
     */
    private AttachmentTypeEnum attachmentType = AttachmentTypeEnum.LOCAL;

    /***
     * token存储类型
     * @see  com.hb0730.boot.admin.commons.constant.enums.TokenTypeEnum
     */
    private TokenTypeEnum tokenType = TokenTypeEnum.LOCAL;

    private TokenProperties tokenConfig = new TokenProperties();

    /**
     * token 配置
     */
    @Data
    public static class TokenProperties implements Serializable {

        private static final long serialVersionUID = -7349290787604829009L;
        /**
         * 令牌自定义标识
         */
        private String header = "Authorization";
        /**
         * 令牌秘钥
         */
        private String secret = "abcdefghijklmnopqrstuvwxyz";
        /**
         * 令牌前缀
         */
        private String prefix = "Bearer ";
        /**
         * 令牌有效期（默认30分钟）
         *
         * @see #timeUnit
         */
        private Integer expireTime = 30;

        /**
         * 刷新时间
         */
        private Integer refreshTime = 10;
        /**
         * 存储时间类型
         *
         * @see #expireTime
         */
        private TimeUnit timeUnit = TimeUnit.MINUTES;

    }
}
