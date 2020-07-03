package com.hb0730.boot.admin.commons.constant.enums;

/**
 * @author bing_huang
 * @date 2020/07/03 10:42
 * @since V1.0
 */
public enum SmsTypeEnum implements ValueEnum<String> {
    /**
     * 阿里云短信服务商
     */
    ALIYUN("aliyun", "阿里云短信服务商"),
    ;

    private String value;
    private String desc;

    SmsTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
