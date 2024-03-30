package com.hb0730.basic.service.impl;

import com.hb0730.basic.domain.BasOrg;
import com.hb0730.basic.repository.BasOrgRepository;
import com.hb0730.basic.service.IBasOrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasOrgServiceImpl implements IBasOrgService {
    private final BasOrgRepository basOrgRepository;

    @Override
    public BasOrg findByOrgId(String orgId) {
        return basOrgRepository.findById(orgId).orElse(null);
    }
}
