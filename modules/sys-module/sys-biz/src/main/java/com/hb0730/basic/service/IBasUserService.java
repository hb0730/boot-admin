package com.hb0730.basic.service;

import com.hb0730.basic.domain.BasUser;
import com.hb0730.rpc.basic.domain.query.BasUserQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface IBasUserService {

    /**
     * 根据用户名查询系统编码
     *
     * @param username 用户名
     * @return 系统编码
     */
    String getSysCodeByUsername(String username);

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    BasUser findByUsername(String username);

    /**
     * 根据id查询
     *
     * @param id 用户id
     * @return 用户信息
     */
    BasUser findById(String id);

    /**
     * 更新最后登录时间
     *
     * @param username 用户名
     */
    void updateLastLoginTime(String username);

    /**
     * 根据用户名查询是否存在
     *
     * @param username 用户名
     * @param sysCode  系统编码
     * @param id       需要排除的id
     */
    Boolean existsByUsername(String username, String sysCode, String id);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    Page<BasUser> page(BasUserQuery query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<BasUser> list(BasUserQuery query);

    /**
     * 保存
     *
     * @param basUser .
     */
    void save(BasUser basUser);

    /**
     * 根据id更新
     *
     * @param basUser .
     */
    void updateById(BasUser basUser);

    /**
     * 重置密码
     *
     * @param id       用户id
     * @param password 密码
     * @param operator 操作人
     */
    void restPassword(String id, String password, String operator);

    /**
     * 根据id删除
     *
     * @param id 用户id
     */
    void deleteById(String id);
}
