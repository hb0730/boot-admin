package com.hb0730.core.data;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/8
 */
@Schema(description = "分页")
@Data
public class Page<T> implements Serializable {
    @Schema(description = "当前页")
    private long current = 1;
    @Schema(description = "每页显示条数")
    private long size = 10;
    @Schema(description = "总页数")
    private long pages;
    @Schema(description = "总条数")
    private long total;
    @Schema(description = "数据")
    private List<T> records;


    /**
     * 创建分页
     *
     * @param current 当前页
     * @param size    每页显示条数
     * @param pages   总页数
     * @param total   总条数
     * @param records 数据
     * @param <T>     泛型
     * @return 分页
     */
    public static <T> Page<T> of(long current, long size, long pages, long total, List<T> records) {
        Page<T> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        page.setPages(pages);
        page.setTotal(total);
        page.setRecords(records);
        return page;
    }

    /**
     * 转换
     *
     * @param page 分页
     * @param <T>  泛型
     * @return 分页
     */
    public static <T> Page<T> of(IPage<T> page) {
        return of(page.getCurrent(), page.getSize(), page.getPages(), page.getTotal(), page.getRecords());
    }

    /**
     * 转换
     *
     * @param page    分页
     * @param records 数据
     * @param <T>     泛型
     * @return 分页
     */
    public static <T> Page<T> of(IPage<?> page, List<T> records) {
        return of(page.getCurrent(), page.getSize(), page.getPages(), page.getTotal(), records);
    }

    /**
     * 创建空分页
     *
     * @param <T> 泛型
     * @return 分页
     */
    public static <T> Page<T> empty() {
        return new Page<>();
    }
}
