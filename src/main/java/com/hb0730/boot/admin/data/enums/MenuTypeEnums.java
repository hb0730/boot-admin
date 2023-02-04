package com.hb0730.boot.admin.data.enums;

/**
 * 菜单类型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/12
 */
public enum MenuTypeEnums implements ValueEnum<Integer> {
    menu("菜单", 1),
    permission("权限", 2),
    ;
    private final String name;
    private final Integer value;

    MenuTypeEnums(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
