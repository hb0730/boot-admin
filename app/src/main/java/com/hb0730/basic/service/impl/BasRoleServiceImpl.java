package com.hb0730.basic.service.impl;

import com.hb0730.basic.domain.BasRole;
import com.hb0730.basic.repository.BasRoleRepository;
import com.hb0730.basic.service.IBasRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasRoleServiceImpl implements IBasRoleService {
    private final BasRoleRepository basRoleRepository;

    @Override
    public List<BasRole> findByUserId(String userId) {
        return basRoleRepository.findByUserId(userId);
    }
}
