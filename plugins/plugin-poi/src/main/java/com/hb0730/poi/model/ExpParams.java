package com.hb0730.poi.model;

import lombok.Data;

import java.util.List;

/**
 * 导出参数
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/25
 */
@Data
public class ExpParams<T> {
    /**
     * 导出标题
     */
    private String title;
    /**
     * 导出的实体类
     */
    private Class<T> entityClass;
    /**
     * 导出数据
     */
    private List<T> data;

    /**
     * 多SHEET导出
     */
    private List<ExpParams<T>> sheets;

    /**
     * 导出上限件数
     */
    private int overLimit;

    /**
     * 导出参数
     *
     * @param entityClass 导出的实体类
     */
    public ExpParams(Class<T> entityClass) {
        this(entityClass, 50000);
    }

    /**
     * 导出参数
     *
     * @param entityClass 导出的实体类
     * @param overLimit   导出上限件数
     */
    public ExpParams(Class<T> entityClass, int overLimit) {
        this.entityClass = entityClass;
        this.overLimit = overLimit;
    }
}
