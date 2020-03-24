package com.hb0730.boot.admin.user.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hb0730.boot.admin.commons.web.model.BusinessVO;
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
public class SystemUserVO extends BusinessVO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    @TableField("username")
    private String username;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;
    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("phonenumber")
    private String phonenumber;

    /**
     * 用户性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 部门ID
     */
    @TableField("deptId")
    private Long deptId;
}
