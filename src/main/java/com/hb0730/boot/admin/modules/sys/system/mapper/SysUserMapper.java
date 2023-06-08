package com.hb0730.boot.admin.modules.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysUser;
import com.hb0730.boot.admin.modules.sys.system.model.query.UserQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.UserVO;
import jakarta.annotation.Nullable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名获取已授权的角色代码
     *
     * @param username 用户名
     * @return 已授权的角色代码
     */
    @Nullable
    Set<String> getRoleCodeByUsername(@Param("username") String username);

    /**
     * 根据用户ID 获取对应的角色ID集合
     *
     * @param userId 用户ID
     * @return 角色ID集合
     */
    @Nullable
    Set<String> queryUserRole(@Param("userId") String userId);

    /**
     * 获取全部角色code
     *
     * @return .
     */
    @Nullable
    Set<String> allRoleCode();
    /*=========================================================*/

    /**
     * 分页查询
     * @param page .
     * @param query  .
     * @return .
     */
    List<UserVO> queryPage(Page<UserVO> page, @Param("query") UserQuery query);
}
