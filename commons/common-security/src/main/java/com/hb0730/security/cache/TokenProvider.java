package com.hb0730.security.cache;

import com.hb0730.security.domain.dto.UserInfoDto;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
public interface TokenProvider {
    /**
     * 创建token
     *
     * @param dto 登录信息
     * @return token
     */
    String createAccessToken(UserInfoDto dto);

    /**
     * 刷新token
     *
     * @param accessToken token
     * @return token
     */
    boolean checkRenewal(String accessToken);

    /**
     * 删除token
     *
     * @param accessToken token
     * @return 是否成功
     */
    boolean removeToken(String accessToken);

    /**
     * 获取token的值
     *
     * @param accessToken token
     * @return 值
     */
    String getValue(String accessToken);
}