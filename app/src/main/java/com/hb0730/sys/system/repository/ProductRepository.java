package com.hb0730.sys.system.repository;

import com.hb0730.sys.system.domain.SysProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 管理端： 产品 repository
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Repository
public interface ProductRepository extends JpaRepository<SysProduct, Long>, JpaSpecificationExecutor<SysProduct> {

    /**
     * 根据code查询
     *
     * @param code code
     * @return .
     */
    boolean existsByCode(String code);

    /**
     * 根据code和id查询
     *
     * @param code code
     * @param id   id
     * @return .
     */
    boolean existsByCodeAndIdNot(String code, Long id);
}
