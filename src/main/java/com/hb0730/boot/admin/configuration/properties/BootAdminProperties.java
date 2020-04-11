package com.hb0730.boot.admin.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
    private boolean demoEnabled;

    /**
     * 文件上传类型
     */
    private Integer attachmentType = 0;

    /***
     * token存储类型
     * @see  com.hb0730.boot.admin.commons.constant.enums.TokenTypeEnum
     */
    private Integer tokenType=0;
}
