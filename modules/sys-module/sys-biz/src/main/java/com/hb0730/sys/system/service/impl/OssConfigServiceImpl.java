package com.hb0730.sys.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hb0730.sys.system.domain.SysOssConfig;
import com.hb0730.sys.system.repository.OssConfigRepository;
import com.hb0730.sys.system.service.IOssConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OssConfigServiceImpl implements IOssConfigService {
    private final OssConfigRepository repository;

    @Override
    public SysOssConfig getBySusCode(String sysCode) {
        return repository.findBySysCode(sysCode);
    }

    @Override
    public void save(SysOssConfig sysOssConfig) {
        String id = sysOssConfig.getId();
        SysOssConfig config = repository.findById(id).orElse(new SysOssConfig());
        if (config.getId() == null) {
            config = sysOssConfig;
        } else {
            BeanUtil.copyProperties(sysOssConfig, config,
                    "id", "created", "createdBy", "sysCode");
        }
        repository.save(config);
    }
}
