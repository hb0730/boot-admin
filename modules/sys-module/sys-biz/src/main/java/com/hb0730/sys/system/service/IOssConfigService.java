package com.hb0730.sys.system.service;

import com.hb0730.sys.system.domain.SysOssConfig;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
public interface IOssConfigService {

    /**
     * 根据商户编码获取oss配置
     *
     * @param sysCode 商户编码
     * @return oss配置
     */
    SysOssConfig getBySusCode(String sysCode);

    /**
     * 保存oss配置
     *
     * @param sysOssConfig oss配置
     */
    void save(SysOssConfig sysOssConfig);
}
