package com.hb0730.boot.admin.domain.service.base;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import org.springframework.lang.NonNull;

/**
 * 基础service之保存
 *
 * @param <DTO> 显示层对象类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface ISuperSaveService<DTO extends BaseDTO> {
    /**
     * 保存
     *
     * @param vo 信息
     * @return 是否成功
     */
    boolean save(@NonNull DTO vo);
}
