package com.hb0730.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.common.mybatis.tenant.core.BaseTenantEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName("bas_role")
public class BasRole extends BaseTenantEntity {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID)
    private String id;
    /**
     * 机构id
     */
    private String orgId;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否系统
     */
//    @TableField("`is_system`")
//    private Boolean system = false;
    private Boolean isSystem = false;

    @TableField(exist = false)
    private Boolean system = isSystem;

    public void setSystem(Boolean system) {
        this.system = system;
        this.isSystem = system;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
        this.system = isSystem;
    }

    /**
     * 是否启用
     */
    @TableField("`is_enabled`")
    private Boolean enabled = true;
}
