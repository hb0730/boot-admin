package com.hb0730.boot.admin.commons.enums;

/**
 * 状态
 *
 * @author bing_huang
 * @since 3.0.0
 */
public enum EnabledEnum implements ValueEnum<Integer> {
    /**
     * 启用
     */
    ENABLED(1),
    /**
     * 禁用
     */
    UN_ENABLED(0);

    private final Integer value;

    EnabledEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
