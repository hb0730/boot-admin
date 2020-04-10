package com.hb0730.boot.admin.project.monitor.logininfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.project.monitor.logininfo.model.dto.LoginInfoDTO;
import com.hb0730.boot.admin.project.monitor.logininfo.model.entity.SystemLoginInfoEntity;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.LoginfoParams;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.SystemLoginfoVO;

import java.util.List;

/**
 * <p>
 * 系统登录日志  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-01
 */
public interface ISystemLoginInfoService extends IService<SystemLoginInfoEntity> {

    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param page     页数
     * @param pageSize 数量
     * @param params   过滤条件
     * @return 分页后信息
     */
    PageInfo<SystemLoginfoVO> list(Integer page, Integer pageSize, LoginfoParams params);

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param params 过滤条件
     * @return 导出信息
     */
    List<LoginInfoDTO> export(LoginfoParams params);
}
