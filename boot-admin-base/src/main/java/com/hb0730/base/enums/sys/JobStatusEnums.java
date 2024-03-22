package com.hb0730.base.enums.sys;

import com.hb0730.base.enums.ValueEnum;
import lombok.Getter;

/**
 * 任务状态
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Getter
public enum JobStatusEnums implements ValueEnum<Integer> {
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private final Integer value;
    private final String desc;

    JobStatusEnums(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public Integer getValue() {
        return this.value;
    }
}
