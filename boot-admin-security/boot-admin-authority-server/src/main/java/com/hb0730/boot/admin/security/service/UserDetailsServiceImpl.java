package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.security.model.LoginUser;
import org.assertj.core.util.Sets;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            LoginUser loginUser = new LoginUser();
            loginUser.setUsername(username);
            loginUser.setPassword(new BCryptPasswordEncoder().encode("123456"));
            Set<String> permission = Sets.newHashSet();
            permission.add("ROLE_admin");
            permission.add("admin:query");
            loginUser.setPermissions(permission);
            return loginUser;
        }
        return null;
    }
}
