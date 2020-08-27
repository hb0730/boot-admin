package com.hb0730.boot.admin.project.system.user.info.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户详情dto
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
public class UserInfoDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    private String nikeName;

    /**
     * 用户手机号
     */
    private String phoneNumber;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否启用
     */
    private Integer isEnabled;
}
