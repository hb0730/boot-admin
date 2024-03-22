package com.hb0730.base.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 接口授权信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiAuth implements Serializable {
    /**
     * 应用键值
     */
    private String appKey;
    /**
     * 应用密钥
     */
    private String appSec;
    /**
     * 加密密钥
     */
    private String aesKey;

}
