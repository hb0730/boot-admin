package com.hb0730.boot.admin.domain.service.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.model.web.BaseParams;
import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author bing_huang
 * @date 2020/07/17 8:06
 * @since V1.0
 */
public interface IQueryBaseService<P extends BaseParams, V extends BusinessVO> {
    /**
     * 分页
     *
     * @param params 请求参数
     * @return 分页列表
     */
    Page<V> page(@NonNull P params);

    /**
     * 列表
     *
     * @param params 请求参数
     * @return 列表
     */
    List<V> list(@NonNull P params);
}
