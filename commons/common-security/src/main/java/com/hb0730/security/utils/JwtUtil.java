package com.hb0730.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.security.config.SecurityProperties;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
public class JwtUtil {
    @Setter
    @Getter
    private static SecurityProperties securityProperties;

    /**
     * 获取 request 里传递的 token
     *
     * @param request .
     * @return .
     */
    public static String getToken(HttpServletRequest request) {
        String accessToken = request.getHeader(JwtUtil.securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(accessToken)) {
            String prefix = JwtUtil.securityProperties.getTokenHeaderPrefix();
            if (StrUtil.isNotBlank(prefix)) {
                return accessToken.replace(prefix, "");
            }
            return accessToken;
        }
        return null;
    }

    /**
     * 获取租户
     *
     * @param request .
     * @return .
     */
    public static String getTenant(HttpServletRequest request) {
        String tenant = request.getHeader(JwtUtil.securityProperties.getTenantHeader());
        if (StrUtil.isNotBlank(tenant)) {
            return tenant;
        }
        return null;
    }


    public static Optional<String> getUserid(HttpServletRequest request) {
        return getUserid(getToken(request));
    }

    /**
     * 获取用户名
     *
     * @param request .
     * @return .
     */
    public static Optional<String> getUsername(HttpServletRequest request) {
        return getUsername(getToken(request));
    }

    /**
     * 获取用户id
     *
     * @param token .
     * @return .
     */
    public static Optional<String> getUserid(String token) {
        Claim userid = JWT.decode(token).getClaim("userid");
        return Optional.ofNullable(userid.asString());
    }

    /**
     * 获取用户名
     *
     * @param token .
     * @return .
     */
    public static Optional<String> getUsername(String token) {
        Claim claim = JWT.decode(token).getClaim("username");
        return Optional.ofNullable(claim.asString());
    }

    /**
     * 获取租户
     *
     * @param token .
     * @return .
     */
    public static Optional<String> getTenant(String token) {
        Claim claim = JWT.decode(token).getClaim("sysCode");
        return Optional.ofNullable(claim.asString());
    }


    /**
     * 签名
     *
     * @param username 用户名
     * @param secret   密钥
     * @param sysCode  系统编码
     * @return 签名
     */
    public static String sign(String id, String username, String secret, @Nullable String sysCode) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userid", id)
                .withClaim("username", username)
                .withClaim("sysCode", sysCode)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String username, String token, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            // 效验TOKEN
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}