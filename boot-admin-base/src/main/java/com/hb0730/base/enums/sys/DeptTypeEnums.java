package com.hb0730.base.enums.sys;

import com.hb0730.base.enums.ValueEnum;
import lombok.Getter;

/**
 * 部门类型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Getter
public enum DeptTypeEnums implements ValueEnum<Integer> {
    GROUP(1, "公司"),
    BRANCH(2, "分公司"),
    DEPT(3, "部门");
    private final Integer value;
    private final String desc;

    DeptTypeEnums(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
