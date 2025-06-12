package com.hb0730.sys.enums;

import com.hb0730.base.Pair;
import com.hb0730.base.PairEnum;

/**
 * 菜单类型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public enum MenuTypeEnums implements PairEnum<Integer, Pair<Integer, String>> {
    /**
     * 菜单
     */
    MENU(new Pair<>(0, "菜单")),
    /**
     * frame
     */
    FRAME(new Pair<>(1, "frame")),
    /**
     * 外链
     */
    EXTERNAL(new Pair<>(2, "外链")),
    /**
     * 按钮
     */
    BUTTON(new Pair<>(3, "按钮"));

    private final Pair<Integer, String> status;

    MenuTypeEnums(Pair<Integer, String> status) {
        this.status = status;
    }

    @Override
    public Pair<Integer, String> getValue() {
        return status;
    }

    @Override
    public Integer getCode() {
        return status.getCode();
    }

    @Override
    public String getMessage() {
        return status.getMessage();
    }
}
