package com.hb0730.boot.admin.modules.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysPermission;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据角色ID查询菜单与权限
     *
     * @param roleIds   角色ID
     * @param isEnabled 是否查询启用的
     * @return 菜单与权限
     */
    @Nullable
    List<SysPermission> listByRoleIds(@Nonnull @Param("roleIds") List<String> roleIds,
                                      @Nullable @Param("is_enabled") Boolean isEnabled);

    /**
     * 菜单与权限
     * @param isEnabled 是否查询启用的
     * @return 菜单与权限
     */
    List<SysPermission> list(@Nullable @Param("is_enabled") Boolean isEnabled);
    /**
     * 查询权限
     *
     * @param ids       ID集合
     * @param isEnabled 是否查询已启用的
     * @return 权限信息
     */
    List<SysPermission> listPermission(@Nullable @Param("ids") List<String> ids,
                                       @Nullable @Param("is_enabled") Boolean isEnabled);

    /**
     * 查询菜单根据ID
     *
     * @param ids       菜单ID集合
     * @param isEnabled 是否查询启用
     * @return 菜单集合
     */
    @Nullable
    List<SysPermission> listMenuByIds(@Nullable @Param("ids") List<String> ids,
                                      @Nullable @Param("is_enabled") Boolean isEnabled);
}
