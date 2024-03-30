package com.hb0730.sys.system.service;

import com.hb0730.rpc.sys.system.domain.query.RoleQuery;
import com.hb0730.sys.system.domain.SysRole;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
public interface IRoleService {

    /**
     * 根据用户ID获取角色
     *
     * @param userId .
     * @return .
     */
    List<SysRole> getByUserId(Long userId);

    /**
     * 查询所有启用的角色
     *
     * @return 角色
     */
    List<SysRole> findAllEnabled();

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 角色
     */
    List<SysRole> findByUserId(Long userId);

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   角色ID
     * @return .
     */
    boolean existsByCode(String code, @Nullable Long id);

    /**
     * 保存
     *
     * @param role 角色
     */
    void save(SysRole role);

    /**
     * 更新角色
     *
     * @param role .
     */
    void updateById(SysRole role);

    /**
     * 根据ID删除
     *
     * @param id .
     */
    void deleteById(Long id);


    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return .
     */
    Page<SysRole> page(RoleQuery query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return .
     */
    List<SysRole> list(RoleQuery query);


    /**
     * 赋予菜单与权限
     *
     * @param roleId        角色ID
     * @param permissionIds 菜单与权限ID
     */
    void assignPermission(Long roleId, List<Long> permissionIds);
}
