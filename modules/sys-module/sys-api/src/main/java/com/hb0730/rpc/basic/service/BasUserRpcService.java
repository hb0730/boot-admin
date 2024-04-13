package com.hb0730.rpc.basic.service;

import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.basic.domain.BasUserDto;
import com.hb0730.rpc.basic.domain.BasUserRestPasswordDto;
import com.hb0730.rpc.basic.domain.BasUserSaveDto;
import com.hb0730.rpc.basic.domain.query.BasUserQuery;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface BasUserRpcService {
    /**
     * 根据用户名查询系统编码
     *
     * @param username 用户名
     * @return 系统编码
     */
    default JR<String> getSysCodeByUsername(String username) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    default JR<BasUserDto> findByUsername(String username) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新最后登录时间
     *
     * @param username 用户名
     * @return .
     */
    default JR<String> updateLastLoginTime(String username) {
        return JR.fail("暂未实现");
    }

    /**
     * 根据用户名查询是否存在
     *
     * @param username 用户名
     * @param sysCode  系统编码
     * @param id       id
     * @return .
     */
    default JR<Boolean> existsByUsername(String username, String sysCode, String id) {
        return JR.fail("暂未实现");
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    default JR<JsfPage<BasUserDto>> page(BasUserQuery query) {
        return JR.fail("暂未实现");
    }


    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    default JR<List<BasUserDto>> list(BasUserQuery query) {
        return JR.fail("暂未实现");
    }


    /**
     * 保存
     *
     * @param dto 保存数据
     * @return .
     */
    default JR<String> save(BasUserSaveDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新
     *
     * @param dto 更新数据
     * @return .
     */
    default JR<String> updateById(BasUserSaveDto dto) {
        return JR.fail("暂未实现");
    }

    /**
     * 重置密码
     *
     * @param dto 重置密码
     * @return .
     */
    default JR<String> restPassword(BasUserRestPasswordDto dto) {
        return JR.fail("暂未实现");
    }
}
