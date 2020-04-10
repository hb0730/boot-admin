package com.hb0730.boot.admin.commons.constant.enums;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public enum AttachmentTypeEnum implements ValueEnum<Integer> {

    /**
     * 本地
     */
    LOCAL(0),
    /**
     * 阿里云
     */
    ALIOSS(1);

    private Integer value;

    AttachmentTypeEnum(Integer value) {
        this.value = value;
    }


    /**
     * Get enum value.
     *
     * @return enum value
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
