package com.hb0730.security.login;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.basic.domain.entity.BasOrg;
import com.hb0730.basic.domain.entity.BasPermission;
import com.hb0730.basic.domain.entity.BasRole;
import com.hb0730.basic.domain.entity.BasUser;
import com.hb0730.basic.service.BasOrgService;
import com.hb0730.basic.service.BasPermissionService;
import com.hb0730.basic.service.BasRoleService;
import com.hb0730.basic.service.BasUserService;
import com.hb0730.security.domain.dto.UserInfoDto;
import com.hb0730.security.domain.dto.UserOrgInfoDto;
import com.hb0730.sys.domain.entity.SysPermission;
import com.hb0730.sys.domain.entity.SysRole;
import com.hb0730.sys.domain.entity.SysUser;
import com.hb0730.sys.service.SysPermissionService;
import com.hb0730.sys.service.SysRoleService;
import com.hb0730.sys.service.SysUserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserLoginService implements com.hb0730.security.service.UserLoginService {
    private final SysUserService sysUserService;
    private final SysRoleService sysRoleService;
    private final SysPermissionService sysPermissionService;


    private final BasUserService basUserService;
    private final BasRoleService basRoleService;
    private final BasPermissionService basPermissionService;
    private final BasOrgService basOrgService;

    @Override
    public Optional<String> getSysCodeByUsername(String username) {
        return Optional.ofNullable(basUserService.getSysCodeByUsername(username));
    }

    @Override
    public UserInfoDto findByUsername(String username) {
        return tenantLogin(username);
    }

    @Override
    public UserInfoDto findByUsername(String username, @Nullable String sysCode) {
        return bizLogin(username);
    }

    /**
     * 商户端登录
     *
     * @param username .
     * @return .
     */
    private UserInfoDto tenantLogin(String username) {
        SysUser user = sysUserService.getByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new UsernameNotFoundException("用户已被禁用");
        }
        // 角色
        List<SysRole> roles = sysRoleService.listByUserId(user.getId());
        // 权限
        List<SysPermission> permissions = sysPermissionService.listByUserId(user.getId());

        UserInfoDto userInfo = new UserInfoDto();

        // id
        userInfo.setId(user.getId());
        // 用户名
        userInfo.setUsername(user.getUsername());
        // 密码
        userInfo.setPassword(user.getPassword());
        // 昵称
        userInfo.setNickname(user.getNickname());
        // 性别
//        userInfo.setGender(user.getGender());
        // 邮箱
        userInfo.setEmail(user.getEmail());
        // 电话
        userInfo.setPhone(user.getPhone());
        // 头像
        userInfo.setAvatar(user.getAvatar());
        // 角色
        userInfo.setRoles(getRolesByAdmin(roles));
        // 权限
        userInfo.setPermissions(getPermissionsByAdmin(permissions));
        // 是否启用
        userInfo.setEnabled(user.getEnabled());
        // 系统编码
        userInfo.setSysCode(null);
        return userInfo;
    }

    /**
     * 履约端登录
     *
     * @param username .
     * @return .
     */
    public UserInfoDto bizLogin(String username) {
        BasUser user = basUserService.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new UsernameNotFoundException("用户已被禁用");
        }
        BasOrg orgInfo = basOrgService.getById(user.getOrgId());
        //商户有效期
        basOrgService.checkOrgExpiredForLogin(orgInfo.getId());
        // 角色
        List<BasRole> roles = basRoleService.findByUserId(user.getId());
        // 权限
        List<BasPermission> permissions = basPermissionService.listByUserId(user.getId());
        UserInfoDto userInfo = new UserInfoDto();
        // id
        userInfo.setId(user.getId());
        // 用户名
        userInfo.setUsername(user.getUsername());
        // 密码
        userInfo.setPassword(user.getPassword());
        // 昵称
        userInfo.setNickname(user.getNickname());
        // 头像
        userInfo.setAvatar(user.getAvatar());
        // 邮箱
        userInfo.setEmail(user.getEmail());
        // 电话
        userInfo.setPhone(user.getPhone());
        // 系统编码
        userInfo.setSysCode(user.getSysCode());
        // 机构
        UserOrgInfoDto userOrgInfoDto = new UserOrgInfoDto();
        userOrgInfoDto.setId(orgInfo.getId());
        userOrgInfoDto.setName(orgInfo.getName());
        userOrgInfoDto.setPath(orgInfo.getPath());
        userInfo.setOrgInfo(userOrgInfoDto);
        // 角色
        userInfo.setRoles(getRoles(roles));
        // 权限
        userInfo.setPermissions(getPermissions(permissions));
        // 是否启用
        userInfo.setEnabled(user.getEnabled());
        return userInfo;
    }

    private List<String> getPermissionsByAdmin(List<SysPermission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(SysPermission::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRolesByAdmin(List<SysRole> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(SysRole::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getPermissions(List<BasPermission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(BasPermission::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRoles(List<BasRole> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(BasRole::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }
}
