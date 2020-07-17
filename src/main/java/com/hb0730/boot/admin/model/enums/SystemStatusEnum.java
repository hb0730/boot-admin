package com.hb0730.boot.admin.model.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public enum SystemStatusEnum implements ValueEnum<Integer> {
    /**
     * 失败
     */
    FAIL("失败", 0),
    /**
     * 成功
     */
    SUCCESS("成功", 1);
    /**
     *
     */
    @Getter
    @Setter
    private String name;
    /**
     *
     */
    @Setter
    private Integer value;

    SystemStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }}
