package com.hb0730.base.enums;

import lombok.Getter;

/**
 * 机构管理枚举类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
public class OrgEnum {

    /**
     * 机构类型
     */
    @Getter
    public enum OrgTypeEnums implements ValueEnum<Integer> {
        /**
         * 机构类型:厂商
         */
        BAS_ORG_TYPE_01("厂商", 1),
        /**
         * 机构类型:网点/机构
         */
        BAS_ORG_TYPE_02("网点", 2),
        ;
        private final String name;
        private final Integer value;

        OrgTypeEnums(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }
}