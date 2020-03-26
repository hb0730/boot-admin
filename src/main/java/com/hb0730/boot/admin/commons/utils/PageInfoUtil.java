package com.hb0730.boot.admin.commons.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.Collection;

/**
 * <p>
 * bean 转换时 PageHelper 失效
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class PageInfoUtil {
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
     */
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
     * <p>
     * 类型转换(替换pageInfo中list)
     * </p>
     *
     * @param pageInfo 原pageinfo
     * @param clazz 目标list
     * @param <P> 原类型
     * @param <D> 目标类型
     * @return 目标类型pageInfo
     */
    public static <P, D> PageInfo<D> replacePageInfoList(PageInfo<P> pageInfo, Collection<D> clazz) {
        Page<D> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());
        page.addAll(clazz);
        return new PageInfo<>(page);
    }
}
