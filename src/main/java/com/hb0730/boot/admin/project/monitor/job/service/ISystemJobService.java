package com.hb0730.boot.admin.project.monitor.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.commons.domain.service.IExportService;
import com.hb0730.boot.admin.project.monitor.job.model.dto.JobExportDto;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;

import java.util.Collection;

/**
 * <p>
 * 定时任务  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
public interface ISystemJobService extends IService<SystemJobEntity>, IExportService<JobExportDto> {

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
