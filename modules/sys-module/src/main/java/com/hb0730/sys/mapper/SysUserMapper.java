package com.hb0730.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.sys.domain.dto.RoleSmallDto;
import com.hb0730.sys.domain.entity.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Select("select * from sys_user where username = #{username}")
    SysUser getByUsername(String username);

    /**
     * 修改最后登录时间
     *
     * @param username 用户名
     */
    @Select("update sys_user set last_login_time = now() where username = #{username}")
    void changeLastLoginTimeByUsername(String username);

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 角色
     */
    @Select("select r.id,r.name from sys_role r " +
            "left join sys_user_role ur on r.id = ur.role_id " +
            "where ur.user_id = #{userId}")
    List<RoleSmallDto> getRolesByUserId(String userId);

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    @Select("select count(1) from sys_user where username = #{username}")
    boolean existsByUsername(String username);

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @param id       需要排除的id
     * @return 是否存在
     */
    @Select("select count(1) from sys_user where username = #{username} and id != #{id}")
    boolean existsByUsernameAndIdNot(String username, String id);

    /**
     * 根据用户id删除用户角色
     *
     * @param userId 用户id
     */
    @Delete("delete from sys_user_role where user_id = #{userId}")
    void deleteUserRoleByUserId(String userId);

    /**
     * 保存用户角色
     *
     * @param userId  用户id
     * @param roleIds 角色id
     */
    void saveUserRole(String userId, List<String> roleIds);

}
