package com.hb0730.sys.system.rpcservice;

import com.hb0730.biz.dto.sys.system.DeptDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.conf.rpc.remote.BaseRemoteService;
import com.hb0730.sys.bean.DeptQuery;
import com.hb0730.sys.service.DeptRpcService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Service
@Slf4j
public class DeptRemoteRpcService extends BaseRemoteService<DeptRpcService> implements DeptRpcService {

    @Override
    public JR<Boolean> existsByCodeAndIdNot(String code, @Nullable Integer id) {
        return getRpcService().existsByCodeAndIdNot(code, id);
    }

    @Override
    public JR<List<DeptDto>> list(DeptQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<JsfPage<DeptDto>> page(DeptQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<String> save(DeptDto dept) {
        return getRpcService().save(dept);
    }

    @Override
    public JR<String> updateById(DeptDto dept) {
        return getRpcService().updateById(dept);
    }

    @Override
    public JR<String> deleteById(Integer id) {
        return getRpcService().deleteById(id);
    }
}
