package com.hb0730.boot.admin.project.monitor.operlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.constant.enums.ValueEnum;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.monitor.operlog.mapper.ISystemOperLogMapper;
import com.hb0730.boot.admin.project.monitor.operlog.model.dto.OperLogDTO;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.OperLogParams;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.SystemOperLogVO;
import com.hb0730.boot.admin.project.monitor.operlog.service.ISystemOperLogService;
import com.hb0730.boot.admin.project.system.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.project.system.user.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
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
public class SystemOperLogServiceImpl extends ServiceImpl<ISystemOperLogMapper, SystemOperLogEntity> implements ISystemOperLogService {


    @Override
    public PageInfo<SystemOperLogVO> list(Integer page, Integer pageSize, OperLogParams params) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(page, pageSize);
        QueryWrapper<SystemOperLogEntity> queryWrapper = getQuery(params);
        PageInfo<SystemOperLogEntity> pageInfo = new PageInfo<>(super.list(queryWrapper));
        return PageInfoUtil.toBean(pageInfo, SystemOperLogVO.class);
    }

    @Override
    public List<OperLogDTO> export(OperLogParams params) {
        QueryWrapper<SystemOperLogEntity> queryWrapper = getQuery(params);
        List<SystemOperLogEntity> entities = super.list(queryWrapper);
        List<OperLogDTO> list = BeanUtils.transformFromInBatch(entities, OperLogDTO.class);
        if (!CollectionUtils.isEmpty(list)) {
            return list.parallelStream().peek(dto -> dto.setBusinessTypeName(ValueEnum.valueToEnum(BusinessTypeEnum.class, dto.getBusinessType()).name())).collect(Collectors.toList());
        }
        return null;
    }

    private QueryWrapper<SystemOperLogEntity> getQuery(OperLogParams params) {
        QueryWrapper<SystemOperLogEntity> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(params)) {
            if (StringUtils.isNotBlank(params.getModule())) {
                queryWrapper.eq(SystemOperLogEntity.MODULE, params.getModule());
            }
            if (StringUtils.isNotBlank(params.getUsername())) {
                SystemUserEntity user = UserUtil.getUserByUsername(params.getUsername());
                queryWrapper.eq(SystemOperLogEntity.CREATE_USER_ID, user.getId());
            }
            if (Objects.nonNull(params.getBusinessType())) {
                queryWrapper.eq(SystemOperLogEntity.BUSINESS_TYPE, params.getBusinessType());
            }
            if (Objects.nonNull(params.getStatus())) {
                queryWrapper.eq(SystemOperLogEntity.STATUS, params.getStatus());
            }
            if (Objects.nonNull(params.getStartTime())) {
                queryWrapper.apply("date_format(create_time,'%y%m%d') >= date_format({0},'%y%m%d')", params.getStartTime());
            }
            if (Objects.nonNull(params.getEndTime())) {
                queryWrapper.apply("date_format(create_time,'%y%m%d') <= date_format({0},'%y%m%d')", params.getEndTime());
            }
        }
        return queryWrapper;
    }
}
