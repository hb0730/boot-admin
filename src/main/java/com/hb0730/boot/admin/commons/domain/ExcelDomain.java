package com.hb0730.boot.admin.commons.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 所有excel导出基类
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@ToString
public class ExcelDomain implements Serializable {
    /**
     * 创建人
     */
    @ExcelIgnore
    private Long createUserId;

    /**
     * 创建时间
     */
    @ExcelIgnore
    private Date createTime;

    /**
     * 更新者
     */
    @ExcelIgnore
    private Long updateUserId;

    /**
     * 创建时间
     */
    @ExcelIgnore
    private Date updateTime;

    /**
     * <p>
     * 是否启用
     * </p>
     */
    @ExcelIgnore
    private Integer isEnabled;

    /**
     * 是否删除
     */
    @ExcelIgnore
    private Integer delFlag = 0;
    /**
     * 版本
     */
    @ExcelIgnore
    private Integer version;
}
