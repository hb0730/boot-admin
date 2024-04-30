package com.hb0730.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.common.api.JsfPage;
import com.hb0730.mybatis.core.service.BaseService;
import com.hb0730.query.mybatis.plus.QueryHelper;
import com.hb0730.sys.domain.dto.RoleSmallDto;
import com.hb0730.sys.domain.dto.UserDto;
import com.hb0730.sys.domain.dto.UserRestPwdDto;
import com.hb0730.sys.domain.dto.UserSaveDto;
import com.hb0730.sys.domain.entity.SysUser;
import com.hb0730.sys.domain.query.UserQuery;
import com.hb0730.sys.mapper.SysUserMapper;
import com.hb0730.sys.service.mapstruct.SysUserMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserService extends BaseService<SysUserMapper, SysUser> {
    private final SysUserMapstruct mapstruct;

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    public SysUser getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return 用户
     */
    public SysUser getById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 修改最后登录时间
     *
     * @param username 用户名
     */
    public void changeLastLoginTimeByUsername(String username) {
        baseMapper.changeLastLoginTimeByUsername(username);
    }


    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @param id       需要排除的id
     * @return 是否存在
     */
    public Boolean existsByUsername(String username, String id) {
        if (StrUtil.isBlank(id)) {
            return baseMapper.existsByUsername(username);
        }
        return baseMapper.existsByUsernameAndIdNot(username, id);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<UserDto> page(UserQuery query) {
        QueryWrapper<SysUser> queryWrapper = QueryHelper.ofBean(query);
        IPage<SysUser> page = QueryHelper.toPage(query);
        page = baseMapper.selectPage(page, queryWrapper);

        List<UserDto> res = mapstruct.toDtoList(page.getRecords());
        return QueryHelper.toJsfPage(page, res);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    public List<UserDto> list(UserQuery query) {
        QueryWrapper<SysUser> queryWrapper = QueryHelper.ofBean(query);
        List<SysUser> list = baseMapper.selectList(queryWrapper);
        return mapstruct.toDtoList(list);
    }

    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return 角色
     */
    public List<RoleSmallDto> getRoles(String userId) {
        return baseMapper.getRolesByUserId(userId);
    }

    /**
     * 保存
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(UserSaveDto dto) {
        SysUser entity = mapstruct.saveDtoToEntity(dto);
        baseMapper.insert(entity);
        if (CollectionUtil.isNotEmpty(dto.getRoles())) {
            baseMapper.saveUserRole(entity.getId(), dto.getRoles().stream().map(RoleSmallDto::getId).toList());
        }
    }

    /**
     * 更新
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(UserSaveDto dto) {
        SysUser sysUser = mapstruct.saveDtoToEntity(dto);
        if (StrUtil.isBlank(sysUser.getId())) {
            throw new ServiceException("id不能为空");
        }
        SysUser entity = getById(sysUser.getId());
        if (entity == null) {
            throw new ServiceException("数据不存在");
        }

        // 账号不能修改，密码单独修改
        sysUser.setUsername(null);
        sysUser.setPassword(null);
        BeanUtil.copyProperties(sysUser, entity, CopyOptions.create().setIgnoreNullValue(true));
        baseMapper.updateById(entity);

        // 更新用户角色
        // 删除原有角色
        baseMapper.deleteUserRoleByUserId(entity.getId());
        // 保存新角色
        if (CollectionUtil.isNotEmpty(dto.getRoles())) {
            baseMapper.saveUserRole(entity.getId(), dto.getRoles().stream().map(RoleSmallDto::getId).toList());
        }
    }

    /**
     * 删除
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        baseMapper.deleteById(id);
        // 删除用户角色
        baseMapper.deleteUserRoleByUserId(id);
        // 删除用户部门
        // 删除用户岗位
    }

    /**
     * 重置密码
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(UserRestPwdDto dto) {
        if (dto.getId() == null) {
            throw new ServiceException("id不能为空");
        }
        if (StrUtil.isBlank(dto.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        changePassword(dto.getId(), dto.getPassword());
    }

    /**
     * 修改密码
     *
     * @param id       id
     * @param password 密码
     */
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String id, String password) {
        SysUser entity = getById(id);
        if (entity == null) {
            throw new ServiceException("数据不存在");
        }
        entity.setPassword(password);
        baseMapper.updateById(entity);
    }


}
