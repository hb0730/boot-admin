package com.hb0730.boot.admin.project.system.dept.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 部门
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_dept" )
public class DeptEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description" )
    private String description;

    /**
     * id
     */
    @TableId("id" )
    private Long id;

    /**
     * 名称
     */
    @TableField("name" )
    private String name;

    /**
     * 负责人
     */
    @TableField("leader" )
    private String leader;

    /**
     * 电话
     */
    @TableField("phone" )
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email" )
    private String email;

    /**
     * 父级
     */
    @TableField("parent_id" )
    private Long parentId;

    /**
     * 排序
     */
    @TableField("sort" )
    private Integer sort;

    /**
     * 祖级关系
     */
    @TableField("ancestors" )
    private String ancestors;


    public static final String DESCRIPTION = "description" ;

    public static final String ID = "id" ;

    public static final String NAME = "name" ;

    public static final String LEADER = "leader" ;

    public static final String PHONE = "phone" ;

    public static final String EMAIL = "email" ;

    public static final String PARENT_ID = "parent_id" ;

    public static final String SORT = "sort" ;

    public static final String ANCESTORS = "ancestors" ;

}
