package com.hb0730.boot.admin.project.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.utils.DictUtils;
import com.hb0730.boot.admin.commons.utils.PasswordSecurityUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.exceptions.BusinessException;
import com.hb0730.boot.admin.project.system.dept.service.IDeptService;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.permission.service.IPermissionService;
import com.hb0730.boot.admin.project.system.post.service.IPostService;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleEntity;
import com.hb0730.boot.admin.project.system.role.service.IRolePermissionService;
import com.hb0730.boot.admin.project.system.role.service.IRoleService;
import com.hb0730.boot.admin.project.system.user.mapper.IUserInfoMapper;
import com.hb0730.boot.admin.project.system.user.model.dto.UserDTO;
import com.hb0730.boot.admin.project.system.user.model.dto.UserInfoDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.UserAccountEntity;
import com.hb0730.boot.admin.project.system.user.model.entity.UserInfoEntity;
import com.hb0730.boot.admin.project.system.user.model.entity.UserPostEntity;
import com.hb0730.boot.admin.project.system.user.model.entity.UserRoleEntity;
import com.hb0730.boot.admin.project.system.user.model.query.UserInfoParams;
import com.hb0730.boot.admin.project.system.user.service.IUserAccountService;
import com.hb0730.boot.admin.project.system.user.service.IUserInfoService;
import com.hb0730.boot.admin.project.system.user.service.IUserPostService;
import com.hb0730.boot.admin.project.system.user.service.IUserRoleService;
import com.hb0730.boot.admin.security.utils.SecurityUtils;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.spring.BeanUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static com.hb0730.boot.admin.commons.constant.DictConstant.SysConstants.SysEntryConstants.DEFAULT_PASSWORD;
import static com.hb0730.boot.admin.commons.constant.DictConstant.SysConstants.TYPE;

/**
 * 用户信息  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends SuperBaseServiceImpl<Long, UserInfoParams, UserInfoDTO, UserInfoEntity, IUserInfoMapper> implements IUserInfoService {
    @Getter
    private final IUserAccountService accountService;
    @Getter
    private final IDeptService deptService;
    @Getter
    private final IPostService postService;
    private final IUserPostService userPostService;
    private final IUserRoleService userRoleService;
    private final IRoleService roleService;
    private final IRolePermissionService rolePermissionService;
    private final IPermissionService permissionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        // 删除相关信息
        accountService.removeByUserId(id);
        userPostService.removeByUserId(id);
        userRoleService.removeByUserId(id);
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        // 删除相关信息
        accountService.removeByUserIds(ids);
        userPostService.removeByUserIds(ids);
        userRoleService.removeByUserIds(ids);
        return super.removeByIds(ids);
    }

    @Override
    public UserDTO loadUserByUsername(String username) {
        UserAccountEntity accountEntity = accountService.findUserAccountByUsername(username);
        if (null == accountEntity) {
            return null;
        }
        Long userId = accountEntity.getUserId();
        UserInfoEntity entity = super.getById(userId);
        UserDTO user = BeanUtils.transformFrom(entity, UserDTO.class);
        assert user != null;
        user.setUsername(accountEntity.getUsername());
        user.setPassword(accountEntity.getPassword());
        //用户角色
        Collection<Long> roleIds = userRoleService.findRoleByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return user;
        }
        List<RoleEntity> roles = roleService.findEnabledRoleByIds(roleIds);
        if (CollectionUtils.isEmpty(roles)) {
            return user;
        }
        Map<Long, String> roleMap = roles.parallelStream().collect(Collectors.toMap(RoleEntity::getId, RoleEntity::getCode));
        user.setRoleIds(roleMap.keySet());
        user.setRole(roleMap.values());
        // 权限
        Map<Long, List<Long>> permission = rolePermissionService.findPermissionIdByRoleId(roleIds);
        if (CollectionUtils.isEmpty(permission)) {
            return user;
        }
        Set<Long> permissionIds = permission.values().stream().flatMap(List::stream).collect(Collectors.toSet());
        List<PermissionEntity> permissionEntities = permissionService.findEnabledPermissionByIds(permissionIds);
        if (CollectionUtils.isEmpty(permissionEntities)) {
            return user;
        }
        Map<Long, String> permissionMap = permissionEntities.parallelStream().collect(Collectors.toMap(PermissionEntity::getId, PermissionEntity::getPermission));
        user.setPermission(permissionMap.values());
        user.setPermissionIds(permissionMap.keySet());
        // 岗位
        List<Long> postIds = userPostService.findPostIdByUserIds(Collections.singletonList(userId));
        user.setPostIds(postIds);
        return user;
    }

    @Override
    public boolean restPassword(Long id) {

        UserInfoEntity entity = super.getById(id);
        if (entity.getIsAdmin() == 1) {
            return false;
        }
        // 默认密码
        String password = DictUtils.getEntryValue(TYPE, DEFAULT_PASSWORD);
        if (StringUtils.isBlank(password)) {
            password = "123456";
        }
        return accountService.updatePassword(id, password);
    }


    @Override
    public Page<UserInfoDTO> page(@Nonnull UserInfoParams params) {
        QueryWrapper<UserInfoEntity> query = this.query(params);
        Page<UserInfoEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        Page<UserInfoDTO> pageInfo = QueryWrapperUtils.pageToBean(page, UserInfoDTO.class);
        List<UserInfoDTO> records = pageInfo.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            fillRoleAndPost(records);
            fillAccount(records);
        }
        return pageInfo;
    }

    @Override
    public List<UserInfoDTO> list(@Nonnull UserInfoParams params) {
        List<UserInfoDTO> list = super.list(params);
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        fillRoleAndPost(list);
        fillAccount(list);
        return list;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(@Nonnull UserInfoDTO dto) {
        UserInfoEntity entity = dto.convertTo();
        // 1.账号是否存在
        String username = dto.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new BusinessException("用户账号不为空");
        }
        UserAccountEntity account = accountService.findUserAccountByUsername(username);
        if (Objects.nonNull(account)) {
            throw new BusinessException("用户账号已存在,请重新设置");
        }
        boolean result = super.save(entity);
        //保存 用户账号
        account = new UserAccountEntity();
        account.setUsername(username);
        // 默认密码
        String password = DictUtils.getEntryValue(TYPE, DEFAULT_PASSWORD);
        if (StringUtils.isBlank(password)) {
            password = "123456";
        }
        account.setPassword(PasswordSecurityUtils.encode(SecurityUtils.getPasswordEncoder(), password));

        account.setUserId(entity.getId());
        accountService.save(account);
        // 保存 用户角色
        Collection<Long> roleIds = dto.getRoleIds();
        if (!CollectionUtils.isEmpty(roleIds)) {
            userRoleService.updateUserRole(entity.getId(), roleIds);
        }
        //保存 用户岗位
        Collection<Long> postIds = dto.getPostIds();
        if (!CollectionUtils.isEmpty(postIds)) {
            userPostService.updateUserPost(entity.getId(), postIds);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(@Nonnull Long id, @Nonnull UserInfoDTO dto) {
        UserInfoEntity entity = dto.convertTo();
        entity.setId(id);
        boolean result = this.updateById(entity);
        // 更新角色
        userRoleService.updateUserRole(id, dto.getRoleIds());
        //更新岗位
        userPostService.updateUserPost(id, dto.getPostIds());
        //不更新账号信息

        return result;
    }

    @Override
    public boolean updateById(UserInfoEntity entity) {
        UserInfoEntity e1 = super.getById(entity.getId());
        BeanUtils.updateProperties(entity, e1);
        return super.updateById(e1);
    }

    /**
     * 填充角色和岗位id
     *
     * @param list 用户信息
     */
    private void fillRoleAndPost(List<UserInfoDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Set<Long> ids = list.parallelStream().map(UserInfoDTO::getId).collect(Collectors.toSet());
        // 角色
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = Wrappers.lambdaQuery(UserRoleEntity.class).in(UserRoleEntity::getUserId, ids);
        List<UserRoleEntity> userRoleEntities = userRoleService.list(queryWrapper);
        Map<Long, List<Long>> roleMap = new HashMap<>(list.size() * 10);
        Map<Long, List<Long>> postMap = new HashMap<>(list.size() * 10);
        if (!CollectionUtils.isEmpty(userRoleEntities)) {
            Map<Long, List<Long>> map = userRoleEntities
                    .parallelStream()
                    .collect(
                            Collectors.groupingBy(
                                    UserRoleEntity::getUserId,
                                    Collectors.mapping(UserRoleEntity::getRoleId, Collectors.toList())
                            )
                    );
            roleMap.putAll(map);

        }
        // 岗位
        LambdaQueryWrapper<UserPostEntity> q1 = Wrappers.lambdaQuery(UserPostEntity.class).in(UserPostEntity::getUserId, ids);
        List<UserPostEntity> userPostEntities = userPostService.list(q1);
        if (!CollectionUtils.isEmpty(userPostEntities)) {
            Map<Long, List<Long>> map = userPostEntities
                    .parallelStream()
                    .collect(
                            Collectors.groupingBy(
                                    UserPostEntity::getUserId,
                                    Collectors.mapping(UserPostEntity::getPostId, Collectors.toList())
                            )
                    );
            postMap.putAll(map);
        }
        list.forEach(v -> {
            v.setRoleIds(roleMap.get(v.getId()));
            v.setPostIds(postMap.get(v.getId()));
        });
    }

    /**
     * 填充账号信息
     *
     * @param list 用户id
     */
    private void fillAccount(List<UserInfoDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Set<Long> ids = list.parallelStream().map(UserInfoDTO::getId).collect(Collectors.toSet());
        LambdaQueryWrapper<UserAccountEntity> queryWrapper = Wrappers.lambdaQuery(UserAccountEntity.class).in(UserAccountEntity::getUserId, ids);
        List<UserAccountEntity> entities = accountService.list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }
        Map<Long, List<String>> map = entities
                .parallelStream()
                .collect(
                        Collectors.groupingBy(
                                UserAccountEntity::getUserId,
                                Collectors.mapping(UserAccountEntity::getUsername, Collectors.toList())
                        )
                );
        list.forEach(v -> v.setUsername(map.get(v.getId()) == null ? null : map.get(v.getId()).get(0)));
    }

    @Override
    @Nonnull
    public QueryWrapper<UserInfoEntity> query(@Nonnull UserInfoParams params) {
        QueryWrapper<UserInfoEntity> query = QueryWrapperUtils.getQuery(params);
        if (Objects.nonNull(params.getDeptId())) {
            query.eq(UserInfoEntity.DEPT_ID, params.getDeptId());
        }
        if (StringUtils.isNotBlank(params.getNickName())) {
            query.like(UserInfoEntity.NICK_NAME, params.getNickName());
        }
        if (StringUtils.isNotBlank(params.getUsername())) {
            UserAccountEntity entity = accountService.findUserAccountByUsername(params.getUsername());
            if (Objects.nonNull(entity)) {
                query.eq(UserInfoEntity.ID, entity.getUserId());
            } else {
                query.eq(UserInfoEntity.ID, null);
            }
        }
        if (Objects.nonNull(params.getIsEnabled())) {
            query.eq(UserInfoEntity.IS_ENABLED, params.getIsEnabled());
        }
        return query;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getThis() {
        return (T) this;
    }
}
