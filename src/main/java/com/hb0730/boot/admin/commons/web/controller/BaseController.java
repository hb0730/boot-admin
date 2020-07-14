package com.hb0730.boot.admin.commons.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.web.model.BaseParams;
import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import com.hb0730.boot.admin.commons.web.response.Result;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @param <V>    返回类型
 * @param <P>    请求参数
 * @param <TYPE> 类型
 * @author bing_huang
 * @since V1.0
 */
@Validated
public abstract class BaseController<P extends BaseParams, V extends BusinessVO, TYPE> implements IBaseController<P, V, TYPE> {

    @Override
    public Result<Page<V>> page(P params) {
        return null;
    }

    @Override
    public Result<List<V>> list(P params) {
        return null;
    }

    @Override
    public Result<String> save(V vo) {
        return null;
    }

    @Override
    public Result<String> updateById(TYPE id, V vo) {
        return null;
    }

    @Override
    public Result<String> deleteById(TYPE id) {
        return null;
    }

    @Override
    public Result<String> deleteByIds(List<TYPE> ids) {
        return null;
    }
}
