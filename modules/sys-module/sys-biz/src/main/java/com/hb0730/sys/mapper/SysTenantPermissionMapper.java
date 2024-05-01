package com.hb0730.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.sys.domain.entity.SysTenantPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Repository
public interface SysTenantPermissionMapper extends BaseMapper<SysTenantPermission> {

    /**
     * 根据角色ID删除权限
     *
     * @param roleId 角色ID
     */
    @Select("delete from bas_role_permission where role_id = #{roleId}")
    void deleteRolePermission(String roleId);

    /**
     * 保存角色权限
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID
     */
    void saveRolePermission(@Param("roleId") String roleId, @Param("permissionIds") List<String> permissionIds);


    /**
     * 是否有子节点
     *
     * @param id .
     * @return .
     */
    @Select("select count(1) from bas_permission where parent_id = #{id}")
    boolean hasChildren(@Param("id") String id);


    /**
     * 根据产品ID查询
     *
     * @param productId 产品ID
     * @return .
     */
    @Select("SELECT bp.* FROM sys_product_permission p,bas_permission bp where p.permission_id = bp.id and p" +
            ".product_id = " +
            "#{productId}")
    List<SysTenantPermission> findByProductId(String productId);
}
