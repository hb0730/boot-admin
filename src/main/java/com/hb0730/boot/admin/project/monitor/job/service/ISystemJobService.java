package com.hb0730.boot.admin.project.monitor.job.service;

import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * <p>
 * 定时任务  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
public interface ISystemJobService extends IService<SystemJobEntity> {

    /**
     * 根据id删除
     *
     * @param id id
     * @return 是否成功
     */
    boolean removeById(Long id);

    /**
     * <p>
     * 根据id删除
     * </p>
     *
     * @param ids id
     * @return 是否成功
     */
    boolean deleteByIds(Collection<Long> ids);
}
