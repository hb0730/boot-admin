package com.hb0730.boot.admin.domain.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.domain.model.web.BaseParams;
import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.domain.result.ResponseResult;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.service.IBaseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * 查询
 *
 * @param <V>      vo类型
 * @param <P>      参数类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @date 2020/07/15 9:33
 * @since V1.0
 */
public interface IQueryBaseController<ID extends Serializable, V extends BusinessVO, P extends BaseParams, ENTITY extends BusinessDomain> extends IBaseController<ENTITY> {

    /**
     * 分页查询
     *
     * @param params 请求参数
     * @return 分页列表
     */
    @PostMapping("/list/page")
    @SuppressWarnings({"unchecked"})
    default Result<Page<V>> page(@Validated @RequestBody P params) {
        IBaseService<ID, P, V, ENTITY> service = getBaseService();
        if (null != service) {

            Page<V> page = service.page(params);
            return ResponseResult.resultSuccess(page);
        }
        return ResponseResult.resultSuccess(new Page<>());
    }

    /**
     * 列表查询
     *
     * @param params 请求参数
     * @return 列表
     */
    @PostMapping("/list")
    @SuppressWarnings({"unchecked"})
    default Result<List<V>> list(@Validated @RequestBody P params) {
        IBaseService<ID, P, V, ENTITY> service = getBaseService();
        if (null != service) {
            List<V> list = service.list(params);
            return ResponseResult.resultSuccess(list);
        }
        return ResponseResult.resultSuccess(Lists.newArrayList());
    }
}
