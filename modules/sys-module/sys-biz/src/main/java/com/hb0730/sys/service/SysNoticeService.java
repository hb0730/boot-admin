package com.hb0730.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.common.api.JsfPage;
import com.hb0730.query.mybatis.plus.QueryHelper;
import com.hb0730.sys.domain.dto.NoticeDto;
import com.hb0730.sys.domain.entity.SysNotice;
import com.hb0730.sys.domain.query.NoticeQuery;
import com.hb0730.sys.mapper.SysNoticeMapper;
import com.hb0730.sys.service.mapstruct.SysNoticeMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysNoticeService extends ServiceImpl<SysNoticeMapper, SysNotice> {
    private final SysNoticeMapstruct mapstruct;

    /**
     * 统计不在ids中的数量
     *
     * @param ids ids
     * @return 数量
     */
    public Integer countNotInIds(List<String> ids) {
        LambdaQueryWrapper<SysNotice> eq = Wrappers.lambdaQuery(SysNotice.class)
                .notIn(SysNotice::getId, ids)
                .eq(SysNotice::getEnabled, true);
        long count = count(eq);
        return (int) count;
    }

    /**
     * 查询不在ids中的id
     *
     * @param ids ids
     * @return id
     */
    public List<String> getIdsNotInIds(List<String> ids) {
        LambdaQueryWrapper<SysNotice> eq = Wrappers.lambdaQuery(SysNotice.class)
                .notIn(SysNotice::getId, ids)
                .eq(SysNotice::getEnabled, true)
                .select(SysNotice::getId);
        List<SysNotice> list = list(eq);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        return CollectionUtil.listToList(list, SysNotice::getId);
    }

    /**
     * 统计公告数量
     *
     * @return 数量
     */
    public int countNotices() {
        LambdaQueryWrapper<SysNotice> eq = Wrappers.lambdaQuery(SysNotice.class)
                .eq(SysNotice::getEnabled, true);
        long count = count(eq);
        return (int) count;
    }

    /**
     * 查询所有id
     *
     * @return id
     */
    public List<String> getIds() {
        LambdaQueryWrapper<SysNotice> eq = Wrappers.lambdaQuery(SysNotice.class)
                .eq(SysNotice::getEnabled, true)
                .select(SysNotice::getId);
        List<SysNotice> list = list(eq);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        return CollectionUtil.listToList(list, SysNotice::getId);
    }


    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<NoticeDto> page(NoticeQuery query) {
        QueryWrapper<SysNotice> queryWrapper = QueryHelper.ofBean(query);
        IPage<SysNotice> page = QueryHelper.toPage(query);
        page = page(page, queryWrapper);
        List<NoticeDto> res = mapstruct.toDtoList(page.getRecords());
        // 获取产品对应的权限
        

        return QueryHelper.toJsfPage(page, res);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    public List<NoticeDto> list(NoticeQuery query) {
        QueryWrapper<SysNotice> queryWrapper = QueryHelper.ofBean(query);
        List<SysNotice> list = list(queryWrapper);
        return mapstruct.toDtoList(list);
    }

    /**
     * 保存
     *
     * @param dto dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(NoticeDto dto) {
        SysNotice entity = mapstruct.toEntity(dto);
        save(entity);
    }

    /**
     * 更新
     *
     * @param dto dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(NoticeDto dto) {
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            throw new ServiceException("id不能为空");
        }
        SysNotice sysNotice = baseMapper.selectById(id);
        BeanUtil.copyProperties(
                dto,
                sysNotice,
                CopyOptions.create().ignoreNullValue()
        );
        updateById(sysNotice);
    }

    /**
     * 通过id删除
     *
     * @param ids id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        removeByIds(ids);
    }

    /**
     * 通过id关闭
     *
     * @param ids id
     */
    @Transactional(rollbackFor = Exception.class)
    public void closeByIds(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        LambdaQueryWrapper<SysNotice> eq = Wrappers.lambdaQuery(SysNotice.class)
                .in(SysNotice::getId, ids)
                .eq(SysNotice::getEnabled, true);
        List<SysNotice> list = list(eq);
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        list.forEach(sysNotice -> sysNotice.setEnabled(false));
        updateBatchById(list);
    }
}
