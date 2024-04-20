package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.system.domain.OssConfigDto;
import com.hb0730.rpc.sys.system.service.OssConfigRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
@Service
@Slf4j
public class OssConfigRemoteRpcService extends BaseRemoteRpcService<OssConfigRpcService> implements OssConfigRpcService {
    @Override
    protected String getAppName() {
        return "sys";
    }

    @Override
    public JR<OssConfigDto> getOssConfig(String sysCode) {
        return getRpcService().getOssConfig(sysCode);
    }

    @Override
    public JR<String> saveOssConfig(OssConfigDto dto) {
        return getRpcService().saveOssConfig(dto);
    }
}
