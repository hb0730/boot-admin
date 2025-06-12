package com.hb0730.sys.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * token 身份信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginTokenIdentity implements Serializable {

    /**
     * 登录时间
     */
    private Long loginTime;

}
