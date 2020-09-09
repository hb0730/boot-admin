package com.hb0730.boot.admin.commons.enums;

import lombok.Getter;

/**
 * 业务操作类型
 *
 * @author bing_huang
 * @since 3.0.0
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
    DELETE("删除", 3),

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

    /**
     * 执行
     */
    EXECUTOR("执行", 9),
    ;
    @Getter
    private final String name;
    private final Integer value;

    BusinessTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
