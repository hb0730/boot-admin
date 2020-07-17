package com.hb0730.boot.admin.commons.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.domain.model.web.BaseParams;
import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.domain.result.ResponseResult;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.service.IBaseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * </P>
 *
 * @param <V>  返回类型
 * @param <P>  请求参数
 * @param <ID> 类型
 * @param <E>  实体
 * @author bing_huang
 * @see com.hb0730.boot.admin.domain.controller.AbstractBaseController
 * @since V1.0
 */
@Deprecated
public abstract class BaseController<P extends BaseParams, V extends BusinessVO, ID extends Serializable, E extends BusinessDomain> implements IBaseController<P, V, ID> {
    private final IBaseService<ID, P, V, E> service;

    public BaseController(IBaseService<ID, P, V, E> service) {
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
    public Result<String> updateById(@PathVariable("id") ID id, @RequestBody V vo) {
        if (null != service) {

//            E e = vo.convertTo();
//            service.updateById(e);
            return ResponseResult.resultSuccess("修改成功");
        }
        return null;
    }

    @Override
    public Result<String> deleteById(@PathVariable ID id) {
        if (null != service) {

            service.removeById(id);
            return ResponseResult.resultSuccess("删除成功");
        }
        return null;
    }

    @Override
    public Result<String> deleteByIds(@RequestBody List<ID> ids) {
        if (null != service) {
            service.removeByIds(ids);
            return ResponseResult.resultSuccess("删除成功");
        }
        return null;
    }
}
