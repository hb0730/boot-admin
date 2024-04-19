package com.hb0730.sys.tenant.repository;

import com.hb0730.sys.tenant.domain.TenantOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Repository
public interface TenantOrgRepository extends JpaRepository<TenantOrg, String>,
        JpaSpecificationExecutor<TenantOrg> {

    /**
     * 根据产品ID是否存在
     *
     * @param productId .
     * @return .
     */
    boolean existsByProductId(Long productId);

    /**
     * 厂商识别码是否存在
     *
     * @param sysCode .
     * @return .
     */
    boolean existsBySysCodeAndSystemIsTrue(String sysCode);

    /**
     * 厂商识别码是否存在
     *
     * @param sysCode .
     * @param id      需要排除的ID
     * @return .
     */
    boolean existsBySysCodeAndIdNotAndSystemIsTrue(String sysCode, String id);

    /**
     * 根据产品ID查询
     *
     * @param productId .
     * @return .
     */
    List<TenantOrg> findByProductId(Long productId);

}
