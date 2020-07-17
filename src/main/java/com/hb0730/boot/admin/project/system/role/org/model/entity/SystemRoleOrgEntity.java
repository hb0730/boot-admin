package com.hb0730.boot.admin.project.system.role.org.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hb0730.boot.admin.domain.model.domain.BusinessDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色组织(数据范围)
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_role_org")
public class SystemRoleOrgEntity extends BusinessDomain {

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
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 组织id
     */
    @TableField("org_id")
    private Long orgId;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String ORG_ID = "org_id";

}
