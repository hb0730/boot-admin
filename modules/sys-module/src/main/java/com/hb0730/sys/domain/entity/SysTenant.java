package com.hb0730.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.common.mybatis.tenant.core.BaseTenantEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@TableName("bas_organization")
public class SysTenant extends BaseTenantEntity {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID)
    private String id;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    @TableField("`name`")
    private String name;
    /**
     * 父机构ID
     */
    private String parentId;
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
     * 总部标识
     */
    // 解决mybatis-plus 别名映射为Mysql关键字问题
    public Boolean isSystem = true;
    @TableField(exist = false)
    public Boolean system = isSystem;

    public void setSystem(Boolean system) {
        this.system = system;
        this.isSystem = system;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
        this.system = isSystem;
    }

    /**
     * 是否启用sass
     */
    @TableField("`is_saas`")
    private Boolean saas;

    /**
     * 产品
     */
    private String productId;

    /**
     * 使用截止日期
     */
    private Date usedEndTime;

    /**
     * 网点等级 1：一级，2：二级，3：三级
     */
    @TableField("`level`")
    private Integer level = 1;

    /**
     * 机构路径
     */
    private String path = id;
    /**
     * 机构类型
     * 1 厂商
     * 2 机构网点
     */
    @TableField("`type`")
    private Integer type = 1;
    /**
     * 备注
     */
    private String memo;

    /**
     * 是否启用
     */
    @TableField("`is_enabled`")
    private Boolean enabled;
}
