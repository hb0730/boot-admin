package com.hb0730.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.sys.domain.entity.SysProduct;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Repository
public interface SysProductMapper extends BaseMapper<SysProduct> {

    /**
     * 根据编码查询
     *
     * @param code 编码
     * @return 是否存在
     */
    @Select("select count(1) from sys_product where code = #{code}")
    boolean existsByCode(String code);

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   需要排除的ID
     * @return 是否存在
     */
    @Select("select count(1) from sys_product where code = #{code} and id != #{id}")
    boolean existsByCodeAndIdNot(String code, String id);

    /**
     * 删除以分配的菜单
     *
     * @param id .
     */
    @Delete("delete from sys_product_permission where product_id = #{id}")
    void deleteMenus(String id);

    /**
     * 保存菜单
     *
     * @param id      .
     * @param menuIds .
     */
    void saveMenus(@Param("id") String id, @Param("permissionIds") List<String> menuIds);

    /**
     * 获取菜单
     *
     * @param id .
     * @return .
     */
    @Select("select permission_id from sys_product_permission where product_id = #{id}")
    List<String> getPermissionIds(String id);
}
