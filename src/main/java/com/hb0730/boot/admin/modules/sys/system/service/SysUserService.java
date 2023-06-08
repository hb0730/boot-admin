package com.hb0730.boot.admin.modules.sys.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysUserMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysUser;
import com.hb0730.boot.admin.modules.sys.system.model.query.UserQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.UserVO;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Slf4j
@Service
public class SysUserService extends BaseServiceImpl<SysUserMapper, SysUser> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return .
     */
    public Optional<SysUser> loadUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getUsername, username);
        return Optional.ofNullable(getOne(queryWrapper));
    }

    /**
     * 获取用户角色编码
     *
     * @param username 用户名称
     * @return 角色编码
     */
    @Nonnull
    public Set<String> getRoleCodeByUsername(String username) {
        Set<String> res = this.baseMapper.getRoleCodeByUsername(username);
        return res == null ? Collections.emptySet() : res;
    }

    /**
     * 获取全部的角色编码
     *
     * @return .
     */
    public Set<String> allRoleCode() {
        Set<String> res = this.baseMapper.allRoleCode();
        return res == null ? Collections.emptySet() : res;
    }


    /**
     * 根据用户ID 获取对应的角色ID集合
     *
     * @param userId 角色ID
     * @return 角色ID集合
     */
    public Set<String> queryRoleIdsByUserId(String userId) {
        Set<String> res = baseMapper.queryUserRole(userId);
        return res == null ? Collections.emptySet() : res;
    }
    /*===================================================*/

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    public BasePage<UserVO> queryPage(UserQuery query) {
        Page<UserVO> page = new Page<>(query.getCurrent(), query.getSize());
        List<UserVO> vos = this.baseMapper.queryPage(page, query);
        return new BasePage<>(page.getCurrent(), page.getSize(), page.getTotal(), vos);
    }

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    public List<UserVO> queryList(UserQuery query) {
        return this.baseMapper.queryPage(null, query);
    }

    /**
     * 帐号是否存在
     *
     * @param username .
     * @return .
     */
    public R<Boolean> hashUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getUsername, username);
        return R.OK(this.count(queryWrapper) > 0);
    }

}
