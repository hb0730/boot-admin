package com.hb0730.boot.admin.domain.service.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.model.vo.BaseVO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 基础service之查询
 *
 * @param <VO>     显示层对象类型
 * @param <PARAMS> 请求参数类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface ISuperQueryBaseService<PARAMS extends BaseParams, VO extends BaseVO> {
    /**
     * 分页
     *
     * @param params 过滤参数
     * @return 分页列表
     */
    Page<VO> page(@NonNull PARAMS params);

    /**
     * 列表查询
     *
     * @param params 过滤参数
     * @return 列表
     */
    List<VO> list(@NonNull PARAMS params);
}
