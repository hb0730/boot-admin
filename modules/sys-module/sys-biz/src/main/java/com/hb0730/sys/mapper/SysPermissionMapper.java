package com.hb0730.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.sys.domain.entity.SysPermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据角色id查询权限
     *
     * @param roleIds 角色id
     * @return 权限
     */
    List<SysPermission> listByRoleIds(List<String> roleIds);


    /**
     * 是否有子节点
     *
     * @param id id
     * @return true/false
     */
    @Select("select count(1) from sys_permission where parent_id = #{id}")
    boolean hasChild(String id);

    /**
     * 是否有角色权限
     *
     * @param permissionId 权限id
     * @return true/false
     */
    @Select("select count(1) from sys_role_permission where permission_id = #{permissionId}")
    boolean existsRolePermission(String permissionId);
}
