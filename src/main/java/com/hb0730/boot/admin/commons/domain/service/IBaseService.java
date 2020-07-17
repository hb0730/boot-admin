package com.hb0730.boot.admin.commons.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.model.web.BaseParams;
import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import org.springframework.lang.NonNull;

import java.io.Serializable;

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
public interface IBaseService<ID extends Serializable, P extends BaseParams,
        V extends BusinessVO,
        E extends BusinessDomain> extends IService<E>, ISuperBaseService<ID, P, V> {
    /**
     * 修改
     *
     * @param vo vo
     * @return 是否成功
     */
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
