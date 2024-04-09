package com.hb0730.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.basic.domain.BasOrg;
import com.hb0730.basic.repository.BasOrgRepository;
import com.hb0730.basic.repository.BasUserRepository;
import com.hb0730.basic.service.IBasOrgService;
import com.hb0730.rpc.basic.domain.query.BasOrgQuery;
import com.hb0730.sys.system.domain.SysProduct;
import com.hb0730.util.QueryHelper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasOrgServiceImpl implements IBasOrgService {
    private final BasOrgRepository basOrgRepository;
    @Lazy
    @Resource
    private BasUserRepository basUserRepository;

    @Override
    public BasOrg findByOrgId(String orgId) {
        return basOrgRepository.findById(orgId).orElse(null);
    }

    @Override
    public boolean checkSiteNum(String orgId) {
        BasOrg org = basOrgRepository.findById(orgId)
                .orElseThrow(() -> new ServiceException("机构不存在"));
        String sysCode = org.getSysCode();
        BasOrg systemIsTrueBySysCode = basOrgRepository.findBySysCodeAndSystemIsTrue(sysCode);
        if (systemIsTrueBySysCode == null) {
            return false;
        }
        SysProduct product = systemIsTrueBySysCode.getProduct();
        if (product == null) {
            return false;
        }
        Integer siteNum = product.getSiteNum();
        if (siteNum == null) {
            return false;
        }
        int siteNumNow = basOrgRepository.countBySysCode(sysCode);
        return siteNumNow < siteNum;
    }

    @Override
    public boolean checkAccountNum(String orgId) {
        BasOrg org = basOrgRepository.findById(orgId)
                .orElseThrow(() -> new ServiceException("机构不存在"));
        String sysCode = org.getSysCode();
        BasOrg systemIsTrueBySysCode = basOrgRepository.findBySysCodeAndSystemIsTrue(sysCode);
        if (systemIsTrueBySysCode == null) {
            return false;
        }
        SysProduct product = systemIsTrueBySysCode.getProduct();
        if (product == null) {
            return false;
        }
        Integer accountNum = product.getAccountNum();
        if (accountNum == null) {
            return false;
        }
        int accountNumNow = basUserRepository.countBySysCode(sysCode);
        return accountNumNow < accountNum;
    }

    @Override
    public List<BasOrg> listDefaultRootQuery(BasOrgQuery query) {
        QueryHelper.setFieldNull(query, Map.of("parentIdIsNull", "parentId"), "size", "current", "parentIdIsNull",
                "sorts");
        Specification<BasOrg> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sortOpl = query.getSorts();
        return sortOpl.map(orders -> basOrgRepository.findAll(specification, Sort.by(orders)))
                .orElseGet(() -> basOrgRepository.findAll(specification));
    }

    @Override
    public List<BasOrg> list(BasOrgQuery query) {
        Specification<BasOrg> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sortOpl = query.getSorts();
        return sortOpl.map(orders -> basOrgRepository.findAll(specification, Sort.by(orders)))
                .orElseGet(() -> basOrgRepository.findAll(specification));
    }

    @Override
    public void save(BasOrg basOrg) {
        basOrgRepository.save(basOrg);
    }

    @Override
    public void updateById(BasOrg basOrg) {
        BasOrg org = basOrgRepository.findById(basOrg.getId()).orElseThrow(
                () -> new ServiceException("机构不存在"));

        BeanUtil.copyProperties(basOrg, org, CopyOptions.create().ignoreNullValue());
        basOrgRepository.save(org);
    }
}
