package com.hb0730.sys.system.repository.mapper;

import com.hb0730.mybatis.core.mapper.IMapper;
import com.hb0730.sys.system.model.entity.SysPermission;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 菜单与权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public interface SysPermissionMapper extends IMapper<SysPermission> {

    /**
     * 根据用户id获取权限
     *
     * @param userId 用户id
     * @return 权限
     */
    @Select("select * from sys_permission where id in (select permission_id from sys_role_permission where role_id in" +
            " (select role_id from sys_user_role where user_id = #{userId})) and del_flag = 0")
    List<SysPermission> findByUserId(String userId);

    /**
     * 修改是否是叶子节点
     *
     * @param id   id
     * @param leaf 是否是叶子节点
     * @return 是否成功
     */
    @Update("update sys_permission set is_leaf = #{leaf} where id = #{id}")
    boolean changeLeafById(String id, Integer leaf);
}
