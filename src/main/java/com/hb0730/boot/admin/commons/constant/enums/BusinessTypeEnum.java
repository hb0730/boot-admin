package com.hb0730.boot.admin.commons.constant.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 业务操作类型
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public enum BusinessTypeEnum implements ValueEnum<Integer> {
    /**
     * 其它
     */
    OTHER("其他", 0),

    /**
     * 新增
     */
    INSERT("新增", 1),

    /**
     * 修改
     */
    UPDATE("修改", 2),

    /**
     * 删除
     */
    DELETE("修改", 3),

    /**
     * 授权
     */
    GRANT("授权", 4),

    /**
     * 导出
     */
    EXPORT("导出", 5),

    /**
     * 导入
     */
    IMPORT("导入", 6),

    /**
     * 强退
     */
    FORCE("强退", 7),
    /**
     * 清空数据
     */
    CLEAN("清空数据", 8),
    ;
    @Getter
    @Setter
    private String name;
    @Setter
    private Integer value;

    BusinessTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }}
