package com.hb0730.basic.service.impl;

import com.hb0730.basic.domain.BasUser;
import com.hb0730.basic.repository.BasUserRepository;
import com.hb0730.basic.service.IBasUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasUserServiceImpl implements IBasUserService {
    private final BasUserRepository basUserRepository;

    @Override
    public String getSysCodeByUsername(String username) {
        return basUserRepository.getSysCodeByUsername(username);
    }

    @Override
    public BasUser findByUsername(String username) {
        return basUserRepository.findByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLastLoginTime(String username) {
        basUserRepository.updateLastLoginTimeByUsername(new Date(), username);
    }
}
