package com.hb0730.boot.admin.security.service;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysUser;
import com.hb0730.boot.admin.modules.sys.system.service.SysPermissionService;
import com.hb0730.boot.admin.modules.sys.system.service.SysUserService;
import com.hb0730.boot.admin.security.model.UserInfo;
import com.hb0730.boot.admin.security.token.RedisCacheUserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final RedisCacheUserProvider redisCacheUserProvider;
    private final SysUserService sysUserService;
    private final SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("获取授权用户信息: username: {}", username);
        Optional<UserInfo> optional = redisCacheUserProvider.getUser(username);
        if (optional.isPresent()) {
            UserInfo userInfo = optional.get();
            log.info("获取授权用户信息->缓存获取 : {}", userInfo);
            return userInfo;
        }
        log.info("获取授权用户信息->重新从库获取");
        // 测试用例
//        UserInfo info = new UserInfo();
//        info.setPassword(PasswordUtil.encoder("123456"));
//        info.setUsername("admin");
//        info.setRoles(Arrays.asList("ROLE_ADMIN"));
//        info.setPermissions(Arrays.asList("test::query", "test:add"));
//        info.setIsEnabled(1);
        // 正式
        Optional<SysUser> userOptional = sysUserService.loadUserByUsername(username);
        SysUser user = userOptional.orElseThrow(() -> new UsernameNotFoundException("根据用户名未找到用户信息"));
        // 角色code
        Set<String> roleCodes;
        // 权限code
        Set<String> preCodes = new HashSet<>();
        if (user.isManager()) {
            roleCodes = sysUserService.allRoleCode();
            preCodes = sysPermissionService.allPermissionPre();
        } else {
            // 查找角色
            roleCodes = sysUserService.getRoleCodeByUsername(username);
            // 用户信息ID 获取 用户角色ID集合
            Set<String> roleIds = sysUserService.queryRoleIdsByUserId(user.getId());

            if (CollectionUtil.isNotEmpty(roleIds)) {
                Set<String> permissionIds = sysPermissionService.listPermissionIdsByRoleIds(List.copyOf(roleIds));
                preCodes = sysPermissionService.listPermissionPreByIds(List.copyOf(permissionIds));
            }
        }
        UserInfo userInfo = UserInfo.convert(
            user,
            List.copyOf(roleCodes),
            List.copyOf(preCodes));
        redisCacheUserProvider.cacheUser(username, userInfo);
        return userInfo;
    }
}
