package com.hb0730.sys.base.granter;

import com.hb0730.base.Pair;
import com.hb0730.base.R;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.cache.core.CacheUtil;
import com.hb0730.configuration.config.AuthenticationConfig;
import com.hb0730.operator.log.core.util.OperatorLogs;
import com.hb0730.security.UserInfo;
import com.hb0730.sys.base.convert.UserInfoConvert;
import com.hb0730.sys.base.define.cache.UserCacheKeyDefine;
import com.hb0730.sys.base.enums.LoginTokenStatusEnums;
import com.hb0730.sys.base.model.dto.LoginInfo;
import com.hb0730.sys.base.model.dto.LoginToken;
import com.hb0730.sys.base.model.dto.LoginTokenIdentity;
import com.hb0730.sys.base.model.vo.UserInfoVO;
import com.hb0730.sys.base.util.TokenUtil;
import com.hb0730.sys.enums.MenuTypeEnums;
import com.hb0730.sys.system.model.entity.SysPermission;
import com.hb0730.sys.system.model.entity.SysRole;
import com.hb0730.sys.system.model.entity.SysUser;
import com.hb0730.sys.system.model.entity.SysUserRole;
import com.hb0730.sys.system.service.SysPermissionService;
import com.hb0730.sys.system.service.SysRolePermissionService;
import com.hb0730.sys.system.service.SysRoleService;
import com.hb0730.sys.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * 抽象的token granter
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Slf4j
public abstract class AbstractTokenGranter implements TokenGranter {
    @Autowired
    private CacheUtil cache;
    @Autowired
    private AuthenticationConfig authConfig;
    @Autowired
    private SysUserService userService;
    @Autowired
    private UserInfoConvert userInfoConvert;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysRolePermissionService rolePermissionService;
    @Autowired
    private SysPermissionService permissionService;

    @Override
    public R<String> login(LoginInfo loginInfo) {
        // 重新设置日志上下文
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(loginInfo.getUsername());
        OperatorLogs.setUser(userInfo);
        // 1. 检查参数
        R<String> r = checkParam(loginInfo);
        if (!r.isSuccess()) {
            return r;
        }
        // 2. 检查验证码
        r = checkCaptcha(loginInfo);
        if (!r.isSuccess()) {
            return r;
        }
        // 3. 获取系统用户
        Optional<SysUser> userR = getUser(loginInfo);
        if (userR.isEmpty()) {
            return R.NG("用户不存在,请注册");
        }
        SysUser user = userR.get();
        // 重新设置日志上下文
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        OperatorLogs.setUser(userInfo);
        // 4. 检查密码
        r = checkPassword(loginInfo, user);
        if (!r.isSuccess()) {
            return r;
        }
        // 5. 检查状态
        r = checkStatus(user);
        if (!r.isSuccess()) {
            return r;
        }
        // 6. 生成token
        String token = generatorToken(user);
        return R.OK(token);
    }

    /**
     * 检查参数
     *
     * @param loginInfo 登录信息
     * @return 是否成功
     */
    protected abstract R<String> checkParam(LoginInfo loginInfo);

    /**
     * 检查验证码
     *
     * @param loginInfo 登录信息
     * @return 是否成功
     */
    protected R<String> checkCaptcha(LoginInfo loginInfo) {
        return R.OK();
    }

    /**
     * 获取系统用户
     *
     * @param loginInfo 登录信息
     * @return 系统用户
     */
    protected abstract Optional<SysUser> getUser(LoginInfo loginInfo);

    /**
     * 检查密码
     *
     * @param loginInfo 登录信息
     * @param user      系统用户
     * @return 是否成功
     */
    protected R<String> checkPassword(LoginInfo loginInfo, SysUser user) {
        return R.OK();
    }

    /**
     * 检查状态
     *
     * @param user 系统用户
     * @return 是否成功
     */
    protected R<String> checkStatus(SysUser user) {
        if (user == null) {
            return R.NG("该用户不存在，请注册");
        }
        // 情况2：根据用户信息查询，该用户已注销
        // 情况3：根据用户信息查询，该用户已冻结
        return R.OK();
    }

    @Override
    public R<UserInfoVO> currentUser(String token) {
        if (StrUtil.isBlank(token)) {
            return R.NG("token不能为空");
        }
        LoginToken loginToken = getLoginToken(token);
        if (loginToken == null) {
            return R.NG("token无效");
        }
        // 获取用户信息
        UserInfo userInfo = getUserInfoById(loginToken.getId());
        if (userInfo == null) {
            return R.NG("用户不存在");
        }
        return R.OK(userInfoConvert.do2vo(userInfo));
    }


    @Override
    public R<String> logout(String token) {
        if (StrUtil.isBlank(token)) {
            return R.NG("token不能为空");
        }
        Pair<String, Long> parseToken = TokenUtil.parseToken(token);
        if (parseToken == null) {
            return R.NG("token无效");
        }
        String id = parseToken.getCode();
        Long current = parseToken.getMessage();
        // 删除 loginToken & refreshToken
        cache.del(UserCacheKeyDefine.LOGIN_TOKEN.format(id, current),
                UserCacheKeyDefine.LOGIN_REFRESH.format(id, current),
                UserCacheKeyDefine.USER_INFO.format(id));
        return R.OK();
    }

    /**
     * 生成token
     *
     * @param user 用户
     * @return 用户信息
     */
    private String generatorToken(SysUser user) {
        // 1. 设置上次登录时间
        userService.updateLastLoginTime(user.getId());

        // 2. 删除用户缓存
        this.delUserCache(user);

        // 3. 重设用户缓存
        this.setUserCache(user);

        long current = System.currentTimeMillis();

        // 4. 不允许多端登录
        if (!authConfig.getAllowMultiDevice()) {
            // 无效化其他缓存
            this.invalidOtherDeviceToken(user, current);
        }
        // 5. 生成token
        return this.generatorLoginToken(user, current);
    }

    /**
     * 获取loginToken 信息
     *
     * @param token token
     * @return token
     */
    protected LoginToken getLoginToken(String token) {
        Pair<String, Long> tokenPair = TokenUtil.parseToken(token);
        if (tokenPair == null) {
            return null;
        }
        // 获取登录 key value
        String tokenCacheKey = UserCacheKeyDefine.LOGIN_TOKEN.format(tokenPair.getCode(), tokenPair.getMessage());
        Optional<String> tokenCacheValue = cache.getString(tokenCacheKey);
        if (tokenCacheValue.isPresent()) {
            return JsonUtil.DEFAULT.json2Obj(tokenCacheValue.get(), LoginToken.class);
        }
        // loginToken 不存在 需要查询 refreshToken
        if (!authConfig.getAllowRefresh()) {
            return null;
        }
        String refreshTokenCacheKey = UserCacheKeyDefine.LOGIN_REFRESH.format(tokenPair.getCode(), tokenPair.getMessage());
        Optional<String> refreshTokenCacheValue = cache.getString(refreshTokenCacheKey);
        // refreshToken 不存在
        if (refreshTokenCacheValue.isEmpty()) {
            return null;
        }
        // 执行续签操作
        LoginToken refreshToken = JsonUtil.DEFAULT.json2Obj(refreshTokenCacheValue.get(), LoginToken.class);
        int refreshCount = refreshToken.getRefreshCount() + 1;
        refreshToken.setRefreshCount(refreshCount);
        // 设置登录缓存
        cache.setJson(tokenCacheKey, refreshToken, UserCacheKeyDefine.LOGIN_TOKEN.getTimeout(), UserCacheKeyDefine.LOGIN_TOKEN.getUnit());
        if (refreshCount < authConfig.getRefreshMaxCount()) {
            // 小于续签最大次数 则再次设置 refreshToken
            cache.setJson(refreshTokenCacheKey, refreshToken, UserCacheKeyDefine.LOGIN_REFRESH.getTimeout(), UserCacheKeyDefine.LOGIN_REFRESH.getUnit());

        } else {
            // 大于等于续签最大次数 则删除
            cache.del(refreshTokenCacheKey);
        }
        return refreshToken;
    }

    /**
     * 删除用户缓存
     *
     * @param user 用户
     */
    private void delUserCache(SysUser user) {
        // 用户信息缓存
        String userCacheKey = UserCacheKeyDefine.USER_INFO.format(user.getId());
        // 登录失败次数缓存
        String loginFailureCount = UserCacheKeyDefine.LOGIN_FAILURE.format(user.getUsername());
        cache.del(userCacheKey, loginFailureCount);
    }

    /**
     * 设置用户缓存
     *
     * @param user 用户
     */
    private UserInfo setUserCache(SysUser user) {
        UserInfo userInfo = userInfoConvert.toObject(user);
        // 用户信息缓存
        String userCacheKey = UserCacheKeyDefine.USER_INFO.format(user.getId());
        String _cacheKey = UserCacheKeyDefine.USER_INFO.format(user.getUsername());


        List<SysUserRole> roles = userService.findEffectiveRoles(user.getId());
        List<String> btn = new ArrayList<>();
        List<String> roleCodes = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roles)) {
            List<String> roleIds = roles.stream().map(SysUserRole::getRoleId).toList();

            List<String> permissionIds = rolePermissionService.getPermsByRoleIds(roleIds);
            // 获取角色
            List<SysRole> sysRoles = roleService.listByIds(roleIds);
            if (CollectionUtil.isNotEmpty(sysRoles)) {
                // 过滤禁用
                roleCodes = sysRoles.stream()
                        .filter(SysRole::getStatus)
                        .map(SysRole::getRoleCode).toList();
            }

            // 获取按钮权限
            if (CollectionUtil.isNotEmpty(permissionIds)) {
                List<SysPermission> sysPermissions = permissionService.listByIds(permissionIds);
                //过滤禁用&按钮
                btn = sysPermissions.stream()
                        .filter(p -> MenuTypeEnums.BUTTON.getCode().equals(p.getMenuType()))
                        .filter(SysPermission::getStatus)
                        .map(SysPermission::getPerms).toList();
            }
        }

        userInfo.setRoles(roleCodes);
        userInfo.setPermissions(btn);

        // 缓存
        cache.setJson(userCacheKey, userInfo, UserCacheKeyDefine.USER_INFO.getTimeout(),
                UserCacheKeyDefine.USER_INFO.getUnit());
        // 缓存
        cache.setJson(_cacheKey, userInfo, UserCacheKeyDefine.USER_INFO.getTimeout(),
                UserCacheKeyDefine.USER_INFO.getUnit());

        return userInfo;
    }

    /**
     * 无效化其他设备
     *
     * @param user      用户
     * @param loginTime 登录时间
     */
    private void invalidOtherDeviceToken(SysUser user, long loginTime) {
        String tokenCacheKey = UserCacheKeyDefine.LOGIN_TOKEN.format(user.getId(), "*");
        Optional<Set<String>> tokenCacheKeys = cache.scanKeys(tokenCacheKey);
        if (tokenCacheKeys.isPresent()) {
            // 修改登录信息
            List<String> values = cache.getString(tokenCacheKeys.get())
                    .orElse(new ArrayList<>());
            // 获取有效登录信息
            List<LoginToken> loginTokenInfoList = values
                    .stream()
                    .filter(Objects::nonNull)
                    .map(val -> JsonUtil.DEFAULT.json2Obj(val, LoginToken.class))
                    .filter(val -> LoginTokenStatusEnums.OK.getCode().equals(val.getStatus()))
                    .toList();
            // 修改登录信息
            for (LoginToken loginTokenInfo : loginTokenInfoList) {
                String deviceLoginKey = UserCacheKeyDefine.LOGIN_TOKEN.format(user.getId(),
                        loginTokenInfo.getOrigin().getLoginTime());
                loginTokenInfo.setStatus(LoginTokenStatusEnums.OTHER_DEVICE.getCode());
                loginTokenInfo.setOverride(new LoginTokenIdentity(loginTime));
                cache.setJson(deviceLoginKey, loginTokenInfo, UserCacheKeyDefine.LOGIN_TOKEN.getTimeout(),
                        UserCacheKeyDefine.LOGIN_TOKEN.getUnit());
            }

        }
        // 删除续签信息
        String refreshTokenCacheKey = UserCacheKeyDefine.LOGIN_REFRESH.format(user.getId(), "*");
        cache.delScan(refreshTokenCacheKey);
    }

    /**
     * 生成token
     *
     * @param user      用户
     * @param loginTime 登录时间
     * @return token
     */
    private String generatorLoginToken(SysUser user, long loginTime) {
        String id = user.getId();
        // 生成 loginToken
        String loginKey = UserCacheKeyDefine.LOGIN_TOKEN.format(id, loginTime);
        LoginToken loginToken = new LoginToken();
        loginToken.setId(id);
        loginToken.setStatus(LoginTokenStatusEnums.OK.getCode());
        loginToken.setRefreshCount(0);
        // 设置原始登录身份
        loginToken.setOrigin(new LoginTokenIdentity(loginTime));
        cache.setJson(loginKey, loginToken, UserCacheKeyDefine.LOGIN_TOKEN.getTimeout(), UserCacheKeyDefine.LOGIN_TOKEN.getUnit());
        // 生成 refreshToken
        if (authConfig.getAllowRefresh()) {
            String refreshKey = UserCacheKeyDefine.LOGIN_REFRESH.format(id, loginTime);
            cache.setJson(refreshKey, loginToken, UserCacheKeyDefine.LOGIN_REFRESH.getTimeout(), UserCacheKeyDefine.LOGIN_REFRESH.getUnit());
        }
        // 生成 token
        return TokenUtil.generateToken(id, loginTime);
    }


    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    protected UserInfo getUserInfoById(String id) {
        String userInfoKey = UserCacheKeyDefine.USER_INFO.format(id);
        Optional<String> userInfoValue = cache.getString(userInfoKey);
        if (userInfoValue.isPresent()) {
            return JsonUtil.DEFAULT.json2Obj(userInfoValue.get(), UserInfo.class);
        }
        // 查询用户信息
        SysUser user = userService.getById(id);
        if (user == null) {
            return null;
        }
        // 缓存用户信息
        return setUserCache(user);
    }
}
