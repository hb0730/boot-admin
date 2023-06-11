package com.hb0730.boot.admin.modules.sys.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.base.util.AesEncryptUtil;
import com.hb0730.boot.admin.base.util.PasswordUtil;
import com.hb0730.boot.admin.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysUserMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysUser;
import com.hb0730.boot.admin.modules.sys.system.model.query.UserQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.UserVO;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

    /**
     * 根据ID获取详情
     *
     * @param id .
     * @return .
     */
    public R<UserVO> detail(String id) {
        SysUser user = getById(id);
        UserVO res = BeanUtil.toBean(user, UserVO.class);
        res.setPassword(null);
        return R.OK(res);
    }

    /**
     * 根据用户名获取详情
     *
     * @param username .
     * @return .
     */
    public R<UserVO> detailByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getUsername, username);
        SysUser user = getOne(queryWrapper);
        UserVO res = BeanUtil.toBean(user, UserVO.class);
        res.setPassword(null);
        return R.OK(res);
    }

    /**
     * 根据用户ID获取角色ID集合
     *
     * @param id .
     * @return .
     */
    public R<List<String>> getRoleIdsByUserId(String id) {
        Set<String> roleIds = this.queryRoleIdsByUserId(id);
        return R.OK(new ArrayList<>(roleIds));
    }

    @Transactional(rollbackFor = Exception.class)
    public R<UserVO> save(UserVO vo) {
        String username = vo.getUsername();
        if (this.hashUsername(username).getResult()) {
            return R.NG("帐号已存在");
        }
        String password = vo.getPassword();
        if (StrUtil.isBlank(password)) {
            return R.NG("密码不能为空");
        }
        String orgId = vo.getOrgId();
        if (StrUtil.isBlank(orgId)) {
            return R.NG("组织不能为空");
        }
        String newPassword = PasswordUtil.encoder(vo.getPassword());
        SysUser user = BeanUtil.toBean(vo, SysUser.class);
        user.setPassword(newPassword);
        if (StrUtil.isNotBlank(vo.getPhone())) {
            user.setPhone(AesEncryptUtil.encrypt(vo.getPhone()));
        }
        save(user);
        List<String> roleIds = vo.getRoleIds();
        if (CollectionUtil.isNotEmpty(roleIds)) {
            baseMapper.saveUserRole(user.getId(), new HashSet<>(roleIds));
        }
        return R.OK(vo);
    }

    /**
     * 更新用户
     *
     * @param id .
     * @param vo .
     * @return .
     */
    public R<UserVO> updateById(String id, UserVO vo) {
        SysUser user = getById(id);
        if (null == user) {
            return R.NG("用户不存在");
        }
        BeanUtil.copyProperties(vo, user);
        if (StrUtil.isNotBlank(vo.getPhone())) {
            user.setPhone(AesEncryptUtil.encrypt(vo.getPhone()));
        }
        updateById(user);
        List<String> roleIds = vo.getRoleIds();
        baseMapper.delUserRoleByUserid(user.getId());
        if (CollectionUtil.isNotEmpty(roleIds)) {
            baseMapper.saveUserRole(user.getId(), new HashSet<>(roleIds));
        }
        return R.OK(vo);
    }

    @Transactional(rollbackFor = Exception.class)
    public R<String> deleteByIds(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return R.NG("参数不能为空");
        }
        removeByIds(ids);
        baseMapper.delUserRoleByUserids(ids);
        return R.OK();
    }

    /**
     * 重置密码
     *
     * @param username .
     * @param passwd   .
     * @return .
     */
    public R<String> resetPassword(@Nonnull String username, @Nonnull String passwd) {
        String newPassword = PasswordUtil.encoder(passwd);
        this.baseMapper.resetPassword(username, newPassword);
        return R.OK();
    }

    /**
     * 修改密码
     *
     * @param username  .
     * @param oldPasswd .
     * @param newPasswd .
     * @return .
     */
    public R<String> changePasswd(@Nonnull String username, @Nonnull String oldPasswd, @Nonnull String newPasswd) {
        SysUser user = baseMapper.getByUsername(username);
        if (null == user) {
            return R.NG("用户不存在");
        }
        if (!PasswordUtil.matches(oldPasswd, user.getPassword())) {
            return R.NG("原密码错误");
        }
        String newPassword = PasswordUtil.encoder(newPasswd);
        this.baseMapper.resetPassword(username, newPassword);
        return R.OK();
    }

}
