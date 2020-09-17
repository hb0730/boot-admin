package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.exceptions.UsernameNotFoundException;
import com.hb0730.boot.admin.project.system.user.model.dto.UserDTO;
import com.hb0730.boot.admin.project.system.user.service.IUserInfoService;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.commons.spring.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final IUserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userInfoService.loadUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return BeanUtils.transformFrom(user, User.class);
    }
}
