package com.hb0730.boot.admin.security.model;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 在线用户
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/3
 */
@Data
@EqualsAndHashCode
@ToString
public class OnlineUser implements Serializable {
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 岗位
     */
    private String dept;
    /**
     * token
     */
    private String key;
    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;


    public static OnlineUser convert(@Nonnull UserInfo userInfo) {
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUsername(userInfo.getUsername());
        return onlineUser;
    }
}
