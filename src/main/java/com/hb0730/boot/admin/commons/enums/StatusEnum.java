package com.hb0730.boot.admin.commons.enums;

import lombok.Getter;

/**
 * 状态{成功，失败}
 *
 * @author bing_huang
 * @since 3.0.0
 */
public enum StatusEnum implements ValueEnum<Integer> {
    /**
     * 成功
     */
    success("成功", 1),
    /**
     * 失败
     */
    FAIL("失败", 0);
    @Getter
    private final String name;
    private final Integer value;

    StatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
