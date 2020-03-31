package com.hb0730.boot.admin.project.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.commons.web.utils.SecurityUtils;
import com.hb0730.boot.admin.project.user.handle.RolePostPermissionHandle;
import com.hb0730.boot.admin.project.user.mapper.SystemUserMapper;
import com.hb0730.boot.admin.project.user.model.dto.LoginUserDTO;
import com.hb0730.boot.admin.project.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.project.user.model.vo.UserVO;
import com.hb0730.boot.admin.project.user.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统用户  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-24
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUserEntity> implements ISystemUserService {
    @Autowired
    private RolePostPermissionHandle postPermissionHandle;

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
        String encryptPassword = SecurityUtils.encryptPassword(SystemConstants.DEFAULT_PASSWORD);
        entity.setPassword(encryptPassword);
        return super.updateById(entity);
    }


    /**
     * 不为空
     *
     * @param vo 用户信息
     */
    private void isNotNull(UserVO vo, Integer isUpdate) {
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
}
