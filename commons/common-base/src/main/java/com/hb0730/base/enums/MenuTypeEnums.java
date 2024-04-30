package com.hb0730.base.enums;

import lombok.Getter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Getter
public enum MenuTypeEnums implements ValueEnum<Integer> {
    Menu_1(1, "菜单"),
    Menu_2(2, "iframe"),
    Menu_3(3, "外链"),
    Menu_4(4, "按钮"),
    ;
    private final Integer value;
    private final String name;

    MenuTypeEnums(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public static MenuTypeEnums of(Integer value) {
        for (MenuTypeEnums enums : MenuTypeEnums.values()) {
            if (enums.getValue().equals(value)) {
                return enums;
            }
        }
        return null;
    }

}