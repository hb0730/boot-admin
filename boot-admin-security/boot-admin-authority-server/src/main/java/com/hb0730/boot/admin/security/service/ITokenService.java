package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.security.model.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface ITokenService {
    /*
     * 会话超时时间，默认2小时
     */
    long MILLIS_SECOND = 1000;

    /*
     *置换保护时间，默认1小时
     */

    long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    /*
     *旧token延迟过期时间
     */

    Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 创建token
     *
     * @param loginUser 用户信息
     * @return token
     */
    String createAccessToken(LoginUser loginUser);

    /**
     * <p>
     * 刷新令牌
     * </P>
     *
     * @param loginUser 用户信息
     */
    void refreshAccessToken(LoginUser loginUser);

    /**
     * <p>
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     * </p>
     *
     * @param loginUser 用户信息
     */
    void verifyAccessToken(LoginUser loginUser);

    /**
     * <p>
     * 删除用户信息
     * </p>
     *
     * @param request 请求
     */
    void delLoginUser(HttpServletRequest request);

    /**
     * <p>
     * 删除accessToken并删除用户信息
     * </P>
     *
     * @param accessToken token令牌
     */
    void deleteAccessToken(String accessToken);
}
