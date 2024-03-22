package com.hb0730.sys.service;

import com.hb0730.biz.dto.sys.system.UserDto;
import com.hb0730.biz.dto.sys.system.UserLoginDto;
import com.hb0730.biz.dto.sys.system.UserRestPwdDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.sys.bean.UserQuery;

import java.util.List;

/**
 * 用户 RPC 服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
public interface UserRpcService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    default JR<UserDto> findByUsername(String username) {
        return null;
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    default JR<UserLoginDto> loginByUsername(String username) {
        return null;
    }

    /**
     * 修改最后登录时间,修改时间为当前时间
     *
     * @param username 用户名
     */
    default JR<String> changeLastLoginTime(String username) {
        return null;
    }

    /**
     * 根据菜单ID查询用户
     *
     * @param menuId .
     * @return .
     */
    default JR<List<UserDto>> findByMenuId(Integer menuId) {
        return null;
    }

    /**
     * 根据角色ID查询用户
     *
     * @param roleId 角色ID
     * @return 用户
     */
    default JR<List<UserDto>> findByRoleId(Integer roleId) {
        return null;
    }


    /*========================ui api==========================================*/

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    default JR<JsfPage<UserDto>> page(UserQuery query) {
        return null;
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    default JR<List<UserDto>> list(UserQuery query) {
        return null;
    }

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @param id       需要排除的id
     * @return .
     */
    default JR<Boolean> existsByUsernameAndIdNot(String username, Integer id) {
        return null;
    }

    /**
     * 保存
     *
     * @param dto 数据
     * @return 是否成功
     */
    default JR<String> save(UserDto dto) {
        return null;
    }

    /**
     * 修改
     *
     * @param dto 数据
     * @return 是否成功
     */
    default JR<String> updateById(UserDto dto) {
        return null;
    }

    /**
     * 根据ID删除
     *
     * @param id .
     * @return 是否成功
     */
    default JR<String> deleteById(Integer id) {
        return null;
    }

    /**
     * 重置密码
     *
     * @param dto 数据
     * @return 是否成功
     */
    default JR<String> resetPassword(UserRestPwdDto dto) {
        return null;
    }
}
