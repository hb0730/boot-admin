package com.hb0730.boot.admin.base.util;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hb0730.boot.admin.base.BootAdminConst;
import com.hb0730.boot.admin.base.exception.BootAdminException;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

/**
 * JWT util
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/30
 */
public class JwtUtil {
    /**
     * 过期时间（毫秒）：Token过期时间30分钟（用户登录过期时间是此时间的两倍，以token在reids缓存时间为准）
     */
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 获取 request 里传递的 token
     *
     * @param request .
     * @return jwt_token
     */
    public static String getTokenByRequest(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (token == null) {
            token = request.getHeader(BootAdminConst.X_ACCESS_TOKEN);
        }
        return token;
    }

    /**
     * 根据request中的token获取用户账号
     *
     * @param request 请求
     * @return 用户名
     */
    @Nullable
    public static String getUserNameByToken(HttpServletRequest request) throws BootAdminException {
        String accessToken = request.getHeader(BootAdminConst.X_ACCESS_TOKEN);
        if (StrUtil.isBlank(accessToken)) {
            return null;
        }
        String username = getUsername(accessToken);
        if (StrUtil.isBlank(username)) {
            throw new BootAdminException("未获取到用户");
        }
        return username;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 生成签名,EXPIRE_TIME后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
    }


    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
