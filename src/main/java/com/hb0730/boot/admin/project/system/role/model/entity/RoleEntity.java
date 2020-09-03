package com.hb0730.boot.admin.project.system.role.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色
 *
 * @author bing_huang
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_role")
public class RoleEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

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
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 标识
     */
    @TableField("`code`")
    private String code;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String SORT = "sort";

}
