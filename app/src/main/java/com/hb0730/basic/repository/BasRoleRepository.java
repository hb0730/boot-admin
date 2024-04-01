package com.hb0730.basic.repository;

import com.hb0730.basic.domain.BasRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface BasRoleRepository extends JpaRepository<BasRole, String>, JpaSpecificationExecutor<BasRole> {

    /**
     * 根据用户id查询角色
     *
     * @param userId .
     * @return .
     */
    @Query("SELECT p FROM BasRole p, BasUserRole up WHERE p.id = up.roleId AND up.userId = ?1")
    List<BasRole> findByUserId(String userId);

    /**
     * code是否存在
     *
     * @param code    角色标识
     * @param sysCode 商户识别码
     * @param id      需要排除的id
     * @return .
     */
    @Query("SELECT COUNT(p) > 0 FROM BasRole p WHERE p.code = ?1 AND p.sysCode = ?2 AND p.id <> ?3")
    Boolean existsByCode(String code, String sysCode, String id);

    /**
     * code是否存在
     *
     * @param code    角色标识
     * @param sysCode 商户识别码
     * @return .
     */
    @Query("SELECT COUNT(p) > 0 FROM BasRole p WHERE p.code = ?1 AND p.sysCode = ?2")
    Boolean existsByCode(String code, String sysCode);


}
