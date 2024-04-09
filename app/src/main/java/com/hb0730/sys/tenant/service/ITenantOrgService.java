package com.hb0730.sys.tenant.service;

import com.hb0730.rpc.sys.tenant.domain.TenantSmallDto;
import com.hb0730.rpc.sys.tenant.domain.UpdateTenantOrgConfigDto;
import com.hb0730.rpc.sys.tenant.domain.query.TenantQuery;
import com.hb0730.sys.tenant.domain.TenantOrg;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
public interface ITenantOrgService {

    /**
     * 根据产品ID查询
     *
     * @param productId .
     * @return .
     */
    List<TenantOrg> findByProductId(Long productId);

    /**
     * 根据ID查询
     *
     * @param id .
     * @return .
     */
    TenantOrg findById(String id);

    /**
     * 根据产品ID是否存在
     *
     * @param productId .
     * @return .
     */
    boolean existsByProductId(Long productId);

    /**
     * 厂商识别码是否存在
     *
     * @param sysCode .
     * @param id      需要排除的ID
     * @return .
     */
    boolean existsBySysCode(String sysCode, String id);

    /**
     * 商户 分页查询
     *
     * @param query .
     * @return .
     */
    Page<TenantOrg> tenantPage(TenantQuery query);

    /**
     * 商户列表
     *
     * @param query .
     * @return .
     */
    List<TenantOrg> tenantList(TenantQuery query);

    /**
     * 保存商户
     *
     * @param dto .
     */
    void saveTenant(TenantSmallDto dto);

    /**
     * 更新商户
     *
     * @param dto           .
     * @param isEditLinkTel 是否修改了联系电话
     * @param isEditProduct 是否修改了产品
     */
    void updateTenant(TenantSmallDto dto, boolean isEditProduct, boolean isEditLinkTel);

    /**
     * 重置密码
     *
     * @param org      . 【必须包含linkTel】
     * @param operator 操作人
     */
    void resetPassword(TenantOrg org, String operator);

    /**
     * 更新商户组织机构配置
     *
     * @param dto       配置
     * @param tenantOrg . 请先查询出来【保证相关关联数据正确】
     */
    void updateTenantOrgConfig(UpdateTenantOrgConfigDto dto, @NotBlank TenantOrg tenantOrg);
}
