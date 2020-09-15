package com.hb0730.boot.admin.commons.enums;

/**
 * email properties
 *
 * @author bing_huang
 * @since 3.0.0
 */
public enum EmailPropertiesEnum implements PropertyEnum {
    /**
     * email sender host
     */
    HOST("email_host", String.class, ""),
    /**
     * Email sender protocol
     */
    PROTOCOL("email_protocol", String.class, "smtp"),

    /**
     * SSL port
     */
    SSL_PORT("email_ssl_port", Integer.class, "465"),

    /**
     * Email Sender username
     */
    USERNAME("email_username", String.class, ""),

    /**
     * Email Sender password
     */
    PASSWORD("email_password", String.class, ""),

    /**
     * Email Sender name
     */
    FROM_NAME("email_from_name", String.class, ""),

    /**
     * Is enabled email sender
     */
    ENABLED("email_enabled", Integer.class, "0");;

    private final String value;
    private final Class<?> type;
    private final String defaultValue;

    EmailPropertiesEnum(String value, Class<?> type, String defaultValue) {
        this.value = value;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String defaultValue() {
        return defaultValue;
    }

}
