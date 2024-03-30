package com.hb0730.basic.repository;

import com.hb0730.basic.domain.BasOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Repository
public interface BasOrgRepository extends JpaRepository<BasOrg, String>, JpaSpecificationExecutor<BasOrg> {
}
