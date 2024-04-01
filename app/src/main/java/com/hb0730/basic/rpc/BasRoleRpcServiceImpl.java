package com.hb0730.basic.rpc;

import com.hb0730.base.conf.server.BaseServerRpcService;
import com.hb0730.basic.domain.BasRole;
import com.hb0730.basic.rpc.mapstruct.BasRoleMapper;
import com.hb0730.basic.service.IBasRoleService;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.basic.domain.BasRoleDto;
import com.hb0730.rpc.basic.domain.query.BasRoleQuery;
import com.hb0730.rpc.basic.service.BasRoleRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasRoleRpcServiceImpl extends BaseServerRpcService<BasRoleRpcService> implements BasRoleRpcService {
    private final BasRoleMapper basRoleMapper;
    private final IBasRoleService basRoleService;

    @Override
    public JR<List<BasRoleDto>> findByUserId(String userId) {
        List<BasRole> roles = basRoleService.findByUserId(userId);
        return JR.okData(basRoleMapper.toDtoList(roles));
    }

    @Override
    public JR<Boolean> existsByCode(String code, String sysCode, String id) {
        return JR.okData(basRoleService.existsByCode(code, sysCode, id));
    }

    @Override
    public JR<JsfPage<BasRoleDto>> page(BasRoleQuery query) {
        Page<BasRole> page = basRoleService.page(query);
        List<BasRoleDto> dtoList = basRoleMapper.toDtoList(page.getContent());
        return JR.okData(JsfPage.of(page, dtoList));
    }

    @Override
    public JR<List<BasRoleDto>> list(BasRoleQuery query) {
        List<BasRole> roles = basRoleService.list(query);
        return JR.okData(basRoleMapper.toDtoList(roles));
    }

    @Override
    public JR<String> save(BasRoleDto dto) {
        BasRole role = basRoleMapper.toEntity(dto);
        basRoleService.save(role);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(BasRoleDto dto) {
        BasRole role = basRoleMapper.toEntity(dto);
        basRoleService.updateById(role);
        return JR.ok();
    }

    @Override
    public JR<String> delete(String id) {
        basRoleService.deleteById(id);
        return JR.ok();
    }

    @Override
    public JR<String> grant(String roleId, List<Long> permissionIds) {
        basRoleService.grantPermission(roleId, permissionIds);
        return JR.ok();
    }
}
