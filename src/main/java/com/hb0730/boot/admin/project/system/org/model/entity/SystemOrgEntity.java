package com.hb0730.boot.admin.project.system.org.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统组织
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_org")
public class SystemOrgEntity extends BusinessDomain {

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
     * 父id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 英文名称
     */
    @TableField("enname")
    private String enname;

    /**
     * 编码
     */
    @TableField("number")
    private String number;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 祖级列表
     */
    @TableField("ancestors")
    private String ancestors;

    /**
     * 负责人
     */
    @TableField("leader")
    private String leader;

    /**
     * 联系方式
     */
    @TableField("phone")
    private String phone;

    /**
     * 联系邮箱
     */
    @TableField("email")
    private String email;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String NAME = "name";

    public static final String ENNAME = "enname";

    public static final String NUMBER = "number";

    public static final String SORT = "sort";

    public static final String ANCESTORS = "ancestors";

    public static final String LEADER = "leader";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

}
