package com.hb0730.boot.admin.project.monitor.operlog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.monitor.operlog.model.dto.OperLogDTO;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.OperLogParams;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.SystemOperLogVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 业务操作日志  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-02
 */
public interface ISystemOperLogService extends IBaseService<Long, OperLogParams, SystemOperLogVO, SystemOperLogEntity> {

    /**
     * 分页查询
     *
     * @param params 过滤条件
     * @return 分页后的信息
     * @since v2.0
     */
    @Override
    Page<SystemOperLogVO> page(@NotNull @NonNull OperLogParams params);

    /**
     * 导出
     *
     * @param params 过滤参数
     * @return 导出信息
     */
    List<OperLogDTO> export(OperLogParams params);
}
