package com.hb0730.boot.admin.data.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页基类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/28
 */
@Data
@EqualsAndHashCode
@ToString
public class BasePage<T> implements Serializable {
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

    public BasePage() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 0L;
        this.current = 0L;
    }

    public BasePage(long current, long size) {
        this(current, size, 0L);
    }

    public BasePage(long current, long size, long total) {
        this(current, size, total, null);
    }

    public BasePage(long current, long size, long total, List<T> records) {
        this.setCurrent(current)
            .setSize(size)
            .setTotal(total)
            .setRecords(records);
    }

    public BasePage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public BasePage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    public BasePage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public BasePage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }
}
