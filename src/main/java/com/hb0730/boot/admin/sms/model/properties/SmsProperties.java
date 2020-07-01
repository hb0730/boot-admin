package com.hb0730.boot.admin.sms.model.properties;

import com.hb0730.boot.admin.sms.constant.SmsTypeEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * sms 配置
 *
 * @author bing_huang
 * @date 2020/07/01 9:29
 * @since V1.0
 */
@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "boot.admin.sms")
public class SmsProperties {


    /**
     * msm类型
     */
    private SmsTypeEnum smsType = SmsTypeEnum.ALIYUN;
    /**
     * 短信应用商服务地址
     */
    private String appid;

    /**
     * 短信供应商服务域名
     */
    private String domain;

    /**
     * 短信服务商应用公钥
     */
    private String accessKey;

    /**
     * 短信服务商应用私钥
     */
    private String securityKey;

    /**
     * 区域
     */
    private String region;

    /**
     * 短信服务商短信签名
     */
    private String sign;

    /**
     * 版本
     */
    private String version;

    /**
     * 其余参数
     */
    private Map<String, String> maps;

}
