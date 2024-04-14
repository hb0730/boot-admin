package com.hb0730.rpc.sys.system.service;

import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.QuartzJobDto;
import com.hb0730.rpc.sys.system.domain.query.QuartzJobQuery;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
public interface QuartzJobRpcService {
    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页
     */
    default JR<JsfPage<QuartzJobDto>> page(QuartzJobQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 查询
     *
     * @param id 主键
     * @return 数据
     */
    default JR<QuartzJobDto> getById(String id) {
        return JR.fail("暂未实现");
    }

    /**
     * 保存
     *
     * @param quartzJob 定时任务
     */
    default JR<String> save(QuartzJobDto quartzJob) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新
     *
     * @param quartzJob 定时任务
     */
    default JR<String> updateById(QuartzJobDto quartzJob) {
        return JR.fail("暂未实现");
    }

    /**
     * 删除
     *
     * @param ids id
     */
    default JR<String> deleteByIds(List<String> ids) {
        return JR.fail("暂未实现");
    }
}
