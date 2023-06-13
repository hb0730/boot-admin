package com.hb0730.boot.admin.data.enums;

/**
 * 配置类型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
public enum ConfigTypeEnums implements ValueEnum<Integer> {
    CONFIG_TYPE01(1, "系统配置"),
    CONFIG_TYPE02(2, "业务配置"),
    ;
    private final Integer value;
    private final String name;

    ConfigTypeEnums(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
