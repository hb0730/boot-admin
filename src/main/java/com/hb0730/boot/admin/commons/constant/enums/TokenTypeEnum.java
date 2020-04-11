package com.hb0730.boot.admin.commons.constant.enums;

/**
 * <p>
 * token存储类型
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */

public enum TokenTypeEnum implements ValueEnum<Integer> {
    /**
     * 内存
     */
    LOCAL(0),
    /**
     * redis
     */
    REDIS(1),
    ;
    private Integer value;

    TokenTypeEnum(Integer value) {
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
