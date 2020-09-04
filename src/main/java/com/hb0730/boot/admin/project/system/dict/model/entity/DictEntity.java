package com.hb0730.boot.admin.project.system.dict.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BusinessDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据字典
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_dict")
public class DictEntity extends BusinessDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * 字典名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典类型
     */
    @TableField("type")
    private String type;


    public static final String DESCRIPTION = "description";

    public static final String NAME = "name";

    public static final String TYPE = "type";

}
