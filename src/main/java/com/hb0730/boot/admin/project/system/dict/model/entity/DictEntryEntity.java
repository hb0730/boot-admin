package com.hb0730.boot.admin.project.system.dict.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BusinessEntryDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据字典项
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_dict_entry")
public class DictEntryEntity extends BusinessEntryDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典值
     */
    @TableField("value")
    private String value;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


    public static final String DESCRIPTION = "description";

    public static final String NAME = "name";

    public static final String VALUE = "value";

    public static final String SORT = "sort";

}
