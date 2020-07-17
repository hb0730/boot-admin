package com.hb0730.boot.admin.commons.domain.service.base;

import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * @author bing_huang
 * @date 2020/07/17 7:37
 * @since V1.0
 */
public interface ISuperBaseUpdateService<ID extends Serializable, V extends BusinessVO> {
    /**
     * 根据修改删除
     *
     * @param id id
     * @param vo 修改参数
     * @return 是否成功
     */
    boolean updateById(@NonNull ID id, @NonNull V vo);
}
