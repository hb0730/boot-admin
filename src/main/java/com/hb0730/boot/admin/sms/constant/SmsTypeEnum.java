package com.hb0730.boot.admin.sms.constant;

import com.hb0730.boot.admin.commons.constant.enums.ValueEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * sms类型
 *
 * @author bing_huang
 * @date 2020/07/01 8:56
 * @since V1.0
 */
public enum SmsTypeEnum implements ValueEnum<String> {
    /**
     * 阿里云
     */
    ALIYUN("aliyun", "阿里云短信服务"),
    ;
    @Setter
    private String value;
    @Getter
    @Setter
    private String desc;

    SmsTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
