package com.hb0730.boot.admin.commons.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.constant.enums.SortTypeEnum;
import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.web.model.BaseParams;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * mybatis plus 条件过滤器
 *
 * @author bing_huang
 * @date 2020/06/26 12:17
 * @since V1.0
 */
public class QueryWrapperUtils {

    /**
     * 分页
     *
     * @param params 过滤参数
     * @param <T>    继承{@link BusinessDomain}
     * @param <M>    继承{@link BaseParams}
     * @return 过滤器
     * @see Page
     * @since v1.1
     */
    @NotNull
    public static <T extends BusinessDomain, M extends BaseParams> Page<T> getPage(@NotNull M params) {
        return new Page<>(params.getPageNum(), params.getPageSize());
    }

    /**
     * 动态拼接过滤参数
     *
     * @param params 过滤参数
     * @param <P>    参数类型
     * @param <T>    实体类型
     * @return {@link QueryWrapper}
     */
    @NotNull
    public static <P extends BaseParams, T extends BusinessDomain> QueryWrapper<T> getQuery(@NotNull P params) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        List<String> groupColumn = params.getGroupColumn();
        if (!CollectionUtils.isEmpty(groupColumn)) {
            String[] array = groupColumn.toArray(new String[0]);
            queryWrapper.groupBy(array);
        }
        if (!CollectionUtils.isEmpty(params.getSortColumn())) {
            String[] array = params.getSortColumn().toArray(new String[0]);
            queryWrapper.orderBy(true, SortTypeEnum.ASC.getValue().equals(params.getSortType()), array);
        }
        return queryWrapper;
    }
}
