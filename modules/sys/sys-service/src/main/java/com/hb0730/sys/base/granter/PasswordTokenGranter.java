package com.hb0730.sys.base.granter;

import cn.hutool.core.convert.Convert;
import com.hb0730.base.R;
import com.hb0730.base.utils.PasswdUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.base.utils.ValidatorUtil;
import com.hb0730.cache.core.CacheUtil;
import com.hb0730.configuration.config.AuthenticationConfig;
import com.hb0730.mybatis.core.encrypt.MybatisEncryptService;
import com.hb0730.security.UserInfo;
import com.hb0730.spring.security.core.service.UserService;
import com.hb0730.sys.base.define.cache.UserCacheKeyDefine;
import com.hb0730.sys.base.model.dto.LoginInfo;
import com.hb0730.sys.base.model.dto.LoginToken;
import com.hb0730.sys.system.model.entity.SysUser;
import com.hb0730.sys.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * username/password 登录
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Component(PasswordTokenGranter.GRANT_TYPE)
@RequiredArgsConstructor
public class PasswordTokenGranter extends AbstractTokenGranter implements UserService {
    public static final String GRANT_TYPE = "password";
    private final CacheUtil cache;
    private final AuthenticationConfig authConfig;
    private final SysUserService userService;
    private final MybatisEncryptService encryptService;

    @Override
    protected R<String> checkParam(LoginInfo loginInfo) {
        String username = loginInfo.getUsername();
        String password = loginInfo.getPassword();
        if (StrUtil.isAnyBlank(username, password)) {
            return R.NG("用户名或密码不能为空");
        }
        return R.OK();
    }

    @Override
    protected Optional<SysUser> getUser(LoginInfo loginInfo) {
        String _username = encryptService.encrypt(loginInfo.getUsername());
        boolean email = ValidatorUtil.isEmail(loginInfo.getUsername());
        if (email) {

            return Optional.ofNullable(userService.findByEmail(_username));
        }
        boolean phone = ValidatorUtil.isMobile(loginInfo.getUsername());
        if (phone) {
            return Optional.ofNullable(userService.findByPhone(loginInfo.getUsername()));
        }
        return Optional.ofNullable(userService.findByUsername(loginInfo.getUsername()));
    }

    @Override
    protected R<String> checkPassword(LoginInfo loginInfo, SysUser user) {
        // 登陆失败次数
        String loginFailureCount = UserCacheKeyDefine.LOGIN_FAILURE.format(loginInfo.getUsername());
        // 多次认证失败禁止登录
        if (Convert.toInt(cache.getString(loginFailureCount), 0) >= authConfig.getLoginFailedLockCount()) {
            return R.NG("账号已被锁定，请联系管理员重置密码");
        }
        // 2. 校验用户名或密码是否正确
        if (!PasswdUtil.matches(loginInfo.getPassword(), user.getSalt(), user.getPassword())) {
            // 记录登录失败次数
            cache.incr(loginFailureCount, 1);
            return R.NG("用户名或密码错误");
        }
        return R.OK();
    }

    @Override
    public UserInfo getUserByToken(String token) {
        LoginToken loginToken = getLoginToken(token);
        if (loginToken == null) {
            return null;
        }
        // 检测状态
        if (loginToken.getStatus() != 1) {
            // 删除缓存
            UserCacheKeyDefine.LOGIN_TOKEN.format(loginToken.getId(), loginToken.getOrigin().getLoginTime());
            return null;
        }
        // 获取登录信息
        SysUser user = userService.getById(loginToken.getId());
        if (user == null) {
            return null;
        }
        // 获取用户信息
        UserInfo userInfo = getUserInfoById(user.getId());
        if (userInfo == null) {
            return null;
        }
        // 检查用户状态
        // 更新登录时间
        return userInfo;
    }

}
