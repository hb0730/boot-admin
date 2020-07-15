package com.hb0730.boot.admin.commons.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.model.web.BaseParams;
import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

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

public abstract class BaseController<P extends BaseParams, V extends BusinessVO, TYPE extends Serializable, E extends BusinessDomain> implements IBaseController<P, V, TYPE> {
    private final IBaseService<P, V, E> service;

    public BaseController(IBaseService<P, V, E> service) {
        this.service = service;
    }

    public BaseController() {
        service = null;
    }

    @Override
    public Result<Page<V>> page(@RequestBody P params) {
        if (null != service) {
            Page<V> page = service.page(params);
            return ResponseResult.resultSuccess(page);
        }
        return null;
    }

    @Override
    public Result<List<V>> list(@RequestBody P params) {
        if (null != service) {

            List<V> list = service.list(params);
            return ResponseResult.resultSuccess(list);
        }
        return null;
    }

    @Override
    public Result<String> save(@RequestBody V vo) {
        if (null != service) {
//            E e = vo.convertTo();
//            service.save(e);
            return ResponseResult.resultSuccess("保存成功");
        }
        return null;
    }

    @Override
    @PostMapping("/update/{id}")
    public Result<String> updateById(@PathVariable("id") TYPE id, @RequestBody V vo) {
        if (null != service) {

//            E e = vo.convertTo();
//            service.updateById(e);
            return ResponseResult.resultSuccess("修改成功");
        }
        return null;
    }

    @Override
    public Result<String> deleteById(@PathVariable TYPE id) {
        if (null != service) {

            service.removeById(id);
            return ResponseResult.resultSuccess("删除成功");
        }
        return null;
    }

    @Override
    public Result<String> deleteByIds(@RequestBody List<TYPE> ids) {
        if (null != service) {
            service.removeByIds(ids);
            return ResponseResult.resultSuccess("删除成功");
        }
        return null;
    }
}
