package com.hb0730.boot.admin.domain.service.base;

import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import org.springframework.lang.NonNull;

/**
 * @author bing_huang
 * @date 2020/07/17 8:09
 * @since V1.0
 */
public interface ISaveBaseService<V extends BusinessVO> {
    /**
     * 保存
     *
     * @param vo vo
     * @return 是否成功
     */
    boolean save(@NonNull V vo);
}
