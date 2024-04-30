package com.hb0730.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.basic.domain.entity.BasRole;
import com.hb0730.basic.domain.entity.BasRolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Repository
public interface BasRoleMapper extends BaseMapper<BasRole> {

    /**
     * 根据用户ID获取角色
     *
     * @param userId 用户ID
     * @return 角色
     */
    @Select("select * from bas_role where id in (select role_id from bas_user_role where user_id = #{userId})")
    List<BasRole> findByUserId(String userId);

    /**
     * 根据组织ID获取管理员角色
     *
     * @param orgIds 组织ID
     * @return 角色
     */
    Set<String> findAdminRoleIdsByOrgIds(List<String> orgIds);

    /**
     * 根据商户码获取角色ID
     *
     * @param sysCodes .
     * @return .
     */
    Set<String> findRoleIdsBySysCodes(List<String> sysCodes);

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    List<BasRolePermission> findRolePermissionsByRoleIds(Collection<String> roleIds);


    /**
     * 根据角色ID获取权限
     *
     * @param roleId 角色ID
     * @return 权限
     */
    @Select("select permission_id from bas_role_permission where role_id = #{roleId}")
    List<String> getPermissionIdsByRoleId(String roleId);

    /**
     * code是否存在
     *
     * @param code code
     * @return 是否存在
     */
    @Select("select count(1) from bas_role where code = #{code} and sys_code = #{sysCode}")
    boolean existsCode(String code, String sysCode);

    /**
     * code是否存在
     *
     * @param code code
     * @param id   需要排除的id
     * @return 是否存在
     */
    @Select("select count(1) from bas_role where code = #{code} and sys_code = #{sysCode} and id != #{id}")
    boolean existsCodeByIdNot(String code, String sysCode, String id);

    /**
     * 角色是否存在用户
     *
     * @param roleId 角色id
     * @return 是否存在
     */
    @Select("select count(1) from bas_user_role where role_id = #{roleId}")
    boolean existsUserByRoleId(String roleId);

    /**
     * 删除角色权限
     *
     * @param roleId 角色id
     */
    @Delete("delete from bas_role_permission where role_id = #{roleId}")
    void deleteRolePermissionByRoleId(String roleId);

    /**
     * 保存角色权限
     *
     * @param rolePermissions 角色权限
     */
    void saveRolePermission(List<BasRolePermission> rolePermissions);
}
