package com.hb0730.sys.repository;

import com.hb0730.biz.entity.system.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 部门
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer>, JpaSpecificationExecutor<Dept> {

    /**
     * code是否存在
     *
     * @param code code
     * @return .
     */
    boolean existsByCode(String code);

    /**
     * code是否存在
     *
     * @param code code
     * @param id   id 需要排除的id
     * @return .
     */
    boolean existsByCodeAndIdNot(String code, Integer id);
}
