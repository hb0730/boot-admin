package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.base.util.PasswordUtil;
import com.hb0730.boot.admin.security.model.UserInfo;
import com.hb0730.boot.admin.security.token.RedisCacheUserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final RedisCacheUserProvider redisCacheUserProvider;

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
        UserInfo info = new UserInfo();
        info.setPassword(PasswordUtil.encoder("123456"));
        info.setUsername("admin");
        info.setRoles(Arrays.asList("ROLE_ADMIN"));
        info.setPermissions(Arrays.asList("test::query", "test:add"));
        info.setIsEnabled(1);
        redisCacheUserProvider.cacheUser(username, info);
        return info;
    }
}
