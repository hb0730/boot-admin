package com.hb0730.sys.base.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录 token 缓存
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
@Data

public class LoginToken implements Serializable {
    /**
     * 用户id
     */
    private String id;

    /**
     * token 状态
     */
    private Integer status;

    /**
     * 已续签次数
     */
    private Integer refreshCount;
    /**
     * 原始登录身份
     */
    private LoginTokenIdentity origin;

    /**
     * 覆盖登录身份
     */
    private LoginTokenIdentity override;
}
