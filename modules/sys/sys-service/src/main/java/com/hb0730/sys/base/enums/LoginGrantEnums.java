package com.hb0730.sys.base.enums;

import com.hb0730.base.Pair;
import com.hb0730.base.PairEnum;

/**
 * 登录授权枚举
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public enum LoginGrantEnums implements PairEnum<String, Pair<String, String>> {
    PASSWORD(new Pair<>("password", "账号(身份证,邮箱,用户名)密码登录")),
    ;

    private final Pair<String, String> status;

    LoginGrantEnums(Pair<String, String> status) {
        this.status = status;
    }

    @Override
    public Pair<String, String> getValue() {
        return status;
    }

    @Override
    public String getCode() {
        return status.getCode();
    }

    @Override
    public String getMessage() {
        return status.getMessage();
    }

    /**
     * 匹配
     *
     * @param code code
     * @return true/false
     */
    public static boolean match(String code) {
        for (LoginGrantEnums value : LoginGrantEnums.values()) {
            if (value.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static LoginGrantEnums get(String code) {
        for (LoginGrantEnums value : LoginGrantEnums.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
