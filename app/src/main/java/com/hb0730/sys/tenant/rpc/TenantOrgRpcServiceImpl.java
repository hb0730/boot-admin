package com.hb0730.sys.tenant.rpc;

import com.hb0730.base.conf.server.BaseServerRpcService;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.sys.tenant.domain.TenantOrgDto;
import com.hb0730.rpc.sys.tenant.domain.TenantSmallDto;
import com.hb0730.rpc.sys.tenant.domain.UpdateTenantOrgConfigDto;
import com.hb0730.rpc.sys.tenant.domain.query.TenantQuery;
import com.hb0730.rpc.sys.tenant.service.TenantOrgRpcService;
import com.hb0730.sys.system.domain.SysProduct;
import com.hb0730.sys.tenant.domain.TenantOrg;
import com.hb0730.sys.tenant.domain.TenantUser;
import com.hb0730.sys.tenant.rpc.mapstruct.TenantOrgMapper;
import com.hb0730.sys.tenant.service.ITenantOrgService;
import com.hb0730.sys.tenant.service.ITenantUserService;
import jakarta.annotation.Nullable;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TenantOrgRpcServiceImpl extends BaseServerRpcService<TenantOrgRpcService> implements TenantOrgRpcService {
    private final TenantOrgMapper organizationMapper;
    @Lazy
    @Resource
    private ITenantUserService basUserService;
    private final ITenantOrgService basOrganizationService;

    @Override
    public JR<Boolean> existsByCode(String code, @Nullable String id) {
        boolean existsed = basOrganizationService.existsBySysCode(code, id);
        return JR.okData(existsed);
    }

    @Override
    public JR<JsfPage<TenantOrgDto>> queryTenantOrganization(TenantQuery query) {
        Page<TenantOrg> page = basOrganizationService.tenantPage(query);
        List<TenantOrgDto> res = organizationMapper.toDtoList(page.getContent());
        JsfPage<TenantOrgDto> jr = JsfPage.of(page, res);
        return JR.okData(jr);
    }

    @Override
    public JR<String> saveTenantOrganization(TenantSmallDto dto) {
        TenantUser user = basUserService.findByUsername(dto.getLinkTel());
        if (null != user && StrUtil.isNotBlank(user.getUsername())) {
            return JR.fail("手机号已被使用");
        }
        basOrganizationService.saveTenant(dto);
        return JR.ok();
    }

    @Override
    public JR<String> updateTenantOrganization(TenantSmallDto dto) {
        TenantOrg org = basOrganizationService.findById(dto.getId());
        if (null == org) {
            return JR.fail("商户不存在");
        }
        boolean isEditProduct = false;
        boolean isEditLinkTel = false;
        if (!org.getLinkTel().equals(dto.getLinkTel())) {
            TenantUser user = basUserService.findByUsername(dto.getLinkTel());
            if (null != user && StrUtil.isNotBlank(user.getUsername())) {
                return JR.fail("手机号已被使用");
            }
            isEditLinkTel = true;
        }
        SysProduct product = org.getProduct();
        if (!product.getId().equals(dto.getProductId())) {
            isEditProduct = true;
        }

        basOrganizationService.updateTenant(dto, isEditProduct, isEditLinkTel);
        return JR.ok();
    }

    @Override
    public JR<String> resetPassword(String id, String operator) {
        TenantOrg tenantOrg = basOrganizationService.findById(id);
        if (null == tenantOrg) {
            return JR.fail("商户不存在");
        }
        basOrganizationService.resetPassword(tenantOrg, operator);
        return JR.ok();
    }

    @Override
    public JR<String> updateTenantOrgConfig(UpdateTenantOrgConfigDto dto) {
        TenantOrg tenantOrg = basOrganizationService.findById(dto.getId());
        if (null == tenantOrg) {
            return JR.fail("商户不存在");
        }
        basOrganizationService.updateTenantOrgConfig(dto, tenantOrg);
        return JR.ok();
    }
}
