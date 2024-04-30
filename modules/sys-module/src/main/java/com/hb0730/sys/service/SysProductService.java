package com.hb0730.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.basic.service.BasOrgService;
import com.hb0730.common.api.JsfPage;
import com.hb0730.mybatis.core.service.BaseService;
import com.hb0730.query.mybatis.plus.QueryHelper;
import com.hb0730.sys.domain.dto.ProductDto;
import com.hb0730.sys.domain.entity.SysProduct;
import com.hb0730.sys.domain.query.ProductQuery;
import com.hb0730.sys.mapper.SysProductMapper;
import com.hb0730.sys.service.mapstruct.SysProductMapstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysProductService extends BaseService<SysProductMapper, SysProduct> {
    private final SysProductMapstruct mapstruct;
    @Lazy
    @Resource
    private BasOrgService basOrgService;
    @Lazy
    @Resource
    private SysTenantPermissionService sysTenantPermissionService;

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   需要排除的ID
     * @return 是否存在
     */
    public Boolean existsCode(String code, String id) {
        if (StrUtil.isBlank(id)) {
            return baseMapper.existsByCode(code);
        }
        return baseMapper.existsByCodeAndIdNot(code, id);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 结果
     */
    public List<ProductDto> list(ProductQuery query) {
        QueryWrapper<SysProduct> queryWrapper = QueryHelper.ofBean(query);
        List<SysProduct> sysProducts = baseMapper.selectList(queryWrapper);
        return mapstruct.toDtoList(sysProducts);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 结果
     */
    public JsfPage<ProductDto> page(ProductQuery query) {
        QueryWrapper<SysProduct> queryWrapper = QueryHelper.ofBean(query);
        IPage<SysProduct> page = QueryHelper.toPage(query);
        page = baseMapper.selectPage(page, queryWrapper);
        List<ProductDto> dtoList = mapstruct.toDtoList(page.getRecords());
        return QueryHelper.toJsfPage(page, dtoList);

    }


    /**
     * 保存
     *
     * @param dto dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(ProductDto dto) {
        SysProduct sysProduct = mapstruct.toEntity(dto);
        save(sysProduct);
    }

    /**
     * 更新
     *
     * @param dto dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(ProductDto dto) {
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            throw new ServiceException("id不能为空");
        }
        SysProduct sysProduct = baseMapper.selectById(id);
        BeanUtil.copyProperties(
                dto,
                sysProduct,
                CopyOptions.create().ignoreNullValue()
        );
        updateById(sysProduct);
    }

    /**
     * 删除
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        // 已经分配的产品不能删除
        if (basOrgService.existsOrgByProduct(id)) {
            throw new ServiceException("机构已经关联该产品，无法删除");
        }
        removeById(id);
        // 删除产品关联的菜单
        baseMapper.deleteMenus(id);
    }

    /**
     * 获取权限ID
     *
     * @param id id
     * @return 权限ID
     */
    public List<String> getPermissionIds(String id) {
        return baseMapper.getPermissionIds(id);
    }

    /**
     * 分配菜单
     *
     * @param id            id
     * @param permissionIds 权限ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void grantMenus(String id, List<String> permissionIds) {
        //删除已经分配的菜单
        baseMapper.deleteMenus(id);
        // 重新分配
        baseMapper.saveMenus(id, permissionIds);

        sysTenantPermissionService.grantMenus(id, permissionIds);
    }
}
