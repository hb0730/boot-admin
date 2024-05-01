package com.hb0730.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.common.mybatis.tenant.core.BaseTenantEntity;
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
@Accessors(chain = true)
@TableName("bas_user")
public class BasUser extends BaseTenantEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 所属机构
     */
    private String orgId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    @TableField(
            insertStrategy = FieldStrategy.NOT_EMPTY,
            updateStrategy = FieldStrategy.NOT_EMPTY,
            whereStrategy = FieldStrategy.NOT_EMPTY
    )
    private String password;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别,0 保密,1 男,2 女
     */
    private Integer gender = 0;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 密码重置时间
     */
    private Date pwdResetTime;
    /**
     * 是否系统用户
     */
//    @TableField("`is_system`")
//    private Boolean system = false;
    private Boolean isSystem = false;
    @TableField(exist = false)
    public Boolean system = isSystem;

    public void setSystem(Boolean system) {
        this.isSystem = system;
        this.system = system;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
        this.system = isSystem;
    }

    /**
     * 状态
     */
    @TableField(value = "`is_enabled`")
    private Boolean enabled = true;
}
