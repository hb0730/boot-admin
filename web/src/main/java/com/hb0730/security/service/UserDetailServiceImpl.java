package com.hb0730.security.service;

import com.hb0730.base.exception.BadRequestException;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasPermissionDto;
import com.hb0730.rpc.basic.domain.BasRoleDto;
import com.hb0730.rpc.basic.domain.BasUserDto;
import com.hb0730.rpc.basic.domain.OrgSmallDto;
import com.hb0730.rpc.basic.service.BasOrgRpcService;
import com.hb0730.rpc.basic.service.BasPermissionRpcService;
import com.hb0730.rpc.basic.service.BasRoleRpcService;
import com.hb0730.rpc.basic.service.BasUserRpcService;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import com.hb0730.rpc.sys.system.domain.RoleDto;
import com.hb0730.rpc.sys.system.domain.UserDto;
import com.hb0730.rpc.sys.system.service.PermissionRpcService;
import com.hb0730.rpc.sys.system.service.RoleRpcService;
import com.hb0730.rpc.sys.system.service.UserRpcService;
import com.hb0730.security.context.AuthenticationContext;
import com.hb0730.security.context.AuthenticationContextHolder;
import com.hb0730.security.domain.dto.UserInfoDto;
import com.hb0730.security.security.cache.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserProvider userProvider;
    /*===管理端====*/
    private final UserRpcService userRpcService;
    private final RoleRpcService roleRpcService;
    private final PermissionRpcService permissionRpcService;
    /*===履约端====*/
    private final BasUserRpcService basUserRpcService;
    private final BasRoleRpcService basRoleRpcService;
    private final BasPermissionRpcService basPermissionRpcService;
    private final BasOrgRpcService basOrgRpcService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationContext authenticationContext = AuthenticationContextHolder.getContext();
        Boolean isAdminLogin = false;
        if (null != authenticationContext) {
            isAdminLogin = authenticationContext.getTenantLogin();
        }
        JR<String> jr = basUserRpcService.getSysCodeByUsername(username);
        if (!jr.isSuccess()) {
            throw new UsernameNotFoundException(jr.getMessage());
        }
        String sysCode = jr.getResult();
        String cacheKey = getCacheKey(username, sysCode);

        UserInfoDto user = userProvider.getUser(cacheKey);
        if (null == user) {
            // 管理端 登录
            if (Boolean.TRUE.equals(isAdminLogin)) {
                user = loginByTenant(username);
            } else {
                // 履约端 登录
                user = loginByBiz(username);
            }
            userProvider.putUser(cacheKey, user);
        }

        return user;
    }

    /**
     * 管理端 登录
     *
     * @param username .
     * @return .
     */
    private UserInfoDto loginByTenant(String username) {
        JR<UserDto> jr = userRpcService.findByUsername(username);
        if (!jr.isSuccess()) {
            throw new UsernameNotFoundException(jr.getMessage());
        }

        UserDto result = jr.getResult();
        if (null == result) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (Boolean.FALSE.equals(result.getEnabled())) {
            throw new UsernameNotFoundException("用户已禁用");
        }
        JR<List<RoleDto>> roleJr = roleRpcService.findByUserId(result.getId());
        if (!roleJr.isSuccess()) {
            throw new BadRequestException(roleJr.getMessage());
        }
        List<RoleDto> roles = roleJr.getResult();
        List<Long> roleIds = roles.stream().map(RoleDto::getId).collect(Collectors.toList());
        JR<List<PermissionDto>> permissionJr = permissionRpcService.findByRoleIds(roleIds);
        if (!permissionJr.isSuccess()) {
            throw new BadRequestException(permissionJr.getMessage());
        }
        List<PermissionDto> permissions = permissionJr.getResult();


        UserInfoDto userInfo = new UserInfoDto();

        // id
        userInfo.setId(result.getId() + "");
        // 用户名
        userInfo.setUsername(result.getUsername());
        // 密码
        userInfo.setPassword(result.getPassword());
        // 昵称
        userInfo.setNickname(result.getNickname());
        // 头像
        userInfo.setAvatar(result.getAvatar());
        // 邮箱
        userInfo.setEmail(result.getEmail());
        // 电话
        userInfo.setPhone(result.getPhone());
        // 角色
        userInfo.setRoles(getRolesByAdmin(roles));
        // 权限
        userInfo.setPermissions(getPermissionsByAdmin(permissions));
        // 是否启用
        userInfo.setEnabled(result.getEnabled());
        // 系统编码
        userInfo.setSysCode(null);
        return userInfo;
    }

    /**
     * 履约端 登录
     *
     * @param username .
     * @return .
     */
    private UserInfoDto loginByBiz(String username) {
        JR<BasUserDto> jr = basUserRpcService.findByUsername(username);
        if (!jr.isSuccess()) {
            throw new UsernameNotFoundException(jr.getMessage());
        }
        BasUserDto result = jr.getResult();
        if (null == result) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (Boolean.FALSE.equals(result.getEnabled())) {
            throw new UsernameNotFoundException("用户已禁用");
        }
        // 验证登录是否到有效期
        OrgSmallDto org = result.getOrg();
        if (null != org) {
            JR<String> checked = basOrgRpcService.checkOrgExpiredForLogin(org.getId());
            if (!checked.isSuccess()) {
                throw new BadRequestException(checked.getMessage());
            }
        }

        JR<List<BasRoleDto>> roleJr = basRoleRpcService.findByUserId(result.getId());
        if (!roleJr.isSuccess()) {
            throw new BadRequestException(roleJr.getMessage());
        }
        List<BasRoleDto> roles = roleJr.getResult();
        List<String> roleIds = roles.stream().map(BasRoleDto::getId).collect(Collectors.toList());
        JR<List<BasPermissionDto>> permissionJr = basPermissionRpcService.findByRoleIds(roleIds);
        if (!permissionJr.isSuccess()) {
            throw new BadRequestException(permissionJr.getMessage());
        }
        List<BasPermissionDto> permissions = permissionJr.getResult();

        UserInfoDto userInfo = new UserInfoDto();
        // id
        userInfo.setId(result.getId());
        // 用户名
        userInfo.setUsername(result.getUsername());
        // 密码
        userInfo.setPassword(result.getPassword());
        // 昵称
        userInfo.setNickname(result.getNickname());
        // 头像
        userInfo.setAvatar(result.getAvatar());
        // 邮箱
        userInfo.setEmail(result.getEmail());
        // 电话
        userInfo.setPhone(result.getPhone());
        // 角色
        userInfo.setRoles(getRoles(roles));
        // 权限
        userInfo.setPermissions(getPermissions(permissions));
        // 是否启用
        userInfo.setEnabled(result.getEnabled());
        // 系统编码
        userInfo.setSysCode(result.getSysCode());
        return userInfo;
    }


    private List<String> getPermissionsByAdmin(List<PermissionDto> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream().map(PermissionDto::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRolesByAdmin(List<RoleDto> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream().map(RoleDto::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getPermissions(List<BasPermissionDto> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream().map(BasPermissionDto::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRoles(List<BasRoleDto> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream().map(BasRoleDto::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private String getCacheKey(String username, String sysCode) {
        if (StrUtil.isNotBlank(sysCode)) {
            return sysCode + ":" + username;
        }
        return username;
    }
}
