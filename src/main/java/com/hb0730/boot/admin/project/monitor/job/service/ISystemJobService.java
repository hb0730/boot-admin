package com.hb0730.boot.admin.project.monitor.job.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.commons.domain.service.IExportService;
import com.hb0730.boot.admin.project.monitor.job.model.dto.JobExportDto;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.hb0730.boot.admin.project.monitor.job.model.vo.JobParams;
import com.hb0730.boot.admin.project.monitor.job.model.vo.SystemJobVO;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 定时任务  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
public interface ISystemJobService extends IService<SystemJobEntity>, IExportService<JobExportDto>, IBaseService<JobParams, SystemJobEntity> {

    /**
     * 分页查询
     *
     * @param params 过滤参数
     * @return 分页列表
     */
    Page<SystemJobVO> page(@NonNull JobParams params);

    /**
     * 列表查询
     *
     * @param params 过滤参数
     * @return 列表
     */
    List<SystemJobVO> list(@NonNull JobParams params);

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

    /**
     * 立即执行
     *
     * @param id id
     */
    void executor(Long id);

    /**
     * 导出
     *
     * @param params 导出参数
     * @return 导出列表
     */
    List<JobExportDto> export(@NonNull JobParams params);
}
