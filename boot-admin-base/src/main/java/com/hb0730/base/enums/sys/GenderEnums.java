package com.hb0730.base.enums.sys;

import com.hb0730.base.enums.ValueEnum;
import lombok.Getter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/20
 */
@Getter
public enum GenderEnums implements ValueEnum<Integer> {
    UNKNOWN(0, "未知"),
    MAN(1, "男"),
    WOMAN(2, "女"),
    ;
    private final Integer value;
    private final String name;

    GenderEnums(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
