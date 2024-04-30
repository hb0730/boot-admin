package com.hb0730.security.service;

import com.hb0730.security.cache.UserProvider;
import com.hb0730.security.context.AuthenticationContext;
import com.hb0730.security.context.AuthenticationContextHolder;
import com.hb0730.security.domain.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserProvider userProvider;
    private final UserLoginService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationContext authenticationContext = AuthenticationContextHolder.getContext();
        Boolean isAdminLogin = false;
        if (null != authenticationContext) {
            isAdminLogin = authenticationContext.getTenantLogin();
        }
        Optional<String> sysCode = Optional.empty();
        if (!isAdminLogin) {
            sysCode = userService.getSysCodeByUsername(username);
        }
        String cacheKey = getCacheKey(username, sysCode);
        UserInfoDto user = userProvider.getUser(cacheKey);
        if (null == user) {
            if (Boolean.TRUE.equals(isAdminLogin)) {
                user = userService.findByUsername(username);
            } else {
                user = userService.findByUsername(username, sysCode.orElse(null));
            }
            userProvider.putUser(cacheKey, user);
        }

        return user;
    }

    private String getCacheKey(String username, Optional<String> sysCode) {
        return sysCode.map(s -> s + ":" + username).orElse(username);
    }
}