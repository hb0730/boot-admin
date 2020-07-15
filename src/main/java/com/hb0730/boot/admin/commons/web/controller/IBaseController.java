package com.hb0730.boot.admin.commons.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.web.model.BaseParams;
import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import com.hb0730.boot.admin.commons.web.response.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 基础controller
 *
 * @param <V>    返回类型
 * @param <P>    请求参数
 * @param <TYPE> 类型
 * @author bing_huang
 * @date 2020/07/14 7:16
 * @since V1.0
 */
@SuppressWarnings({"rawtypes"})
@Validated
public interface IBaseController<P extends BaseParams, V extends BusinessVO, TYPE> {

    /**
     * 分页查询
     *
     * @param params 过滤参数
     * @return 分页列表
     */
    @PostMapping("/list/page")
    Result<Page<V>> page(@RequestBody P params);

    /**
     * 列表查询
     *
     * @param params 过滤参数
     * @return 列表
     */
    @PostMapping("/list")
    Result<List<V>> list(@RequestBody P params);

    /**
     * 保存
     *
     * @param vo vo类型
     * @return 是否成功
     */
    @PostMapping("/save")
    Result<String> save(@RequestBody V vo);

    /**
     * 根据id修改
     *
     * @param id id
     * @param vo 修改参数
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    Result<String> updateById(@PathVariable("id") TYPE id, @RequestBody V vo);

    /**
     * 根据id删除
     *
     * @param id id
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    Result<String> deleteById(@PathVariable("id") TYPE id);

    /**
     * 根据id删除
     *
     * @param ids id集合
     * @return 是否成功
     */
    @PostMapping("/delete")
    Result<String> deleteByIds(@RequestBody List<TYPE> ids);
}
