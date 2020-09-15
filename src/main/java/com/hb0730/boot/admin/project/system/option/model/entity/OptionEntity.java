package com.hb0730.boot.admin.project.system.option.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * options选项
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_option")
public class OptionEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * key
     */
    @TableField("option_key")
    private String optionKey;

    /**
     * value
     */
    @TableField("option_value")
    private String optionValue;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String OPTION_KEY = "option_key";

    public static final String OPTION_VALUE = "option_value";

}
