package com.hb0730.rpc.sys.system.service;

import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.system.domain.OssConfigDto;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
public interface OssConfigRpcService {

    /**
     * 获取oss配置
     *
     * @param sysCode 系统编码
     * @return oss配置
     */
    default JR<OssConfigDto> getOssConfig(String sysCode) {
        return JR.fail("暂未实现");
    }

    /**
     * 保存oss配置
     *
     * @param dto oss配置
     * @return 是否成功
     */
    default JR<String> saveOssConfig(OssConfigDto dto) {
        return JR.fail("暂未实现");
    }
}
