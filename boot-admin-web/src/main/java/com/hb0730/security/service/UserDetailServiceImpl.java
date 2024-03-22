package com.hb0730.security.service;

import com.hb0730.base.exception.BadRequestException;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.biz.dto.sys.system.PermissionDto;
import com.hb0730.biz.dto.sys.system.RoleDto;
import com.hb0730.biz.dto.sys.system.UserLoginDto;
import com.hb0730.commons.JR;
import com.hb0730.security.model.dto.LoginInfoDto;
import com.hb0730.security.security.cache.UserProvider;
import com.hb0730.sys.service.PermissionRpcService;
import com.hb0730.sys.service.RoleRpcService;
import com.hb0730.sys.service.UserRpcService;
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
 * @date 2024/2/21
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRpcService userRemoteRpcService;
    private final UserProvider userProvider;
    private final PermissionRpcService permissionRpcService;
    private final RoleRpcService roleRpcService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginInfoDto user = userProvider.getUser(username);
        if (null == user) {
            JR<UserLoginDto> jr = userRemoteRpcService.loginByUsername(username);
            if (!jr.isSuccess()) {
                throw new UsernameNotFoundException(jr.getMessage());
            }
            UserLoginDto loginInfo = jr.getResult();
            if (!loginInfo.isEnabled()) {
                throw new UsernameNotFoundException("账号未启用！");
            }
            user = new LoginInfoDto();
            user.setUser(loginInfo);

            List<PermissionDto> permissions = null; // 权限
            List<RoleDto> roles = null; // 角色
            // 查询用户权限
            JR<List<PermissionDto>> permissionRes = permissionRpcService.findByUserId(loginInfo.getId());
            if (!permissionRes.isSuccess()) {
                throw new BadRequestException(permissionRes.getMessage());
            }
            permissions = permissionRes.getResult();

            // 查询用户角色
            JR<List<RoleDto>> roleRes = roleRpcService.findByUserId(loginInfo.getId());
            if (!roleRes.isSuccess()) {
                throw new BadRequestException(roleRes.getMessage());
            }
            roles = roleRes.getResult();
            // 设置权限和角色
            user.setPermissions(getPermissions(permissions));
            user.setRoles(getRoles(roles));

            userProvider.putUser(username, user);
        }
        return user;

    }


    private List<String> getPermissions(List<PermissionDto> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream().map(PermissionDto::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRoles(List<RoleDto> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream().map(RoleDto::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }
}
