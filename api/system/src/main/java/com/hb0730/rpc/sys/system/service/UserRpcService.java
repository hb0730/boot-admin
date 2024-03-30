package com.hb0730.rpc.sys.system.service;

import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.sys.system.domain.UserDto;
import com.hb0730.rpc.sys.system.domain.UserRestPwdDto;
import com.hb0730.rpc.sys.system.domain.query.UserQuery;

import java.util.List;

/**
 * 用户 RPC 服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
public interface UserRpcService {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    default JR<UserDto> findByUsername(String username) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID查询
     *
     * @param id .
     * @return .
     */
    default JR<UserDto> findById(Long id) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新最后登录时间
     *
     * @param username .
     * @return .
     */
    default JR<String> changeLastLoginTime(String username) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据菜单查询
     *
     * @param menuId .
     * @return .
     */
    default JR<List<UserDto>> findByMenuId(Long menuId) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据角色查询
     *
     * @param roleId .
     * @return .
     */
    default JR<List<UserDto>> findByRoleId(Long roleId) {
        return JR.fail("暂未实现");
    }

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    default JR<JsfPage<UserDto>> page(UserQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 账号是否存在
     *
     * @param username 账号
     * @param id       排除的ID
     * @return .
     */
    default JR<Boolean> existsByUsernameAndIdNot(String username, Long id) {
        return JR.fail("暂未实现");
    }

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    default JR<List<UserDto>> list(UserQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 新增用户
     *
     * @param dto .
     * @return .
     */
    default JR<String> save(UserDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID修改
     *
     * @param dto .
     * @return .
     */
    default JR<String> updateById(UserDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据ID删除
     *
     * @param id .
     * @return .
     */
    default JR<String> deleteById(Long id) {
        return JR.fail("暂未实现");
    }

    /**
     * 重置密码
     *
     * @param dto .
     * @return .
     */
    default JR<String> resetPassword(UserRestPwdDto dto) {
        return JR.fail("暂未实现");
    }
}
