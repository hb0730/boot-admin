package com.hb0730.basic.service.impl;

import com.hb0730.basic.domain.BasPermission;
import com.hb0730.basic.repository.BasPermissionRepository;
import com.hb0730.basic.service.IBasPermissionService;
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
public class BasPermissionServiceImpl implements IBasPermissionService {
    private final BasPermissionRepository basPermissionRepository;

    @Override
    public List<BasPermission> findByRoleIds(List<String> roleIds) {
        return basPermissionRepository.findByRoleIds(roleIds);
    }
}
