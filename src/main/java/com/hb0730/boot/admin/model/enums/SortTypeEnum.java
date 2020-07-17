package com.hb0730.boot.admin.model.enums;

import lombok.Setter;

/**
 * 排序规则
 *
 * @author bing_huang
 * @date 2020/06/26 12:18
 * @since V1.0
 */
public enum SortTypeEnum implements ValueEnum<String> {
    /**
     * 降序
     */
    DESC("DESC"),
    /**
     * 升序
     */
    ASC("ASC");

    @Setter
    private String value;

    SortTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
