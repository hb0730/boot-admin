package com.hb0730.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.security.config.SecurityProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/22
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
        String accessToken = request.getHeader(JwtUtil.securityProperties.getHeaderName());
        if (StrUtil.isNotBlank(accessToken)) {
            String prefix = JwtUtil.securityProperties.getHeaderPrefix();
            if (StrUtil.isNotBlank(prefix)) {
                return accessToken.replace(prefix, "");
            }
            return accessToken;
        }
        return null;
    }

    /**
     * 获取用户名
     *
     * @param request .
     * @return .
     */
    public static String getUsername(HttpServletRequest request) {
        return getUsername(getToken(request));
    }

    /**
     * 获取用户名
     *
     * @param token .
     * @return .
     */
    public static String getUsername(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }


    /**
     * 签名
     *
     * @param username 用户名
     * @param secret   密钥
     * @return 签名
     */
    public static String sign(String username, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withClaim("username", username).sign(algorithm);
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
