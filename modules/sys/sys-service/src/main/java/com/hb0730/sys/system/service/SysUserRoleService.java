package com.hb0730.sys.system.service;

import com.hb0730.core.service.BasicService;
import com.hb0730.sys.system.model.entity.SysUserRole;
import com.hb0730.sys.system.repository.SysUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/15
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleService extends BasicService<String, SysUserRole, SysUserRoleRepository> {

    /**
     * 根据用户ID删除
     *
     * @param userId 用户ID
     * @return 是否成功
     */
    public boolean deleteByUserId(String userId) {
        return repository.deleteByUserId(userId);
    }

    /**
     * 批量保存
     *
     * @param list 用户角色列表
     * @return 是否成功
     */
    public boolean saveBatch(List<SysUserRole> list) {
        return repository.saveBatch(list);
    }

    /**
     * 通过用户ID查询有效的角色
     *
     * @param userId 用户ID
     * @return 有效的角色列表
     */
    public List<SysUserRole> findEffectiveRolesByUserId(String userId) {
        return repository.findEffectiveRolesByUserId(userId);
    }
}
