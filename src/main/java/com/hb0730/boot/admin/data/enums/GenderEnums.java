package com.hb0730.boot.admin.data.enums;

/**
 * 性别
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
public enum GenderEnums implements ValueEnum<Integer> {
    /**
     * 未知
     */
    unknown("未知", "保密", -1),
    /**
     * 男性
     */
    man("男", "先生", 0),
    /**
     * 女性
     */
    woman("女", "女士", 1),
    ;
    private final String name;
    private final String alias;
    private final Integer value;

    GenderEnums(String name, String alias, Integer value) {
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
