package com.hb0730.boot.admin.commons.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * bean 转换时 PageHelper 失效
 * </P>
 *
 * @author bing_huang
 * @since V2.0
 */
public class PageUtils {
    /**
     * <p>
     * pageInfo to bean
     * </p>
     *
     * @param pageInfo pageinfo
     * @param clazz    转换类型
     * @param <P>      原类型
     * @param <D>      需转类型
     * @return 转后类型<D>
     * @see #toBean(com.baomidou.mybatisplus.extension.plugins.pagination.Page, Class)
     */
    @Deprecated(since = "v1.0")
    public static <P, D> PageInfo<D> toBean(PageInfo<P> pageInfo, Class<D> clazz) {
        Page<D> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());
        for (P p : pageInfo.getList()) {
            D d = BeanUtils.transformFrom(p, clazz);
            page.add(d);
        }
        return new PageInfo<>(page);
    }

    /**
     * page 类型转换
     *
     * @param page  page
     * @param clazz 转换目标
     * @param <P>   源类型
     * @param <E>   目标类型
     * @return 转换后的page
     * @since v2.0
     */
    public static <P, E> com.baomidou.mybatisplus.extension.plugins.pagination.Page<E> toBean(com.baomidou.mybatisplus.extension.plugins.pagination.Page<P> page, Class<E> clazz) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<E> p1 = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        List<P> records = page.getRecords();
        List<E> list = BeanUtils.transformFromInBatch(records, clazz);
        p1.setRecords(list);
        p1.setTotal(page.getTotal());
        p1.setSize(page.getSize());
        p1.setCurrent(page.getCurrent());
        p1.setOrders(page.getOrders());
        return p1;
    }


    /**
     * 替换records
     *
     * @param page page
     * @param list 目标集合
     * @param <P>  源类型
     * @param <E>  目标类型
     * @return 目标类型的page
     */
    public static <P, E> com.baomidou.mybatisplus.extension.plugins.pagination.Page<E> replacePageList(com.baomidou.mybatisplus.extension.plugins.pagination.Page<P> page, List<E> list) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<E> p1 = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        p1.setRecords(list);
        p1.setTotal(page.getTotal());
        p1.setSize(page.getSize());
        p1.setCurrent(page.getCurrent());
        p1.setOrders(page.getOrders());
        return p1;
    }

    /**
     * <p>
     * 类型转换(替换pageInfo中list)
     * </p>
     *
     * @param pageInfo 原pageinfo
     * @param clazz    目标list
     * @param <P>      原类型
     * @param <D>      目标类型
     * @return 目标类型pageInfo
     */
    @Deprecated(since = "v1.0")
    public static <P, D> PageInfo<D> replacePageInfoList(PageInfo<P> pageInfo, Collection<D> clazz) {
        Page<D> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());
        page.addAll(clazz);
        return new PageInfo<>(page);
    }
}
