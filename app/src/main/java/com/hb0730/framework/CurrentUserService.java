package com.hb0730.framework;

import com.hb0730.base.meta.ICurrentUserService;
import com.hb0730.security.SecurityUtils;
import com.hb0730.security.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/2/12
 */
@Component
public class CurrentUserService implements ICurrentUserService {
    @Override
    public UserInfo getCurrentUser() {
        return (UserInfo) SecurityUtils.getLoginUser().orElseGet(UserInfo::new);
    }
}
