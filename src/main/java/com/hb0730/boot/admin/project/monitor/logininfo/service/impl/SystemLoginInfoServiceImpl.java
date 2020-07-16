package com.hb0730.boot.admin.project.monitor.logininfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.domain.service.SuperBaseServiceImpl;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.monitor.logininfo.mapper.ISystemLoginInfoMapper;
import com.hb0730.boot.admin.project.monitor.logininfo.model.dto.LoginInfoDTO;
import com.hb0730.boot.admin.project.monitor.logininfo.model.entity.SystemLoginInfoEntity;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.LoginfoParams;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.SystemLoginfoVO;
import com.hb0730.boot.admin.project.monitor.logininfo.service.ISystemLoginInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.hb0730.boot.admin.commons.utils.QueryWrapperUtils.getQuery;

/**
 * <p>
 * 系统登录日志  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-01
 */
@Service
public class SystemLoginInfoServiceImpl extends SuperBaseServiceImpl<LoginfoParams, SystemLoginfoVO, ISystemLoginInfoMapper, SystemLoginInfoEntity> implements ISystemLoginInfoService {


    @Override
    public Page<SystemLoginfoVO> page(@NonNull LoginfoParams params) {
        QueryWrapper<SystemLoginInfoEntity> query = query(params);
        Page<SystemLoginInfoEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, SystemLoginfoVO.class);
    }

    @Override
    @Deprecated
    public List<SystemLoginfoVO> list(@NonNull LoginfoParams params) {
        return null;
    }


    @Override
    public List<LoginInfoDTO> export(LoginfoParams params) {
        QueryWrapper<SystemLoginInfoEntity> queryWrapper = query(params);
        List<SystemLoginInfoEntity> entities = super.list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, LoginInfoDTO.class);
    }

    @Override
    @NonNull
    public QueryWrapper<SystemLoginInfoEntity> query(@NonNull LoginfoParams params) {
        QueryWrapper<SystemLoginInfoEntity> query = getQuery(params);
        if (StringUtils.isNotBlank(params.getUsername())) {
            query.eq(SystemLoginInfoEntity.USERNAME, params.getUsername());
        }
        if (StringUtils.isNotBlank(params.getIpaddr())) {
            query.eq(SystemLoginInfoEntity.IPADDR, params.getIpaddr());
        }
        if (Objects.nonNull(params.getStatus())) {
            query.eq(SystemLoginInfoEntity.STATUS, params.getStatus());
        }
        if (Objects.nonNull(params.getStartTime())) {
            query.apply("date_format(create_time,'%y%m%d') >= date_format({0},'%y%m%d')", params.getStartTime());
        }
        if (Objects.nonNull(params.getEndTime())) {
            query.apply("date_format(create_time,'%y%m%d') <= date_format({0},'%y%m%d')", params.getEndTime());
        }
        return query;
    }
}
