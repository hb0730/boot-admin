package com.hb0730.basic.repository;

import com.hb0730.basic.domain.BasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Repository
public interface BasUserRepository extends JpaRepository<BasUser, String>, JpaSpecificationExecutor<BasUser> {
    /**
     * 根据系统编码统计
     *
     * @param sysCode 系统编码
     * @return .
     */
    int countBySysCode(String sysCode);

    /**
     * 根据用户名查询系统编码
     *
     * @param username 用户名
     * @return 系统编码
     */
    @Query("select u.sysCode from BasUser u where u.username = ?1")
    String getSysCodeByUsername(String username);

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    BasUser findByUsername(String username);

    /**
     * 更新最后登录时间
     *
     * @param username 用户名
     */
    @Modifying
    @Query("update BasUser u set u.lastLoginTime = ?1 where u.username = ?2")
    void updateLastLoginTimeByUsername(Date lastLoginTime, String username);

    /**
     * 根据用户名查询是否存在
     *
     * @param username 用户名
     * @return .
     */
    boolean existsByUsername(String username);

    /**
     * 根据用户名查询是否存在
     *
     * @param username 用户名
     * @param id       需要排除的id
     * @return .
     */
    boolean existsByUsernameAndIdNot(String username, String id);

    /**
     * 重置密码
     *
     * @param id       id
     * @param password 密码
     * @param operator 操作人
     */
    @Modifying
    @Query("update BasUser u set u.password = ?2, u.modifiedBy = ?3, u.modified = now(),u.pwdResetTime = now() where " +
            "u.id = ?1")
    void resetPassword(String id, String password, String operator);
}
