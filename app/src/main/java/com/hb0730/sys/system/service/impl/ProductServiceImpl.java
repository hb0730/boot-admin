package com.hb0730.sys.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.system.domain.query.ProductQuery;
import com.hb0730.sys.system.domain.SysProduct;
import com.hb0730.sys.system.repository.ProductRepository;
import com.hb0730.sys.system.service.IProductService;
import com.hb0730.sys.tenant.domain.TenantPermission;
import com.hb0730.sys.tenant.service.ITenantOrgService;
import com.hb0730.sys.tenant.service.ITenantPermissionService;
import com.hb0730.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    private final ITenantOrgService baseOrganizationService;
    private final ITenantPermissionService basePermissionService;


    @Override
    public Boolean existsByCode(String code, Long id) {
        if (id == null) {
            return productRepository.existsByCode(code);
        }
        return productRepository.existsByCodeAndIdNot(code, id);
    }

    @Override
    public List<SysProduct> list(ProductQuery query) {
        Specification<SysProduct> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sorts = query.getSorts();
        List<Sort.Order> orders =
                sorts.orElseGet(() -> CollectionUtil.newArrayList(new Sort.Order(Sort.Direction.DESC, "created")));
        return productRepository.findAll(specification, Sort.by(orders));
    }

    @Override
    public Page<SysProduct> page(ProductQuery query) {
        Specification<SysProduct> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return productRepository.findAll(specification, page);
    }

    @Override
    public void save(SysProduct product) {
        productRepository.save(product);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(SysProduct product) {
        if (product.getId() == null) {
            throw new ServiceException("id不能为空");
        }
        SysProduct _product = productRepository.findById(product.getId()).orElseThrow(() -> new ServiceException("数据不存在"));
        BeanUtil.copyProperties(product, _product, CopyOptions.create().ignoreNullValue());
        productRepository.save(_product);
        // TODO 是否根据产品的禁用与启用来禁用与启用机构
    }

    @Override
    public JR<String> deleteById(Long id) {
        boolean existed = baseOrganizationService.existsByProductId(id);
        if (existed) {
            return JR.fail("机构已经关联该产品，无法删除");
        }
        productRepository.deleteById(id);
        return JR.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantMenus(Long id, List<Long> menuIds) {
        SysProduct product = productRepository.findById(id).orElseThrow(() -> new ServiceException("数据不存在"));
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<TenantPermission> permissions = menuIds.stream().map(e -> {
                TenantPermission sysMenu = new TenantPermission();
                sysMenu.setId(e);
                return sysMenu;
            }).collect(Collectors.toList());
            product.setPermissions(permissions);
        } else {
            product.setPermissions(null);
        }
        productRepository.save(product);

        // 授权
        basePermissionService.grantMenus(id, menuIds);
    }
}
