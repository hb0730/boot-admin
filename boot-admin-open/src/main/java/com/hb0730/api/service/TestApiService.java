package com.hb0730.api.service;

import com.hb0730.base.api.Pair;
import com.hb0730.base.api.TaskStatus;
import com.hb0730.base.api.service.ApiService;
import com.hb0730.base.api.service.BaseApiService;

import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@ApiService(name = "test")
public class TestApiService extends BaseApiService {
    @Override
    protected TaskStatus doExecute(Map<String, Object> params) {
        return TaskStatus.SUCCESS().setMessage("测试成功！");
    }

    @Override
    protected Map<String, Pair<String, ParamCheck[]>> getParamRule() {
        return null;
    }

    @Override
    protected Map<String, String[]> getSpecialRule() {
        return null;
    }
}
