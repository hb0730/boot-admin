package com.hb0730.boot.admin.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_user")
public class SystemUserEntity extends BusinessDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    @TableField(value = "username", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String password;
    /**
     * 用户昵称
     */
    @TableField(value = "nick_name", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String nickName;

    /**
     * 用户邮箱
     */
    @TableField(value = "email", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String email;

    /**
     * 手机号码
     */
    @TableField(value = "phonenumber", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String phonenumber;

    /**
     * 用户性别
     */
    @TableField(value = "sex", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer sex;

    /**
     * 用户头像
     */
    @TableField(
            value = "avatar", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String avatar;

    /**
     * 部门ID
     */
    @TableField(value = "deptId", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Long deptId;

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String NICK_NAME = "nick_name";

    public static final String EMAIL = "email";

    public static final String PHONENUMBER = "phonenumber";

    public static final String SEX = "sex";

    public static final String AVATAR = "avatar";

    public static final String DEPTID = "deptId";

}
