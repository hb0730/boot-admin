package com.hb0730.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hb0730.common.api.JsfPage;
import com.hb0730.mybatis.core.service.BaseService;
import com.hb0730.query.mybatis.plus.QueryHelper;
import com.hb0730.sys.domain.dto.QuartzJobDto;
import com.hb0730.sys.domain.entity.SysQuartzJob;
import com.hb0730.sys.domain.query.QuartzJobQuery;
import com.hb0730.sys.mapper.SysQuartzJobMapper;
import com.hb0730.sys.service.mapstruct.SysQuartzJobMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysQuartzJobService extends BaseService<SysQuartzJobMapper, SysQuartzJob> {
    private final SysQuartzJobMapstruct mapstruct;

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<QuartzJobDto> page(QuartzJobQuery query) {
        QueryWrapper<SysQuartzJob> queryWrapper = QueryHelper.ofBean(query);
        IPage<SysQuartzJob> page = QueryHelper.toPage(query);
        page = baseMapper.selectPage(page, queryWrapper);
        List<QuartzJobDto> res = mapstruct.toDtoList(page.getRecords());
        return QueryHelper.toJsfPage(page, res);
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 数据
     */
    public QuartzJobDto getById(String id) {
        SysQuartzJob sysQuartzJob = baseMapper.selectById(id);
        return mapstruct.toDto(sysQuartzJob);
    }

    /**
     * 保存
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(QuartzJobDto dto) {
        SysQuartzJob sysQuartzJob = mapstruct.toEntity(dto);
        baseMapper.insert(sysQuartzJob);
    }

    /**
     * 更新
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(QuartzJobDto dto) {
        SysQuartzJob entity = baseMapper.selectById(dto.getId());
        SysQuartzJob sysQuartzJob = mapstruct.toEntity(dto);
        BeanUtil.copyProperties(sysQuartzJob, entity, CopyOptions.create().setIgnoreNullValue(true));
        baseMapper.updateById(entity);
    }

    /**
     * 删除
     *
     * @param ids id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<String> ids) {
        baseMapper.deleteBatchIds(ids);
    }
}
