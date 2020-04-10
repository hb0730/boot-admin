package com.hb0730.boot.admin.project.system.user.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class UserParams implements Serializable {
    /**
     * 组织id
     */
    private Long deptId;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 是否启用
     */
    private Integer isEnabled;
}
