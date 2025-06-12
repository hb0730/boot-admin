package com.hb0730.sys.base.enums;


import com.hb0730.base.Pair;
import com.hb0730.base.PairEnum;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
public enum LoginTokenStatusEnums implements PairEnum<Integer, Pair<Integer, String>> {
    OK(new Pair<>(1, "正常")),
    OTHER_DEVICE(new Pair<>(2, "其他设备登录")),
    SESSION_INVALID(new Pair<>(3, "强制下线")),
    ;

    LoginTokenStatusEnums(Pair<Integer, String> status) {
        this.status = status;
    }

    private final Pair<Integer, String> status;

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
