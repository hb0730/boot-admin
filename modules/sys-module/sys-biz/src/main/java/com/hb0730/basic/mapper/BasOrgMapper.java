package com.hb0730.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.basic.domain.entity.BasOrg;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Repository
public interface BasOrgMapper extends BaseMapper<BasOrg> {

    /**
     * 根据产品ID查询是否存在
     *
     * @param id 产品ID
     * @return 是否存在
     */
    @Select("SELECT count(1) from bas_organization where product_id = #{id}")
    boolean existsOrgByProduct(String id);

    /**
     * 根据产品ID查询
     *
     * @param productId 产品ID
     * @return .
     */
    @Select("SELECT * from bas_organization where product_id = #{productId}")
    List<BasOrg> listByProduct(String productId);

    /**
     * 是否有子节点
     *
     * @param id id
     * @return .
     */
    @Select("SELECT count(1) from bas_organization where parent_id = #{id}")
    boolean hasChild(String id);

    /**
     * 是否有用户
     *
     * @param id id
     * @return .
     */
    @Select("SELECT count(1) from bas_user where org_id = #{id}")
    boolean hasUser(String id);

    /**
     * 获取顶级机构
     *
     * @param sysCode 商户号
     * @return .
     */
    @Select("SELECT * from bas_organization where is_system = 1 and sys_code = #{sysCode}")
    BasOrg getTopOrg(String sysCode);

    /**
     * 获取产品站点数量
     *
     * @param productId 产品ID
     * @return .
     */
    @Select("SELECT site_num from `sys_product` where id = #{productId}")
    int getProductSiteNum(String productId);

    /**
     * 获取产品账号数量
     *
     * @param productId 产品ID
     * @return .
     */
    @Select("SELECT account_num from `sys_product` where id = #{productId}")
    int getProductAccountNum(String productId);

    /**
     * 统计站点数量
     *
     * @param sysCode 商户号
     * @return .
     */
    @Select("SELECT count(1) from bas_organization where sys_code = #{sysCode}")
    int countBySysCode(String sysCode);

    /**
     * 统计账号数量
     *
     * @param sysCode 商户号
     * @return .
     */
    @Select("SELECT count(1) from bas_user where sys_code = #{sysCode}")
    int countAccountBySysCode(String sysCode);
}
