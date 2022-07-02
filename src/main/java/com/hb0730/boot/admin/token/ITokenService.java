package com.hb0730.boot.admin.token;

import com.hb0730.boot.admin.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * login AccessToken
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface ITokenService {
    /**
     * 令牌前缀
     */
    String LOGIN_USER_KEY_PREFIX = "login_user_key:";

    /**
     * 获取当前登录用户
     *
     * @param request 请求
     * @return 当前请求登录用户
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 创建accessToken
     *
     * @param user 登录用户
     * @return AccessToken
     */
    String createAccessToken(User user);

    /**
     * 刷新AccessToken令牌
     *
     * @param user 登录用户
     */
    void refreshAccessToken(User user);

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param request 请求
     */
    void verifyAccessToken(HttpServletRequest request);

    /**
     * 删除用户信息
     *
     * @param request 请求
     */
    void delLoginUser(HttpServletRequest request);


    /**
     * 删除accessToken并删除用户信息
     *
     * @param accessToken token令牌
     */
    void deleteAccessToken(String accessToken);

    /**
     * 获取在线用户
     *
     * @return 在线用户 key为accessToken,value为{@link User}
     */
    Map<String, UserDetails> getOnline();

    /**
     * 从数据声明生成令牌
     *
     * @param values 数据声明
     * @param secret 密钥
     * @return 令牌
     */
    default String createToken(Map<String, Object> values, String secret) {
        return Jwts.builder()
            .setClaims(values)
            .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token  令牌
     * @param secret 密钥
     * @return 数据声明
     */
    default Claims parseToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
