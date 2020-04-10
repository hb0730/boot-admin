package com.hb0730.boot.admin.project.monitor.operlog.service;

import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.project.monitor.operlog.model.dto.OperLogDTO;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.OperLogParams;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.SystemOperLogVO;

import java.util.List;

/**
 * <p>
 * 业务操作日志  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-02
 */
public interface ISystemOperLogService extends IService<SystemOperLogEntity> {
    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param page 页数
     * @param pageSize 数量
     * @param params 过滤条件
     * @return 分页后的信息
     */
    PageInfo<SystemOperLogVO> list(Integer page, Integer pageSize, OperLogParams params);

    /**
     * 导出
     * @param params 过滤参数
     * @return 导出信息
     */
    List<OperLogDTO> export(OperLogParams params);
}
