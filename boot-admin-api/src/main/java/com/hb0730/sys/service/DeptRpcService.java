package com.hb0730.sys.service;

import com.hb0730.biz.dto.sys.system.DeptDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.sys.bean.DeptQuery;
import jakarta.annotation.Nullable;

import java.util.List;

/**
 * 部门 rpc
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
public interface DeptRpcService {
    /**
     * code是否存在
     *
     * @param code code
     * @param id   id 需要排除的id
     * @return .
     */
    default JR<Boolean> existsByCodeAndIdNot(String code, @Nullable Integer id) {
        return null;
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 部门
     */
    default JR<List<DeptDto>> list(DeptQuery query) {
        return null;
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 部门
     */
    default JR<JsfPage<DeptDto>> page(DeptQuery query) {
        return null;
    }

    /**
     * 保存
     *
     * @param dept 部门
     */
    default JR<String> save(DeptDto dept) {
        return null;
    }

    /**
     * 更新
     *
     * @param dept 部门
     */
    default JR<String> updateById(DeptDto dept) {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default JR<String> deleteById(Integer id) {
        return null;
    }
}
