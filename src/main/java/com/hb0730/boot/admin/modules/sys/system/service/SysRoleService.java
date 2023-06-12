package com.hb0730.boot.admin.modules.sys.system.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysRoleMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysRole;
import com.hb0730.boot.admin.modules.sys.system.model.query.RoleQuery;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysRoleService extends BaseServiceImpl<SysRoleMapper, SysRole> {
    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    public BasePage<SysRole> queryPage(RoleQuery query) {
        LambdaQueryWrapper<SysRole> queryWrapper = buildQuery(query);
        IPage<SysRole> page = new Page<>(query.getCurrent(), query.getSize());
        page = this.page(page, queryWrapper);
        return new BasePage<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    public List<SysRole> queryList(RoleQuery query) {
        LambdaQueryWrapper<SysRole> queryWrapper = buildQuery(query);
        return this.list(queryWrapper);
    }

    /**
     * 检查code是否重复
     *
     * @param code .
     * @param id   需要排查的ID，可以为空
     * @return .
     */
    public boolean checkCode(@Nonnull String code, @Nullable String id) {
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery(SysRole.class)
            .eq(SysRole::getRoleCode, code);
        if (StrUtil.isNotBlank(id)) {
            queryWrapper.ne(SysRole::getId, id);
        }
        return count(queryWrapper) > 0;
    }

    /**
     * 保存
     *
     * @param sysRole .
     * @return .
     */
    @Transactional(rollbackFor = Exception.class)
    public R<SysRole> saveRole(SysRole sysRole) {
        if (StrUtil.isBlank(sysRole.getRoleName()) || StrUtil.isBlank(sysRole.getRoleCode())) {
            return R.NG("参数错误,请检查参数");
        }
        boolean checkCode = checkCode(sysRole.getRoleCode(), null);
        if (checkCode) {
            return R.NG("角色编码: " + sysRole.getRoleCode() + "重复");
        }
        save(sysRole);
        return R.OK(sysRole);
    }

    /**
     * 更新角色
     *
     * @param sysRole .
     * @return .
     */
    public R<SysRole> updateRole(SysRole sysRole) {
        if (StrUtil.isBlank(sysRole.getRoleName()) || StrUtil.isBlank(sysRole.getRoleCode())) {
            return R.NG("参数错误,请检查参数");
        }
        boolean checkCode = checkCode(sysRole.getRoleCode(), sysRole.getId());
        if (checkCode) {
            return R.NG("角色编码: " + sysRole.getRoleCode() + "重复");
        }
        updateById(sysRole);
        return R.OK(sysRole);
    }

    /**
     * 根据ID删除角色
     *
     * @param ids .
     * @return .
     */
    @Transactional(rollbackFor = Exception.class)
    public R<String> deleteByIds(@Nonnull List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return R.NG("删除失败");
        }
        boolean remove = removeByIds(ids);
        if (remove) {
            return R.OK("删除成功");
        }
        return R.NG("删除失败");

    }

    private LambdaQueryWrapper<SysRole> buildQuery(RoleQuery query) {
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery(SysRole.class);
        if (null == query) {
            return queryWrapper;
        }
        if (StrUtil.isNotBlank(query.getRoleCode())) {
            queryWrapper.eq(SysRole::getRoleCode, query.getRoleCode());
        }
        if (StrUtil.isNotBlank(query.getRoleName())) {
            queryWrapper.like(SysRole::getRoleName, query.getRoleName());
        }
        if (null != query.getEnabled()) {
            queryWrapper.eq(SysRole::getEnabled, query.getEnabled());
        }
        return queryWrapper;
    }
}
