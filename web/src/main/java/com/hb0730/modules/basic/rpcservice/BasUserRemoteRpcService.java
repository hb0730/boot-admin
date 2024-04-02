package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.rpc.client.ClientRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.basic.domain.BasUserDto;
import com.hb0730.rpc.basic.domain.BasUserRestPasswordDto;
import com.hb0730.rpc.basic.domain.BasUserSaveDto;
import com.hb0730.rpc.basic.domain.query.BasUserQuery;
import com.hb0730.rpc.basic.service.BasUserRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
public class BasUserRemoteRpcService extends ClientRemoteRpcService<BasUserRpcService> implements BasUserRpcService {
    @Override
    public JR<String> getSysCodeByUsername(String username) {
        return getRpcService().getSysCodeByUsername(username);
    }

    @Override
    public JR<BasUserDto> findByUsername(String username) {
        return getRpcService().findByUsername(username);
    }

    @Override
    public JR<String> updateLastLoginTime(String username) {
        return getRpcService().updateLastLoginTime(username);
    }

    @Override
    public JR<JsfPage<BasUserDto>> page(BasUserQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<List<BasUserDto>> list(BasUserQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<Boolean> existsByUsername(String username, String sysCode, String id) {
        return getRpcService().existsByUsername(username, sysCode, id);
    }

    @Override
    public JR<String> save(BasUserSaveDto dto) {
        return getRpcService().save(dto);
    }

    @Override
    public JR<String> updateById(BasUserSaveDto dto) {
        return getRpcService().updateById(dto);
    }

    @Override
    public JR<String> restPassword(BasUserRestPasswordDto dto) {
        return getRpcService().restPassword(dto);
    }
}
