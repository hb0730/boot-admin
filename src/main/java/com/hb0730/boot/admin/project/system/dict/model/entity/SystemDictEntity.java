package com.hb0730.boot.admin.project.system.dict.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hb0730.boot.admin.domain.model.domain.BusinessDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@TableName("t_system_dict")
public class SystemDictEntity extends BusinessDomain {

    private static final long serialVersionUID=1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 字典名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典英文名称
     */
    @TableField("enname")
    private String enname;

    /**
     * 字典编码
     */
    @TableField("number")
    private String number;

    /**
     * 父id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 字典类型
     */
    @TableField("dict_type")
    private String dictType;

    /**
     * 字典值
     */
    @TableField("dict_value")
    private String dictValue;

    /**
     * 字典标签
     */
    @TableField("dict_label")
    private String dictLabel;

    /**
     * 是否默认
     */
    @TableField("is_defeult")
    private Integer isDefeult;

    /**
     * 字典参数
     */
    @TableField("params")
    private String params;

    /**
     * 排序
     */
    @TableField("sort")
    private String sort;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ENNAME = "enname";

    public static final String NUMBER = "number";

    public static final String PARENT_ID = "parent_id";

    public static final String DICT_TYPE = "dict_type";

    public static final String DICT_VALUE = "dict_value";

    public static final String DICT_LABEL = "dict_label";

    public static final String IS_DEFEULT = "is_defeult";

    public static final String PARAMS = "params";

    public static final String SORT = "sort";

}
