package com.hb0730.boot.admin.domain.model.web;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 单据Vo
 * </p>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessVO extends BaseVO {

    private static final long serialVersionUID = 9123860922498374224L;
    /**
     * 用于翻页 起始位置 (第几页)
     */
    protected Integer startRow = 1;
    /**
     * 用于翻页 每页记录数(每页显示数)
     */
    protected Integer pageSize = 10;
    /**
     * 用于排序 排序列(字段名称)
     */
    protected String sortDirection;
    /**
     * 用于多列排序(字段名称,字段名称)
     */
    protected String sortSql;
}
