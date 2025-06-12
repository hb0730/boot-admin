package com.hb0730.desensitize.core.desensitization;


import com.hb0730.desensitize.core.enums.DesensitizationType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
public class DesensitizationFactory {
    private static final Map<String, Desensitization<?>> map = new HashMap<>();

    static {
        map.put(DesensitizationType.CHINESE_NAME.name(), new ChineseNameDesensitization());
        map.put(DesensitizationType.ID_CARD.name(), new IdCardDesensitization());
        map.put(DesensitizationType.FIXED_PHONE.name(), new FixedPhoneDesensitization());
        map.put(DesensitizationType.MOBILE.name(), new MobileDesensitization());
        map.put(DesensitizationType.ADDRESS.name(), new AddressDesensitization());
        map.put(DesensitizationType.EMAIL.name(), new EmailDesensitization());
        map.put(DesensitizationType.BANK_CARD.name(), new BankCardDesensitization());
        map.put(DesensitizationType.NONE.name(), new NoneDesensitization());
    }

    /**
     * 获取脱敏处理类
     *
     * @param type 脱敏类型
     * @return Desensitization
     */
    @SuppressWarnings("unchecked")
    public static <T> Desensitization<T> getDesensitization(DesensitizationType type) {
        return (Desensitization<T>) map.get(type.name());
    }

    /**
     * 获取脱敏处理类
     *
     * @param type 脱敏类型
     * @return Desensitization
     */
    @SuppressWarnings("unchecked")
    public static <T> Desensitization<T> getDesensitization(String type) {
        return (Desensitization<T>) map.get(type);
    }

    /**
     * 添加脱敏处理类
     *
     * @param type            脱敏类型
     * @param desensitization 脱敏处理类
     */
    public static <T> void registerDesensitization(DesensitizationType type,
                                                   Desensitization<T> desensitization) {
        map.put(type.name(), desensitization);
    }

    /**
     * 添加脱敏处理类
     *
     * @param type            脱敏类型
     * @param desensitization 脱敏处理类
     */
    public static <T> void registerDesensitization(String type,
                                                   Desensitization<T> desensitization) {
        map.put(type, desensitization);
    }
}
