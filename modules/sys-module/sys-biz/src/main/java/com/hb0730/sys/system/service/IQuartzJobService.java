package com.hb0730.sys.system.service;

import com.hb0730.rpc.sys.system.domain.query.QuartzJobQuery;
import com.hb0730.sys.system.domain.SysQuartzJob;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
public interface IQuartzJobService {

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页
     */
    Page<SysQuartzJob> page(QuartzJobQuery query);

    /**
     * 根据id查询
     *
     * @param id id
     * @return 定时任务
     */
    SysQuartzJob getById(String id);

    /**
     * 保存
     *
     * @param quartzJob 定时任务
     */
    void save(SysQuartzJob quartzJob);

    /**
     * 更新
     *
     * @param quartzJob 定时任务
     */
    void updateById(SysQuartzJob quartzJob);


    /**
     * 删除
     *
     * @param ids id
     */
    void deleteByIds(List<String> ids);
}
