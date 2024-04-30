package com.hb0730.basic.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.basic.domain.dto.BasOrgDto;
import com.hb0730.basic.domain.entity.BasOrg;
import com.hb0730.basic.domain.query.BasOrgQuery;
import com.hb0730.basic.domain.query.BasOrgTreeQuery;
import com.hb0730.basic.mapper.BasOrgMapper;
import com.hb0730.basic.service.mapstruct.BasOrgMapstruct;
import com.hb0730.mybatis.core.service.BaseService;
import com.hb0730.query.mybatis.plus.QueryHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasOrgService extends BaseService<BasOrgMapper, BasOrg> {
    private final BasOrgMapstruct mapstruct;

    /**
     * 登录时，校验相关机构是否过期
     *
     * @param orgId .
     */
    public void checkOrgExpiredForLogin(String orgId) {
        BasOrg userOrg = getById(orgId);
        if (userOrg == null) {
            throw new ServiceException("机构不存在");
        }
        String[] orgIds = userOrg.getPath().split(",");
        BasOrg adminOrg = getById(orgIds[0]);
        if (adminOrg == null) {
            throw new ServiceException("顶级机构不存在");
        }
        //是否已被禁用
        if (!Boolean.TRUE.equals(adminOrg.getEnabled())) {
            throw new ServiceException("系统已被禁用，请联系管理员！");
        }
        Date usedEndTime = adminOrg.getUsedEndTime();
        if (null == usedEndTime) {
            return;
        }
        // 当前时间在使用截止时间之前返回true
        if (usedEndTime.after(new Date())) {
            return;
        }
        throw new ServiceException("系统使用已失效，请联系管理员！");
    }

    /**
     * 站点数量是否超过限制
     *
     * @param orgId 机构ID
     * @return 是否超过
     */
    public boolean checkSiteNum(String orgId) {
        BasOrg org = getById(orgId);
        if (org == null) {
            throw new ServiceException("机构不存在");
        }
        String sysCode = org.getSysCode();
        BasOrg parentOrg = baseMapper.getTopOrg(sysCode);
        if (parentOrg == null) {
            throw new ServiceException("顶级机构不存在");
        }
        String productId = parentOrg.getProductId();
        if (productId == null) {
            throw new ServiceException("产品ID不存在");
        }
        int siteNum = baseMapper.getProductSiteNum(productId);
        if (siteNum <= 0) {
            return false;
        }
        int orgSiteNum = baseMapper.countBySysCode(sysCode);
        return orgSiteNum >= siteNum;
    }

    /**
     * 账号数量是否超过限制
     *
     * @param orgId 机构ID
     * @return 是否超过
     */
    public boolean checkAccountNum(String orgId) {
        BasOrg org = getById(orgId);
        if (org == null) {
            throw new ServiceException("机构不存在");
        }
        String sysCode = org.getSysCode();
        BasOrg parentOrg = baseMapper.getTopOrg(sysCode);
        if (parentOrg == null) {
            throw new ServiceException("顶级机构不存在");
        }
        String productId = parentOrg.getProductId();
        if (productId == null) {
            throw new ServiceException("产品ID不存在");
        }
        int accountNum = baseMapper.getProductAccountNum(productId);
        if (accountNum <= 0) {
            return false;
        }
        int orgAccountNum = baseMapper.countAccountBySysCode(sysCode);
        return orgAccountNum >= accountNum;
    }

    public void validateOrg(BasOrgDto dto, boolean isUpdate) {
        String parentId = dto.getParentId();
        if (!Boolean.TRUE.equals(dto.getSystem()) && parentId == null) {
            throw new ServiceException("父类ID不能为空");
        }
        Integer type = dto.getType();
        if (type == null) {
            throw new ServiceException("类型不能为空");
        }
        if (!Boolean.TRUE.equals(dto.getSystem()) && type == 1) {
            throw new ServiceException("类型不能为1");
        }
        if (!isUpdate) {
            boolean checkSiteNum = checkSiteNum(parentId);
            if (checkSiteNum) {
                throw new ServiceException("站点数量已达上限");
            }
        }
        if (isUpdate) {
            String id = dto.getId();
            if (id == null) {
                throw new ServiceException("ID不能为空");
            }
        }


    }

    /**
     * 根据产品ID查询是否存在
     *
     * @param id 产品ID
     * @return 是否存在
     */
    public boolean existsOrgByProduct(String id) {
        return baseMapper.existsOrgByProduct(id);
    }

    /**
     * 根据产品ID查询
     *
     * @param productId 产品ID
     * @return .
     */
    public List<BasOrg> listByProduct(String productId) {
        return baseMapper.listByProduct(productId);
    }

    /**
     * 获取顶级机构
     *
     * @param sysCode 商户号
     * @return .
     */
    public BasOrg getTopOrg(String sysCode) {
        return baseMapper.getTopOrg(sysCode);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return .
     */
    public List<BasOrgDto> list(BasOrgTreeQuery query) {
        QueryWrapper<BasOrg> queryWrapper = QueryHelper.ofBean(query);
        List<BasOrg> list = baseMapper.selectList(queryWrapper);
        return mapstruct.toDtoList(list);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return .
     */
    public List<BasOrgDto> list(BasOrgQuery query) {
        QueryWrapper<BasOrg> queryWrapper = QueryHelper.ofBean(query);
        List<BasOrg> list = baseMapper.selectList(queryWrapper);
        return mapstruct.toDtoList(list);
    }


    /**
     * 保存
     *
     * @param dto .
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(BasOrgDto dto) {
        validateOrg(dto, false);
        BasOrg parentOrg = getById(dto.getParentId());
        BasOrg entity = mapstruct.toEntity(dto);
        //设置机构路径
        entity.setPath(parentOrg.getPath() + "," + entity.getId());
        // 设置机构层级
        entity.setLevel(parentOrg.getLevel() + 1);
        entity.setSystem(false);
        entity.setSaas(false);
        save(entity);
    }

    /**
     * 更新
     *
     * @param dto .
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(BasOrgDto dto) {
        validateOrg(dto, true);
        String id = dto.getId();
        if (id == null) {
            throw new ServiceException("ID不能为空");
        }
        BasOrg entity = getById(dto.getId());
        // 系统机构不允许修改
        if (Boolean.TRUE.equals(entity.getSystem())) {
            dto.setLinkTel(null);
            dto.setLinkMan(null);
            dto.setName(null);
            dto.setSystem(null);
            dto.setSaas(null);
            dto.setPath(null);
            dto.setLevel(null);
        } else {
            String parentId = dto.getParentId();
            if (parentId == null) {
                throw new ServiceException("父类ID不能为空");
            }
            BasOrg parentOrg = getById(dto.getParentId());
            if (parentOrg == null) {
                throw new ServiceException("父类ID不存在");
            }
            //设置机构路径
            entity.setPath(parentOrg.getPath() + "," + entity.getId());
            // 设置机构层级
            entity.setLevel(parentOrg.getLevel() + 1);
            entity.setSystem(false);
            entity.setSaas(false);
        }
        BeanUtil.copyProperties(dto, entity, CopyOptions.create().setIgnoreNullValue(true));
        updateById(entity);
    }

    /**
     * 删除
     *
     * @param id .
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        BasOrg entity = getById(id);
        if (entity == null) {
            throw new ServiceException("机构不存在");
        }
        // 是否顶级机构
        if (Boolean.TRUE.equals(entity.getSystem())) {
            throw new ServiceException("系统机构不允许删除");
        }
        // 是否有子机构
        boolean hasChild = baseMapper.hasChild(id);
        if (hasChild) {
            throw new ServiceException("请先删除子机构");
        }
        // 是否分配用户
        boolean hasUser = baseMapper.hasUser(id);
        if (hasUser) {
            throw new ServiceException("请先删除用户");
        }
        removeById(id);
    }
}
