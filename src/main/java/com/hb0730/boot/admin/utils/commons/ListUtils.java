package com.hb0730.boot.admin.utils.commons;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class ListUtils {


    /**
     * <p>
     *  差集
     * </p>
     *
     * @param aList 目标集
     * @param bList 源集
     * @param <T> 类型
     * @return 去重(保证源集不变) new List
     */
    public static  <T> List<T> removeAll(List<T> aList, List<T> bList) {
        List<T> newList = Lists.newArrayList();
        newList.addAll(aList);
        newList.removeAll(bList);
        return newList;
    }
}
