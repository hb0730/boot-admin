package com.hb0730.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.common.mybatis.tenant.core.BaseTenantEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bas_organization")
public class BasOrg extends BaseTenantEntity {

    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID)
    private String id;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 联系人
     */
    private String linkMan;
    /**
     * 联系电话
     */
    private String linkTel;
    /**
     * 联系邮箱
     */
    private String linkEmail;
    /**
     * 机构地址
     */
    private String address;
    /**
     * 到期时间
     */
    private Date usedEndTime;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 路径
     */
    private String path;
    /**
     * 机构类型
     * 1 商户
     * 2 机构
     * 3 部门
     */
    private Integer type = 2;
    /**
     * 备注
     */
    private String memo;
    /**
     * 是否系统机构
     */
    private Boolean isSystem = false;
    @TableField(exist = false)
    private Boolean system = isSystem;

//    @TableField(value = "`is_system`", property = "`system`")
//    private Boolean system = false;

    public void setSystem(Boolean system) {
        isSystem = system;
        this.system = system;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
        system = isSystem;
    }

    /**
     * 是否启用
     */
    @TableField(value = "`is_enabled`")
    private Boolean enabled = true;
    /**
     * 是否启用saas
     */
    @TableField(value = "`is_saas`")
    private Boolean saas = false;
}
