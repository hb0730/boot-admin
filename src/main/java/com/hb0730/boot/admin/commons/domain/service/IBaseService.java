package com.hb0730.boot.admin.commons.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.model.web.BaseParams;
import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service 基类
 *
 * @param <P> 请求参数
 * @param <V> 返回类型
 * @param <E> 实体类型
 * @author bing_huang
 * @date 2020/06/26 12:10
 * @since V1.0
 */
public interface IBaseService<P extends BaseParams, V extends BusinessVO, E extends BusinessDomain> extends IService<E> {
    /**
     * 分页
     *
     * @param params 请求参数
     * @return 分页列表
     */
    Page<V> page(@NonNull P params);

    /**
     * 列表
     *
     * @param params 请求参数
     * @return 列表
     */
    List<V> list(@NonNull P params);

    /**
     * 保存
     *
     * @param vo vo
     * @return 是否成功
     */
    @Transactional
    boolean save(@NonNull V vo);

    /**
     * 修改
     *
     * @param vo vo
     * @return 是否成功
     */
    @Transactional
    boolean updateById(@NonNull V vo);

    /**
     * 构造QueryWrapper
     *
     * @param params 请求参数
     * @return QueryWrapper
     */
    @NonNull
    QueryWrapper<E> query(@NonNull P params);
}
