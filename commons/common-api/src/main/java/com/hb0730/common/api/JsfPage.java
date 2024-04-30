package com.hb0730.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
public class JsfPage<T> implements Serializable {
    /**
     * 数据集合
     */
    @Schema(description = "数据集合")
    private List<T> records;
    /**
     * 数据总数
     */
    @Schema(description = "数据总数")
    private long total;
    /**
     * 分页size
     */
    @Schema(description = "分页size")
    private long size;
    /**
     * 当前页码
     */
    @Schema(description = "当前页码")
    private long current;

    /**
     * 分页初始化
     */
    public JsfPage() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
    }

    /**
     * 分页初始化
     *
     * @param current 当前页码
     * @param size    分页size
     */
    public JsfPage(long current, long size) {
        this(current, size, 0L);
    }

    /**
     * 分页初始化
     *
     * @param current 当前页码
     * @param size    分页size
     * @param total   数据总数
     */
    public JsfPage(long current, long size, long total) {
        this.records = Collections.emptyList();
        if (current > 0L) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
    }

    /**
     * 分页结果构造器
     *
     * @param current 当前页码
     * @param size    分页size
     * @param total   数据总数
     * @param records 当前页数据集合
     */
    public JsfPage(long current, long size, long total, List<T> records) {
        this.setCurrent(current).setSize(size).setTotal(total).setRecords(records);
    }

    public JsfPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public JsfPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public JsfPage<T> setSize(long size) {
        this.current = current;
        return this;
    }

    public JsfPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }
}