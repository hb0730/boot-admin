package com.hb0730.boot.admin.data.enums;

/**
 * 操作
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/12
 */
public enum OperateEnums implements ValueEnum<Integer> {
    operate_null("", 0),
    /**
     * 查询
     */
    operate_query("查询", 1),
    /**
     * 添加
     */
    operate_add("添加", 2),
    /**
     * 更新
     */
    operate_edit("更新", 3),
    /**
     * 删除
     */
    operate_delete("删除", 4),
    /**
     * 导出
     */
    operate_export("导出", 5),
    /**
     * 导入
     */
    operate_import("导入", 6),
    ;
    private final String name;
    private final Integer value;

    OperateEnums(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
