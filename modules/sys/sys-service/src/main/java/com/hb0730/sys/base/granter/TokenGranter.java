package com.hb0730.sys.base.granter;


import com.hb0730.base.R;
import com.hb0730.sys.base.model.dto.LoginInfo;
import com.hb0730.sys.base.model.vo.UserInfoVO;

/**
 * 授予token接口
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public interface TokenGranter {
    /**
     * 登录
     *
     * @param loginInfo 登录信息
     * @return token
     */
    R<String> login(LoginInfo loginInfo);

    /**
     * 获取当前用户
     *
     * @param token token
     * @return 用户信息
     */
    R<UserInfoVO> currentUser(String token);

    /**
     * 退出
     *
     * @param token token
     * @return 是否成功
     */
    R<String> logout(String token);
}
