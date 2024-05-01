package com.hb0730.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.basic.domain.entity.BasPermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Repository
public interface BasPermissionMapper extends BaseMapper<BasPermission> {

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    List<BasPermission> listByRoleIds(List<String> roleIds);

    /**
     * 根据产品ID获取权限
     *
     * @param productId 产品ID
     * @return 权限
     */
    @Select("SELECT p.* from bas_permission p,sys_product_permission pp WHERE p.id=pp.permission_id AND pp" +
            ".product_id=#{productId} ORDER BY p.`rank`")
    List<BasPermission> findByProductId(String productId);

}
