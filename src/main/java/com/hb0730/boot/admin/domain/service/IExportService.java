package com.hb0730.boot.admin.domain.service;

import com.hb0730.boot.admin.domain.model.domain.ExcelDomain;

import java.util.Collection;

/**
 * <p>
 * 基础service
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IExportService<T extends ExcelDomain> {
    /**
     * <p>
     * 文件上傳
     * </p>
     *
     * @param list 导入信息
     * @return 是否成功
     */
    boolean upload(Collection<T> list);
}
