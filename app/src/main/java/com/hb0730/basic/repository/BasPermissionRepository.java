package com.hb0730.basic.repository;

import com.hb0730.basic.domain.BasPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Repository
public interface BasPermissionRepository extends JpaRepository<BasPermission, Long>, JpaSpecificationExecutor<BasPermission> {

    /**
     * 根据角色获取权限
     *
     * @param roleIds .
     * @return .
     */
    @Query("SELECT p FROM BasPermission p JOIN p.roles r WHERE r.id IN ?1 ORDER BY p.rank")
    List<BasPermission> findByRoleIds(List<String> roleIds);
}
