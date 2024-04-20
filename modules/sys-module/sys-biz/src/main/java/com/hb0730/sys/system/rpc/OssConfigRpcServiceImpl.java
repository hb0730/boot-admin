package com.hb0730.sys.system.rpc;

import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.system.domain.OssConfigDto;
import com.hb0730.rpc.sys.system.service.OssConfigRpcService;
import com.hb0730.sys.system.domain.SysOssConfig;
import com.hb0730.sys.system.rpc.cache.OssCache;
import com.hb0730.sys.system.rpc.mapstruct.OssConfigMapper;
import com.hb0730.sys.system.service.IOssConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OssConfigRpcServiceImpl extends BaseServerRpcService<OssConfigRpcService> implements OssConfigRpcService {
    private final IOssConfigService ossConfigService;
    private final OssConfigMapper ossConfigMapper;
    private final OssCache ossCache;

    @Override
    public JR<OssConfigDto> getOssConfig(String sysCode) {
        Optional<SysOssConfig> configOptional = ossCache.get(sysCode);
        if (configOptional.isPresent()) {
            OssConfigDto dto = ossConfigMapper.toDto(configOptional.get());
            return JR.okData(dto);
        }
        SysOssConfig config = ossConfigService.getBySusCode(sysCode);
        if (null != config) {
            ossCache.put(sysCode, config);
        }
        OssConfigDto dto = ossConfigMapper.toDto(config);
        return JR.okData(dto);
    }

    @Override
    public JR<String> saveOssConfig(OssConfigDto dto) {
        if (null != dto.getId()) {
            ossCache.delete(dto.getSysCode());
        }
        SysOssConfig config = ossConfigMapper.toEntity(dto);
        ossConfigService.save(config);
        return JR.ok();
    }
}
