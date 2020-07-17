package com.hb0730.boot.admin.project.monitor.logininfo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.project.monitor.logininfo.model.dto.LoginInfoDTO;
import com.hb0730.boot.admin.project.monitor.logininfo.model.entity.SystemLoginInfoEntity;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.LoginfoParams;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.SystemLoginfoVO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 系统登录日志  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-01
 */
public interface ISystemLoginInfoService extends IBaseService<Long, LoginfoParams, SystemLoginfoVO, SystemLoginInfoEntity> {

    /**
     * 分页查询
     *
     * @param params 过滤条件
     * @return 分页后信息
     * @since v2.0
     */
    @Override
    Page<SystemLoginfoVO> page(@NonNull LoginfoParams params);

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
