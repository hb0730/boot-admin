package com.hb0730.sys.tenant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.base.utils.PasswordUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.common.util.PageUtil;
import com.hb0730.jpa.specification.SpecificationUtil;
import com.hb0730.rpc.sys.tenant.domain.TenantBasicConfigDto;
import com.hb0730.rpc.sys.tenant.domain.TenantSmallDto;
import com.hb0730.rpc.sys.tenant.domain.query.TenantQuery;
import com.hb0730.sys.system.domain.SysProduct;
import com.hb0730.sys.tenant.domain.TenantOrg;
import com.hb0730.sys.tenant.domain.TenantPermission;
import com.hb0730.sys.tenant.domain.TenantRole;
import com.hb0730.sys.tenant.domain.TenantUser;
import com.hb0730.sys.tenant.repository.TenantOrgRepository;
import com.hb0730.sys.tenant.repository.TenantRoleRepository;
import com.hb0730.sys.tenant.repository.TenantUserRepository;
import com.hb0730.sys.tenant.service.ITenantOrgService;
import com.hb0730.sys.tenant.service.ITenantPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TenantOrgServiceImpl implements ITenantOrgService {
    private final TenantOrgRepository tenantOrgRepository;
    @Lazy
    @Resource
    private ITenantPermissionService basPermissionService;
    @Lazy
    @Resource
    private TenantRoleRepository basRoleRepository;
    @Lazy
    @Resource
    private TenantUserRepository basUserRepository;

    @Override
    public List<TenantOrg> findByProductId(Long productId) {
        return tenantOrgRepository.findByProductId(productId);
    }

    @Override
    public TenantOrg findById(String id) {
        return tenantOrgRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByProductId(Long productId) {
        return tenantOrgRepository.existsByProductId(productId);
    }

    @Override
    public boolean existsBySysCode(String sysCode, String id) {
        if (id == null) {
            return tenantOrgRepository.existsBySysCodeAndSystemIsTrue(sysCode);
        }
        return tenantOrgRepository.existsBySysCodeAndIdNotAndSystemIsTrue(sysCode, id);
    }

    @Override
    public Page<TenantOrg> tenantPage(TenantQuery query) {
        Specification<TenantOrg> specification = SpecificationUtil.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return tenantOrgRepository.findAll(specification, page);
    }

    @Override
    public List<TenantOrg> tenantList(TenantQuery query) {
        Specification<TenantOrg> specification = SpecificationUtil.ofBean(query);
        return tenantOrgRepository.findAll(specification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTenant(TenantSmallDto dto) {
        TenantOrg organization = convert(dto);
        tenantOrgRepository.save(organization);
//        String linkTel = dto.getLinkTel();
//        BasUser user = basUserRepository.findByUsername(linkTel);
//        if (null != user && StrUtil.isNotBlank(user.getUsername())) {
//            throw new BadRequestException("手机号已被使用");
//        }
        initUserRoleProduct(organization);
    }

    /**
     * 更新商户
     *
     * @param dto .
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTenant(TenantSmallDto dto, boolean isEditProduct, boolean isEditLinkTel) {
        TenantOrg oldOrg = tenantOrgRepository.findById(dto.getId()).orElseThrow(() -> new ServiceException("商户不存在"));
        TenantOrg organization = convert(dto);
        if (isEditProduct) {
            isUpdateProduct(organization, oldOrg);
        }
        if (isEditLinkTel) {
            isUpdateUser(organization, oldOrg);
        }
        BeanUtil.copyProperties(organization, oldOrg, CopyOptions.create().ignoreNullValue());
        tenantOrgRepository.save(oldOrg);
    }

    @Override
    public void resetPassword(TenantOrg org, String operator) {
        org.setModified(new Date());
        org.setModifiedBy(operator);
        isUpdateUser(org, org);
    }

    @Override
    public TenantBasicConfigDto getTenantBasicConfig(String id) {
        TenantOrg tenantOrg = tenantOrgRepository.findById(id).orElse(new TenantOrg());
        TenantBasicConfigDto dto = new TenantBasicConfigDto();
        BeanUtil.copyProperties(tenantOrg, dto);
        return dto;
    }

    @Override
    public void updateTenantOrgBasicConfig(TenantBasicConfigDto dto, TenantOrg tenantOrg) {
        tenantOrg.setModifiedBy(dto.getModifiedBy());
        tenantOrg.setModified(dto.getModified());
        tenantOrg.setUsedEndTime(dto.getUsedEndTime());
        tenantOrgRepository.save(tenantOrg);
    }

    private TenantOrg convert(TenantSmallDto dto) {
        TenantOrg organization = new TenantOrg();
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            id = IdUtil.getSnowflakeNextIdStr();
        }
        // 设置id
        organization.setId(id);
        // 设置商户识别码
        organization.setSysCode(dto.getSysCode());
        // 商户名称
        organization.setName(dto.getName());
        organization.setParentId(null);
        // 联系人
        organization.setLinkMan(dto.getLinkMan());
        // 联系电话
        organization.setLinkTel(dto.getLinkTel());
        // 联系邮箱
        organization.setLinkEmail(dto.getLinkEmail());
        // 机构地址
        organization.setAddress(dto.getAddress());
        // 总部标识
        organization.setSystem(true);
        // 启用saas
        organization.setSaas(true);
        // 产品ID
        SysProduct sysProduct = new SysProduct();
        sysProduct.setId(dto.getProductId());
        organization.setProduct(sysProduct);
        // 截止日期
        organization.setUsedEndTime(dto.getUsedEndTime());
        //网点等级
        organization.setLevel(1);
        // 机构路径
        organization.setPath(id);
        // 机构类型
        organization.setType(1);
        // 备注
        organization.setMemo(dto.getMemo());
        //是否启用
        Boolean enabled = dto.getEnabled();
        if (null == enabled) {
            enabled = true;
        }
        organization.setEnabled(enabled);
        // 创建人
        organization.setCreatedBy(dto.getCreatedBy());
        // 创建时间
        organization.setCreated(dto.getCreated());
        // 更新人
        organization.setModifiedBy(dto.getModifiedBy());
        // 更新时间
        organization.setModified(dto.getModified());
        return organization;
    }

    private void initUserRoleProduct(TenantOrg organization) {
        // 1、为厂商自动创建管理角色，角色对应权限默认系统所有
        TenantRole role = new TenantRole();
        String roleId = IdUtil.getSnowflakeNextIdStr();
        role.setId(roleId);
        role.setCreated(new Date());
        role.setCreatedBy(organization.getCreatedBy());
        role.setCode(organization.getSysCode());
        role.setSysCode(organization.getSysCode());
        role.setName("管理角色");
        role.setSystem(true);
        role.setEnabled(true);
        role.setOrg(organization);
        // 1.1 角色-权限绑定
        List<TenantPermission> permissions = basPermissionService.findByProductId(organization.getProduct().getId());
        role.setPermissions(permissions);
        // 2、厂商自动开户，同时为账户绑定初始角色
        TenantUser user = new TenantUser();
        String userId = IdUtil.getSnowflakeNextIdStr();
        user.setId(userId);
        user.setCreated(new Date());
        user.setCreatedBy(organization.getCreatedBy());
        user.setUsername(organization.getLinkTel());
        String pwd = RandomUtil.randomString(9);
        log.info("creat: {}", pwd);
        user.setPassword(PasswordUtil.encoder(pwd));
        user.setNickname("管理员");
        user.setPhone(organization.getLinkTel());
        user.setSystem(true);
        user.setEnabled(true);
        user.setOrg(organization);
        user.setSysCode(organization.getSysCode());
        user.setRoles(List.of(role));

        basRoleRepository.save(role);
        basUserRepository.save(user);
    }

    /**
     * 更新了产品
     *
     * @param newOrg .
     * @param oldOrg .
     */
    private void isUpdateProduct(TenantOrg newOrg, TenantOrg oldOrg) {
        SysProduct product = newOrg.getProduct();
        // 查询新产品权限
        List<TenantPermission> newProductPermission = basPermissionService.findByProductId(product.getId());

        basPermissionService.checkPermission(newProductPermission, List.of(oldOrg.getId()));
    }

    /**
     * 更新了预留电话
     *
     * @param newOrg .
     * @param oldOrg .
     */
    private void isUpdateUser(TenantOrg newOrg, TenantOrg oldOrg) {
        TenantUser user = basUserRepository.findByUsername(oldOrg.getLinkTel());
        if (null == user) {
            throw new ServiceException("机构管理员角色信息异常~~");
        }
        user.setModified(newOrg.getModified());
        user.setModifiedBy(newOrg.getModifiedBy());
        user.setUsername(newOrg.getLinkTel());
        user.setPhone(newOrg.getLinkTel());
        String pwd = RandomUtil.randomString(9);
        log.info("creat: {}", pwd);
        user.setPassword(PasswordUtil.encoder(pwd));
        // 更新用户信息
        basUserRepository.save(user);
    }
}
