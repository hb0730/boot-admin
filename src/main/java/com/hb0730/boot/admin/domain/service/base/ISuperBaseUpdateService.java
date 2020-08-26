package com.hb0730.boot.admin.domain.service.base;

import com.hb0730.boot.admin.domain.model.vo.BaseVO;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * 基础service之修改
 *
 * @param <VO> 显示层对象类型
 * @param <ID> id类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface ISuperBaseUpdateService<ID extends Serializable, VO extends BaseVO> {
    /**
     * 根据修改删除
     *
     * @param id id
     * @param vo 修改参数
     * @return 是否成功
     */
    boolean updateById(@NonNull ID id, @NonNull VO vo);
}
