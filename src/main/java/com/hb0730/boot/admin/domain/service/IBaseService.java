package com.hb0730.boot.admin.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.model.vo.BaseVO;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * 基础Service
 *
 * @param <ID>     id类型
 * @param <PARAMS> 请求参数类型
 * @param <VO>     显示层对象类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface IBaseService<ID extends Serializable,
        PARAMS extends BaseParams,
        VO extends BaseVO,
        ENTITY extends BaseDomain> extends IService<ENTITY>, ISuperBaseService<ID, PARAMS, VO> {

    /**
     * 根据id修改
     *
     * @param vo 显示层对象类型
     * @return 是否成功
     */
    boolean updateById(@NonNull VO vo);

    /**
     * 构造QueryWrapper
     *
     * @param params 请求参数
     * @return QueryWrapper
     */
    QueryWrapper<ENTITY> query(@NonNull PARAMS params);

    /**
     * 获取实现类,用户获取实现内，内部依赖
     *
     * @param <T> 实现类类型
     * @return 实现类
     */
    <T> T getThis();
}
