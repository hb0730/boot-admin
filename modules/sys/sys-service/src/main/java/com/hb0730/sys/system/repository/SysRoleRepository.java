package com.hb0730.sys.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.core.repository.BaseRepository;
import com.hb0730.sys.system.convert.SysRoleConvert;
import com.hb0730.sys.system.model.entity.SysRole;
import com.hb0730.sys.system.model.request.role.SysRoleCreateRequest;
import com.hb0730.sys.system.model.request.role.SysRoleQueryRequest;
import com.hb0730.sys.system.model.request.role.SysRoleUpdateRequest;
import com.hb0730.sys.system.model.vo.SysRoleVO;
import com.hb0730.sys.system.repository.mapper.SysRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
@Service
@Slf4j
public class SysRoleRepository extends BaseRepository<String, SysRoleQueryRequest, SysRoleVO, SysRole,
        SysRoleCreateRequest, SysRoleUpdateRequest, SysRoleMapper, SysRoleConvert> {

    /**
     * code是否存在
     *
     * @param id   id
     * @param code code
     * @return 是否存在
     */
    public boolean codeExists(String code, String id) {
        LambdaQueryWrapper<SysRole> eq = Wrappers.lambdaQuery(SysRole.class)
                .eq(SysRole::getRoleCode, code);
        if (StrUtil.isBlank(id)) {
            eq.ne(SysRole::getId, id);
        }
        return baseMapper.exists(eq);
    }
}
