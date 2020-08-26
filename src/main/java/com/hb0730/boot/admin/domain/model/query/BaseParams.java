package com.hb0730.boot.admin.domain.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 通用controller请求参数
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@ToString
@EqualsAndHashCode
public class BaseParams implements Serializable {
    /**
     * 排序
     * DESC
     * asc
     */
    private String sortType;
    /**
     * 排序字段
     * 表名称
     */
    private List<String> sortColumn;

    /**
     * 分组字段
     */
    private List<String> groupColumn;

    /**
     * 页数
     */
    private Long pageNum = 1L;

    /**
     * 数量
     */
    private Long pageSize = 10L;
}
