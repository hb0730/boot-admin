package com.hb0730.boot.admin.project.monitor.operlog.service;

import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.OperLogParams;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.SystemOperLogVO;

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
}
