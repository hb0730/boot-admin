package com.hb0730.boot.admin.project.system.dict.model.vo;

import com.hb0730.boot.admin.domain.model.web.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 数据字段类型
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DictParams extends BaseParams {
    /**
     * 是否查询全部
     */
    private Integer isAll;

    /**
     * <p>
     * 字典名称
     * </p>
     */
    private String name;

    /**
     * 字典编码
     */
    private String number;

    /**
     * 是否启用
     */
    private String isEnabled;
}
