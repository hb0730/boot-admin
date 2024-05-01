package com.hb0730.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hb0730.basic.domain.dto.BasUserDto;
import com.hb0730.basic.domain.entity.BasUser;
import com.hb0730.basic.domain.entity.BasUserRole;
import com.hb0730.basic.domain.query.BasUserQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Repository
public interface BasUserMapper extends BaseMapper<BasUser> {

    /**
     * 根据用户名获取租户编码
     *
     * @param username 用户名
     * @return 租户编码
     */
    @Select("select sys_code from bas_user where username = #{username}")
    String getSysCodeByUsername(String username);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Select("select * from bas_user where username = #{username}")
    BasUser getByUsername(String username);

    /**
     * 修改最后登录时间
     *
     * @param username 用户名
     * @param sysCode  租户编码
     */
    @Select("update bas_user set last_login_time = now() where username = #{username} and sys_code = #{sysCode}")
    void changeLastLoginTimeByUsername(String username, String sysCode);


    /**
     * 根据组织ID查询用户ID
     *
     * @param orgIds 组织ID
     * @return 用户ID
     */
    Set<String> findUserIdByOrgIds(List<String> orgIds);


    /**
     * 根据用户ID查询角色ID
     *
     * @param userIds 用户ID
     * @return 角色ID
     */
    Set<String> findRoleIdsByUserIds(Collection<String> userIds);

    /**
     * 账号是否存在
     *
     * @param username .
     * @return .
     */
    @Select("select count(1) from bas_user where username = #{username}")
    boolean existsUsername(String username);

    /**
     * 账号是否存在
     *
     * @param username .
     * @param id       .
     * @return .
     */
    @Select("select count(1) from bas_user where username = #{username} and id != #{id}")
    boolean existsUsernameByIdNot(String username, String id);

    /**
     * 查询用户列表
     *
     * @param page  分页
     * @param query 查询条件
     * @return 用户列表
     */
    List<BasUserDto> list(IPage<BasUserDto> page, @Param("query") BasUserQuery query);


    /**
     * 根据用户ID获取角色ID
     *
     * @param id 用户ID
     * @return 角色ID
     */
    @Select("select role_id from bas_user_role where user_id = #{id}")
    List<String> getRoleIds(String id);

    /**
     * 根据用户ID删除用户角色
     *
     * @param userId 用户ID
     */
    @Delete("delete from bas_user_role where user_id = #{userId}")
    void deleteUserRoleByUserId(String userId);

    /**
     * 保存用户角色
     *
     * @param userRoles 用户角色
     */
    void saveUserRole(List<BasUserRole> userRoles);
}
