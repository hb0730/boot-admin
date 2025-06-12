package com.hb0730.framework;

import cn.hutool.core.bean.BeanUtil;
import com.hb0730.security.IUserProxyService;
import com.hb0730.security.UserInfo;
import com.hb0730.sys.system.model.entity.SysUser;
import com.hb0730.sys.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/6/12
 */
@Component
@RequiredArgsConstructor
public class UserProxyService implements IUserProxyService {
    private final SysUserService userService;

    @Override
    public UserInfo findUsername(String username) {
        SysUser user = userService.findByUsername(username);
        return BeanUtil.toBean(user, UserInfo.class);
    }
}
