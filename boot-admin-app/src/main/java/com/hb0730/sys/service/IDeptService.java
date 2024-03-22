package com.hb0730.sys.service;

import com.hb0730.biz.entity.system.Dept;
import com.hb0730.sys.bean.DeptQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
public interface IDeptService {

    /**
     * code是否存在
     *
     * @param code code
     * @param id   id 需要排除的id
     * @return .
     */
    Boolean existsByCodeAndIdNot(String code, Integer id);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 部门
     */
    List<Dept> list(DeptQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 部门
     */
    Page<Dept> page(DeptQuery query);

    /**
     * 保存
     *
     * @param dept 部门
     */
    void save(Dept dept);

    /**
     * 更新
     *
     * @param dept 部门
     */
    void update(Dept dept);

    /**
     * 删除
     *
     * @param id id
     */
    void delete(Integer id);

}
