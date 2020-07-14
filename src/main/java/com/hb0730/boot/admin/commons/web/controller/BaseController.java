package com.hb0730.boot.admin.commons.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.commons.web.model.BaseParams;
import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * </P>
 *
 * @param <V>    返回类型
 * @param <P>    请求参数
 * @param <TYPE> 类型
 * @param <E>    实体
 * @author bing_huang
 * @since V1.0
 */
@Validated
public abstract class BaseController<P extends BaseParams, V extends BusinessVO<E>, TYPE extends Serializable, E extends BusinessDomain> implements IBaseController<P, V, TYPE> {
    private Optional<IBaseService<P, V, E>> service;

    public BaseController(IBaseService<P, V, E> service) {
        this.service = Optional.ofNullable(service);
    }

    public BaseController() {
        service = Optional.empty();
    }

    @Override
    public Result<Page<V>> page(P params) {
        if (service.isPresent()) {
            Page<V> page = service.get().page(params);
            return ResponseResult.resultSuccess(page);
        }
        return null;
    }

    @Override
    public Result<List<V>> list(P params) {
        if (service.isPresent()) {

            List<V> list = service.get().list(params);
            return ResponseResult.resultSuccess(list);
        }
        return null;
    }

    @Override
    public Result<String> save(V vo) {
        if (service.isPresent()) {

            E e = vo.convertTo();
            service.get().save(e);
            return ResponseResult.resultSuccess("保存成功");
        }
        return null;
    }

    @Override
    public Result<String> updateById(TYPE id, V vo) {
        if (service.isPresent()) {

            E e = vo.convertTo();
            service.get().updateById(e);
            return ResponseResult.resultSuccess("修改成功");
        }
        return null;
    }

    @Override
    public Result<String> deleteById(TYPE id) {
        if (service.isPresent()) {

            service.get().removeById(id);
            return ResponseResult.resultSuccess("删除成功");
        }
        return null;
    }

    @Override
    public Result<String> deleteByIds(List<TYPE> ids) {
        if (service.isPresent()) {

            service.get().removeByIds(ids);
            return ResponseResult.resultSuccess("删除成功");
        }
        return null;
    }
}
