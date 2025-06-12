package com.hb0730.base.meta;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/22
 */
public interface IUserInfo {
    /**
     * 用户ID
     */
    String getId();

    /**
     * 用户
     */
    String getUsername();

    /**
     * 密码
     */
    String getPassword();

    /**
     * 昵称
     */
    String getNickname();

    /**
     * 头像
     */
    String getAvatar();

    /**
     * 邮箱
     */
    String getEmail();

    /**
     * 手机号
     */
    String getPhone();

    /**
     * 用户角色
     */
    default List<String> getRoles() {
        return null;
    }

    ;

    /**
     * 用户权限
     */
    default List<String> getPermissions() {
        return null;
    }

    ;

    /**
     * 是否启用
     */
    Boolean getStatus();
}
