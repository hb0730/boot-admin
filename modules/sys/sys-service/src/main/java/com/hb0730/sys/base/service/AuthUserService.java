package com.hb0730.sys.base.service;

import com.hb0730.base.Pair;
import com.hb0730.base.R;
import com.hb0730.sys.base.model.request.RestPasswordRequest;
import com.hb0730.sys.base.util.TokenUtil;
import com.hb0730.sys.system.model.dto.RestPasswordDTO;
import com.hb0730.sys.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 授权用户
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthUserService {
    private final SysUserService userService;

    /**
     * 重置密码
     *
     * @param token   token
     * @param request 请求
     * @return 是否成功
     */
    public R<String> restPassword(String token, RestPasswordRequest request) {
        Pair<String, Long> parseToken = TokenUtil.parseToken(token);
        if (null == parseToken) {
            return R.NG("非法token");
        }
        String userId = parseToken.getCode();
        // 重置密码
        RestPasswordDTO dto = new RestPasswordDTO();
        dto.setUserId(userId);
        dto.setOldPassword(request.getOldPassword());
        dto.setNewPassword(request.getNewPassword());
        return userService.resetPassword(dto);
    }
}
