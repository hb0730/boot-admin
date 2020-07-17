package com.hb0730.boot.admin.utils;

import com.hb0730.boot.admin.utils.bean.BeanUtils;

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
}
