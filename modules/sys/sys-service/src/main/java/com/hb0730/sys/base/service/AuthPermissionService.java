package com.hb0730.sys.base.service;

import com.hb0730.base.Pair;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.sys.base.convert.PermissionCovert;
import com.hb0730.sys.base.model.vo.PermissionVO;
import com.hb0730.sys.base.model.vo.RouteVO;
import com.hb0730.sys.base.util.RouteUtil;
import com.hb0730.sys.base.util.TokenUtil;
import com.hb0730.sys.enums.MenuTypeEnums;
import com.hb0730.sys.system.model.entity.SysPermission;
import com.hb0730.sys.system.model.entity.SysUserRole;
import com.hb0730.sys.system.service.SysPermissionService;
import com.hb0730.sys.system.service.SysRolePermissionService;
import com.hb0730.sys.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthPermissionService {
    private final SysRolePermissionService rolePermissionService;
    private final SysPermissionService permissionService;
    private final PermissionCovert permissionCovert;
    private final SysUserService userService;


    /**
     * 根据token获取用户权限
     *
     * @param token token
     * @return 用户权限
     */
    public PermissionVO getPermissionByToken(String token) {
        Pair<String, Long> pair = TokenUtil.parseToken(token);
        if (null == pair) {
            throw new BasicException("非法token，解析失败");
        }
        String userId = pair.getCode();
        return getPermissionByUserId(userId);
    }

    /**
     * 根据用户id获取用户权限
     *
     * @param userId 用户id
     * @return 用户权限
     */
    public PermissionVO getPermissionByUserId(String userId) {
        List<SysUserRole> roles = userService.findEffectiveRoles(userId);
        if (CollectionUtil.isEmpty(roles)) {
            return new PermissionVO();
        }
        List<String> roleIds = new ArrayList<>();
        roles.forEach(sysUserRole -> roleIds.add(sysUserRole.getRoleId()));
        List<String> permissionIds = rolePermissionService.getPermsByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(permissionIds)) {
            return new PermissionVO();
        }
        List<SysPermission> sysPermissions = permissionService.listByIdsOrderBySortAsc(permissionIds);
        // 过滤掉禁用的
        sysPermissions.removeIf(SysPermission::isDisabled);
        if (CollectionUtil.isEmpty(sysPermissions)) {
            return null;
        }

        // 获取button
        List<SysPermission> buttons =
                sysPermissions.stream().filter(sysPermission -> MenuTypeEnums.BUTTON.getCode().equals(sysPermission.getMenuType())).toList();
        // 获取权限
        List<SysPermission> menus =
                sysPermissions.stream().filter(sysPermission -> !MenuTypeEnums.BUTTON.getCode().equals(sysPermission.getMenuType())).toList();

        List<String> btn = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(buttons)) {
            buttons.stream().map(SysPermission::getPerms).forEach(btn::add);
        }

        List<RouteVO> routes = RouteUtil.buildRoutes(permissionCovert.toObjectList(menus));
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setMenu(routes);
        permissionVO.setAuth(btn);
        return permissionVO;
    }
}
