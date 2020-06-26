package com.hb0730.boot.admin.project.monitor.logininfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.monitor.logininfo.mapper.ISystemLoginInfoMapper;
import com.hb0730.boot.admin.project.monitor.logininfo.model.dto.LoginInfoDTO;
import com.hb0730.boot.admin.project.monitor.logininfo.model.entity.SystemLoginInfoEntity;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.LoginfoParams;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.SystemLoginfoVO;
import com.hb0730.boot.admin.project.monitor.logininfo.service.ISystemLoginInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统登录日志  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-01
 */
@Service
public class SystemLoginInfoServiceImpl extends ServiceImpl<ISystemLoginInfoMapper, SystemLoginInfoEntity> implements ISystemLoginInfoService {

    @Override
    public PageInfo<SystemLoginfoVO> list(Integer page, Integer pageSize, LoginfoParams params) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        QueryWrapper<SystemLoginInfoEntity> queryWrapper = getQuery(params);
        PageHelper.startPage(page, pageSize);
        PageInfo<SystemLoginInfoEntity> pageInfo = new PageInfo<>(super.list(queryWrapper));
        return PageUtils.toBean(pageInfo, SystemLoginfoVO.class);
    }

    @Override
    public List<LoginInfoDTO> export(LoginfoParams params) {
        QueryWrapper<SystemLoginInfoEntity> queryWrapper = getQuery(params);
        List<SystemLoginInfoEntity> entities = super.list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, LoginInfoDTO.class);
    }

    /**
     * <p>
     * 获取查询条件
     * </p>
     *
     * @param params 过滤条件
     * @return 查询条件
     */
    private QueryWrapper<SystemLoginInfoEntity> getQuery(LoginfoParams params) {
        QueryWrapper<SystemLoginInfoEntity> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(params)) {
            if (StringUtils.isNotBlank(params.getUsername())) {
                queryWrapper.eq(SystemLoginInfoEntity.USERNAME, params.getUsername());
            }
            if (StringUtils.isNotBlank(params.getIpaddr())) {
                queryWrapper.eq(SystemLoginInfoEntity.IPADDR, params.getIpaddr());
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
