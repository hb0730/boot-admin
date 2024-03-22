package com.hb0730.base.enums.sys;

import com.hb0730.base.enums.ValueEnum;
import lombok.Getter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/20
 */
@Getter
public enum EnabledEnums implements ValueEnum<Integer> {
    ENABLE(1, "启用"),
    DISABLE(0, "禁用"),
    ;
    private final Integer value;
    private final String name;

    EnabledEnums(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
