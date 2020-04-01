package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.user.model.dto.LoginUserDTO;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.project.user.service.ISystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private ISystemUserService systemUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        LoginUserDTO loginUserDTO = systemUserService.loadUserByUsername(username);
        if (Objects.isNull(loginUserDTO)){
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        return BeanUtils.transformFrom(loginUserDTO,LoginUser.class);
    }
}
