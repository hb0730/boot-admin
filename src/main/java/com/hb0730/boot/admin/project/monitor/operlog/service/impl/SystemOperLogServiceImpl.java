package com.hb0730.boot.admin.project.monitor.operlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.constant.enums.ValueEnum;
import com.hb0730.boot.admin.commons.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.utils.PageUtils;
import com.hb0730.boot.admin.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.monitor.operlog.mapper.ISystemOperLogMapper;
import com.hb0730.boot.admin.project.monitor.operlog.model.dto.OperLogDTO;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.OperLogParams;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.SystemOperLogVO;
import com.hb0730.boot.admin.project.monitor.operlog.service.ISystemOperLogService;
import com.hb0730.boot.admin.project.system.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.project.system.user.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务操作日志  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-02
 */
@Service
public class SystemOperLogServiceImpl extends SuperBaseServiceImpl<Long, OperLogParams, SystemOperLogVO, ISystemOperLogMapper, SystemOperLogEntity> implements ISystemOperLogService {

    @Override
    public Page<SystemOperLogVO> page(@NotNull @NonNull OperLogParams params) {
        QueryWrapper<SystemOperLogEntity> query = query(params);
        Page<SystemOperLogEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, SystemOperLogVO.class);
    }

    @Override
    @Deprecated
    public List<SystemOperLogVO> list(@NonNull OperLogParams params) {
        return null;
    }

    @Override
    public List<OperLogDTO> export(OperLogParams params) {
        QueryWrapper<SystemOperLogEntity> queryWrapper = query(params);
        List<SystemOperLogEntity> entities = super.list(queryWrapper);
        List<OperLogDTO> list = BeanUtils.transformFromInBatch(entities, OperLogDTO.class);
        if (!CollectionUtils.isEmpty(list)) {
            return list.parallelStream().peek(dto -> dto.setBusinessTypeName(ValueEnum.valueToEnum(BusinessTypeEnum.class, dto.getBusinessType()).name())).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    @NonNull
    public QueryWrapper<SystemOperLogEntity> query(@NonNull OperLogParams params) {
        QueryWrapper<SystemOperLogEntity> query = QueryWrapperUtils.getQuery(params);
        if (StringUtils.isNotBlank(params.getModule())) {
            query.eq(SystemOperLogEntity.MODULE, params.getModule());
        }
        if (StringUtils.isNotBlank(params.getUsername())) {
            SystemUserEntity user = UserUtil.getUserByUsername(params.getUsername());
            query.eq(SystemOperLogEntity.CREATE_USER_ID, user.getId());
        }
        if (Objects.nonNull(params.getBusinessType())) {
            query.eq(SystemOperLogEntity.BUSINESS_TYPE, params.getBusinessType());
        }
        if (Objects.nonNull(params.getStatus())) {
            query.eq(SystemOperLogEntity.STATUS, params.getStatus());
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
