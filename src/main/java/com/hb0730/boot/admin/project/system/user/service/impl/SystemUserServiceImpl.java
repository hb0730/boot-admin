package com.hb0730.boot.admin.project.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.domain.service.BaseServiceImpl;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.cache.DictCacheUtils;
import com.hb0730.boot.admin.commons.utils.spring.SecurityUtils;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.exception.file.FileUploadException;
import com.hb0730.boot.admin.project.system.org.model.entity.SystemOrgEntity;
import com.hb0730.boot.admin.project.system.org.service.ISystemOrgService;
import com.hb0730.boot.admin.project.system.user.handle.RolePostPermissionHandle;
import com.hb0730.boot.admin.project.system.user.mapper.SystemUserMapper;
import com.hb0730.boot.admin.project.system.user.model.dto.LoginUserDTO;
import com.hb0730.boot.admin.project.system.user.model.dto.UserExcelDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.project.system.user.model.vo.SystemUserVO;
import com.hb0730.boot.admin.project.system.user.model.vo.UserParams;
import com.hb0730.boot.admin.project.system.user.model.vo.UserVO;
import com.hb0730.boot.admin.project.system.user.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-24
 */
@Service
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUserMapper, SystemUserEntity> implements ISystemUserService {
    @Autowired
    private RolePostPermissionHandle postPermissionHandle;
    @Autowired
    private ISystemOrgService systemOrgService;

    @Override
    public Page<SystemUserVO> page(@NonNull UserParams params) {
        QueryWrapper<SystemUserEntity> query = getQuery(params);
        @NotNull Page<SystemUserEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, SystemUserVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(@NonNull UserVO vo) {
        isNotNull(vo, SystemConstants.NO_UPDATE);
        //密码加密
        String encryptPassword = SecurityUtils.encryptPassword(vo.getPassword());
        vo.setPassword(encryptPassword);
        //更新用户角色和用户岗位
        List<Long> postIds = vo.getPostId();
        List<Long> roleIds = vo.getRoleId();
        SystemUserEntity entity = BeanUtils.transformFrom(vo, SystemUserEntity.class);
        super.save(entity);
        assert entity != null;
        Long id = entity.getId();
        if (Objects.isNull(id)) {
            throw new BaseException("用户id为空，保存失败");
        }
        return postPermissionHandle.getUserRolePostHandle().updateUserRoleAndUserPost(id, roleIds, postIds);
    }

    @Override
    public UserVO getUserInfo(@NotNull Long userId) {
        SystemUserEntity entity = super.getById(userId);
        UserVO userVO = BeanUtils.transformFrom(entity, UserVO.class);
        postPermissionHandle.getUserRolePostHandle().getRoleIdAndPostIdByUser(userVO);
        return userVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(@NonNull UserVO vo, @NonNull Long userId) {
        isNotNull(vo, SystemConstants.IS_UPDATE);
        vo.setPassword(null);
        vo.setUsername(null);
        //更新用户角色和用户岗位
        List<Long> postIds = vo.getPostId();
        List<Long> roleIds = vo.getRoleId();
        SystemUserEntity entity = BeanUtils.transformFrom(vo, SystemUserEntity.class);
        assert entity != null;
        entity.setId(userId);
        super.updateById(entity);
        return postPermissionHandle.getUserRolePostHandle().updateUserRoleAndUserPost(userId, roleIds, postIds);
    }

    @Override
    public LoginUserDTO loadUserByUsername(@NonNull String username) {
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemUserEntity.USERNAME, username);
        SystemUserEntity userEntity = super.getOne(queryWrapper);
        if (Objects.isNull(userEntity)) {
            throw new BaseException("用户账号不存在");
        }
        LoginUserDTO userDTO = BeanUtils.transformFrom(userEntity, LoginUserDTO.class);
        assert userDTO != null;
        postPermissionHandle.getRolePostPermissionByLoginUser(userDTO);
        return userDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resetPassword(@NonNull Long id) {
        SystemUserEntity entity = super.getById(id);
        Object value = DictCacheUtils.getValue(SystemConstants.REDIS_CACHE, SystemConstants.DEFAULT_CACHE_PASSWORD);
        String password = value == null ? SystemConstants.DEFAULT_PASSWORD : String.valueOf(value);
        String encryptPassword = SecurityUtils.encryptPassword(password);
        entity.setPassword(encryptPassword);
        return super.updateById(entity);
    }

    @Override
    public List<UserExcelDTO> export(UserParams params) {
        QueryWrapper<SystemUserEntity> queryWrapper = query(params);
        List<SystemUserEntity> entities = super.list(queryWrapper);
        List<UserExcelDTO> dtos = BeanUtils.transformFromInBatch(entities, UserExcelDTO.class);
        if (!CollectionUtils.isEmpty(dtos)) {
            Set<Long> ids = dtos.parallelStream().map(UserExcelDTO::getDeptId).collect(Collectors.toSet());
            QueryWrapper<SystemOrgEntity> q1 = new QueryWrapper<>();
            q1.in(SystemOrgEntity.ID, ids);
            q1.select(SystemOrgEntity.ID, SystemOrgEntity.NUMBER, SystemOrgEntity.NAME);
            List<SystemOrgEntity> orgEntities = systemOrgService.list(q1);
            Map<Long, SystemOrgEntity> orgEntityMap = orgEntities.parallelStream().collect(Collectors.toMap(SystemOrgEntity::getId, Function.identity()));
            return dtos.parallelStream().peek((exportDto) -> {
                SystemOrgEntity orgEntity = orgEntityMap.get(exportDto.getDeptId());
                exportDto.setDeptName(orgEntity.getName());
                exportDto.setDeptNumber(orgEntity.getNumber());
            }).collect(Collectors.toList());

        }
        return null;
    }


    @Override
    public boolean upload(Collection<UserExcelDTO> list) {
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> usernames = list.parallelStream().map(UserExcelDTO::getUsername).collect(Collectors.toSet());
            QueryWrapper<SystemUserEntity> q1 = new QueryWrapper<>();
            q1.in(SystemUserEntity.USERNAME, usernames);
            int count = super.count(q1);
            if (count > 0) {
                throw new FileUploadException("用户导入失败，用户账号重复");
            }
            Set<String> deptNumbers = list.parallelStream().map(UserExcelDTO::getDeptNumber).collect(Collectors.toSet());
            QueryWrapper<SystemOrgEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.in(SystemOrgEntity.NUMBER, deptNumbers);
            queryWrapper.select(SystemOrgEntity.ID, SystemOrgEntity.NUMBER);
            List<SystemOrgEntity> orgEntities = systemOrgService.list(queryWrapper);
            Map<String, Long> orgEntityMaps = orgEntities.parallelStream().collect(Collectors.toMap(SystemOrgEntity::getNumber, SystemOrgEntity::getId));
            List<UserExcelDTO> excels = list.parallelStream().peek(excelDto -> excelDto.setDeptId(orgEntityMaps.get(excelDto.getDeptNumber()))).collect(Collectors.toList());
            List<SystemUserEntity> entities = BeanUtils.transformFromInBatch(excels, SystemUserEntity.class);
            entities.forEach(entity -> {
                entity.setPassword(SecurityUtils.encryptPassword(entity.getPassword()));
                UserVO userVO = BeanUtils.transformFrom(entity, UserVO.class);
                assert userVO != null;
                isNotNull(userVO, SystemConstants.IS_UPDATE);
            });
            return super.saveBatch(entities);
        }
        return false;
    }

    /**
     * 不为空
     *
     * @param vo 用户信息
     */
    private void isNotNull(SystemUserVO vo, Integer isUpdate) {
        String username = vo.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new BaseException("用户账号为空");
        }
        if (!Objects.equals(isUpdate, SystemConstants.IS_UPDATE)) {
            QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            List<SystemUserEntity> entities = list(queryWrapper);
            if (!CollectionUtils.isEmpty(entities)) {
                throw new BaseException("用户账号已存在");
            }
            String password = vo.getPassword();
            if (StringUtils.isBlank(password)) {
                throw new BaseException("用户密码为空");
            }
        }

        Long deptId = vo.getDeptId();
        if (Objects.isNull(deptId)) {
            throw new BaseException("用户组织不为空");
        }
    }

    private QueryWrapper<SystemUserEntity> getQuery(UserParams params) {
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(params)) {
            Long deptId = params.getDeptId();
            if (!Objects.isNull(deptId)) {
                queryWrapper.eq(SystemUserEntity.DEPTID, deptId);
            }
            if (StringUtils.isNotBlank(params.getNickName())) {
                queryWrapper.eq(SystemUserEntity.NICK_NAME, params.getNickName());
            }
            if (StringUtils.isNotBlank(params.getUsername())) {
                queryWrapper.eq(SystemUserEntity.USERNAME, params.getUsername());
            }
            if (Objects.nonNull(params.getIsEnabled())) {
                queryWrapper.eq(SystemUserEntity.IS_ENABLED, params.getIsEnabled());
            }
        }
        return queryWrapper;
    }

    @Override
    public @NotNull QueryWrapper<SystemUserEntity> query(@NotNull UserParams params) {
        @NotNull QueryWrapper<SystemUserEntity> query = QueryWrapperUtils.getQuery(params);
        if (!Objects.nonNull(params.getDeptId())) {
            query.eq(SystemUserEntity.DEPTID, params.getDeptId());
        }
        if (StringUtils.isNotBlank(params.getNickName())) {
            query.eq(SystemUserEntity.NICK_NAME, params.getNickName());
        }
        if (StringUtils.isNotBlank(params.getUsername())) {
            query.eq(SystemUserEntity.USERNAME, params.getUsername());
        }
        if (Objects.nonNull(params.getIsEnabled())) {
            query.eq(SystemUserEntity.IS_ENABLED, params.getIsEnabled());
        }
        return query;
    }
}
