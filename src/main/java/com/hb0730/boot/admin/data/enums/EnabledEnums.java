package com.hb0730.boot.admin.data.enums;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
public enum EnabledEnums implements ValueEnum<Integer> {
    /**
     * 启用:1
     */
    enabled("启用", "", 1),
    /**
     * 禁用:0
     */
    un_enabled("禁用", "", 0);
    private final String name;
    private final String alias;
    private final Integer value;

    EnabledEnums(String name, String alias, Integer value) {
        this.name = name;
        this.alias = alias;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
