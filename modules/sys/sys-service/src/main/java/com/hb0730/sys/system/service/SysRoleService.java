package com.hb0730.sys.system.service;

import com.hb0730.base.R;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.core.service.BaseService;
import com.hb0730.sys.system.model.entity.SysRole;
import com.hb0730.sys.system.model.entity.SysRolePermission;
import com.hb0730.sys.system.model.request.role.SysRoleCreateRequest;
import com.hb0730.sys.system.model.request.role.SysRoleQueryRequest;
import com.hb0730.sys.system.model.request.role.SysRoleUpdateRequest;
import com.hb0730.sys.system.model.vo.SysRoleVO;
import com.hb0730.sys.system.repository.SysRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/8
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysRoleService extends BaseService<String, SysRoleQueryRequest, SysRoleVO, SysRole,
        SysRoleCreateRequest, SysRoleUpdateRequest, SysRoleRepository> {
    private final SysRolePermissionService sysRolePermissionService;


    /**
     * 是否存在
     *
     * @param id   id
     * @param code code
     * @return 是否存在
     */
    public R<String> hasCode(String id, String code) {
        boolean present = repository.codeExists(code, id);
        return present ? R.NG("角色标识已存在") : R.OK();
    }

    @Override
    public boolean create(SysRoleCreateRequest req) {
        R<String> jr = hasCode(null, req.getRoleCode());
        if (!jr.isSuccess()) {
            throw new BasicException(jr.getMessage());
        }
        return super.create(req);
    }

    @Override
    public boolean updateById(String id, SysRoleUpdateRequest req) {
        R<String> jr = hasCode(id, req.getRoleCode());
        if (!jr.isSuccess()) {
            throw new BasicException(jr.getMessage());
        }
        return super.updateById(id, req);
    }


    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(String id) {
        SysRole entity = getById(id);
        if (null == entity) {
            throw new BasicException("角色不存在");
        }
        return repository.deleteById(id);
    }


    /**
     * 获取权限
     *
     * @param id 角色id
     * @return 权限
     */
    public List<String> getPerms(String id) {
        return sysRolePermissionService.getPermsByRoleId(id);
    }

    /**
     * 赋权
     *
     * @param id            id
     * @param permissionIds 权限id
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean grant(String id, List<String> permissionIds) {
        SysRole entity = getById(id);
        if (null == entity) {
            throw new BasicException("角色不存在");
        }
        // 删除旧的权限
        sysRolePermissionService.removeByRoleId(id);
        // 新增权限
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            List<SysRolePermission> list = permissionIds.stream().map(permissionId -> {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setRoleId(id);
                sysRolePermission.setPermissionId(permissionId);
                return sysRolePermission;
            }).toList();
            sysRolePermissionService.saveBatch(list);
        }

        // 更新缓存

        return true;
    }
}
