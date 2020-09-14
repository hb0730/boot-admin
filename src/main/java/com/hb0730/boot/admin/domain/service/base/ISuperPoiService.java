package com.hb0730.boot.admin.domain.service.base;

import com.hb0730.boot.admin.domain.model.excel.ExcelDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * excel 操作
 *
 * @param <PARAMS> 过滤参数类型
 * @param <EXCEL>  excel实体类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface ISuperPoiService<PARAMS extends BaseParams, EXCEL extends ExcelDomain> {

    /**
     * 导出
     *
     * @param params 过滤参数
     * @return 导出实体
     */
    List<EXCEL> export(@Nonnull PARAMS params);

    /**
     * 导入
     *
     * @param dataList 导入信息
     * @return 是否成功
     */
    boolean upload(Collection<EXCEL> dataList);
}
