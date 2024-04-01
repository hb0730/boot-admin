package com.hb0730.basic.service;

import com.hb0730.basic.domain.BasRole;
import com.hb0730.rpc.basic.domain.query.BasRoleQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface IBasRoleService {

    /**
     * 根据id查询
     *
     * @param ids .
     * @return .
     */
    List<BasRole> findByIds(List<String> ids);

    /**
     * 根据用户id查询角色
     *
     * @param userId .
     * @return .
     */
    List<BasRole> findByUserId(String userId);

    /**
     * code是否存在
     *
     * @param code    角色标识
     * @param sysCode 商户识别码
     * @param id      需要排除的id
     * @return .
     */
    Boolean existsByCode(String code, String sysCode, String id);

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    Page<BasRole> page(BasRoleQuery query);

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    List<BasRole> list(BasRoleQuery query);

    /**
     * 保存
     *
     * @param basRole .
     */
    void save(BasRole basRole);

    /**
     * 根据id更新
     *
     * @param basRole .
     */
    void updateById(BasRole basRole);

    /**
     * 根据id删除
     *
     * @param id .
     */
    void deleteById(String id);

    /**
     * 分配权限
     *
     * @param id            .
     * @param permissionIds .
     */
    void grantPermission(String id, List<Long> permissionIds);
}
