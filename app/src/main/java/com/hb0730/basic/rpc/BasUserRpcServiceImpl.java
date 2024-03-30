package com.hb0730.basic.rpc;

import com.hb0730.base.conf.server.BaseServerRpcService;
import com.hb0730.basic.domain.BasUser;
import com.hb0730.basic.rpc.mapstruct.BasUserMapper;
import com.hb0730.basic.service.IBasUserService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasUserDto;
import com.hb0730.rpc.basic.service.BasUserRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasUserRpcServiceImpl extends BaseServerRpcService<BasUserRpcService> implements BasUserRpcService {
    private final BasUserMapper userMapper;
    private final IBasUserService userService;

    @Override
    public JR<String> getSysCodeByUsername(String username) {
        return JR.okData(userService.getSysCodeByUsername(username));
    }

    @Override
    public JR<BasUserDto> findByUsername(String username) {
        BasUser user = userService.findByUsername(username);
        BasUserDto res = userMapper.toDto(user);
        return JR.okData(res);
    }

    @Override
    public JR<String> updateLastLoginTime(String username) {
        userService.updateLastLoginTime(username);
        return JR.ok();
    }
}
