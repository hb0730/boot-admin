package com.hb0730.boot.admin.sms.properties;

import com.hb0730.boot.admin.model.enums.SmsTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author bing_huang
 * @date 2020/07/03 10:44
 * @since V1.0
 */
@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "boot.admin.sms.aliyun")
@EqualsAndHashCode(callSuper = true)
public class AliyunSmsProperties extends SmsProperties {
    /**
     * 短信服务商应用公钥
     */
    private String accessKey;

    /**
     * 短信服务商应用私钥
     */
    private String securityKey;
    /**
     * 短信供应商服务域名
     */
    private String domain;
    /**
     * 区域
     */
    private String region;

    /**
     * 版本
     */
    private String version;

    @Override
    public boolean supportType(SmsTypeEnum type) {
        return SmsTypeEnum.ALIYUN.equals(type);
    }
}
