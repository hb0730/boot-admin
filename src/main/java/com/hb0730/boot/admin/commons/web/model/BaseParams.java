package com.hb0730.boot.admin.commons.web.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 通用 过滤参数
 *
 * @author bing_huang
 * @date 2020/06/26 11:56
 * @since V1.0
 */
@Data
@ToString
public class BaseParams implements Serializable {

    private static final long serialVersionUID = -629088576577675731L;

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
