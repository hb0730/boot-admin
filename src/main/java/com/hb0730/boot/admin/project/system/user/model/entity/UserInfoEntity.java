package com.hb0730.boot.admin.project.system.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_user_info")
public class UserInfoEntity extends BaseDomain {

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
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户手机号
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 是否为管理员
     */
    @TableField("is_admin")
    private Integer isAdmin;
    /**
     * 所属部门
     */
    @TableField("dept_id")
    private Long deptId;

    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NICK_NAME = "nick_name";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String GENDER = "gender";

    public static final String AVATAR = "avatar";

    public static final String EMAIL = "email";

    public static final String IS_ADMIN="is_admin";

    public static final String DEPT_ID="dept_id";

}
