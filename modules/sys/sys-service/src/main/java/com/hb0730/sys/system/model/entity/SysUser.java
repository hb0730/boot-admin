package com.hb0730.sys.system.model.entity;

import com.hb0730.core.entity.BizEntity;
import com.hb0730.mybatis.core.annotation.FieldEncrypt;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户表
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends BizEntity {
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 加密盐
     */
    private String salt;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户邮箱
     */
    @FieldEncrypt
    private String email;
    /**
     * 用户邮箱hash
     */
    private String hashEmail;
    /**
     * 用户手机号
     */
    @FieldEncrypt
    private String phone;
    /**
     * 用户手机号hash
     */
    private String hashPhone;

    /**
     * 用户性别 0-未知 1-男 2-女
     */
    private Integer gender;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 是否系统用户
     */
    private Boolean isSystem;

    /**
     * 用户状态 0-正常 1-禁用
     */
    private Boolean status = true;

}
