package com.hb0730.sys.system.repository;

import com.hb0730.core.repository.BaseRepository;
import com.hb0730.sys.system.convert.SysUserConvert;
import com.hb0730.sys.system.model.entity.SysUser;
import com.hb0730.sys.system.model.request.user.SysUserCreateRequest;
import com.hb0730.sys.system.model.request.user.SysUserQueryRequest;
import com.hb0730.sys.system.model.request.user.SysUserUpdateRequest;
import com.hb0730.sys.system.model.vo.SysUserRoleVO;
import com.hb0730.sys.system.model.vo.SysUserVO;
import com.hb0730.sys.system.repository.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
@Service
@Slf4j
public class SysUserRepository extends BaseRepository<String, SysUserQueryRequest, SysUserVO, SysUser,
        SysUserCreateRequest, SysUserUpdateRequest, SysUserMapper, SysUserConvert> {

    /**
     * email是否存在
     *
     * @param email 邮箱
     * @return true 存在
     */
    public boolean emailExists(String email) {
        return emailExists(email, null);
    }

    /**
     * 邮箱是否存在
     *
     * @param email 邮箱
     * @param id    需要排除的ID
     * @return true 存在
     */
    public boolean emailExists(String email, String id) {
        return lambdaQuery()
                .eq(SysUser::getEmail, email)
                .ne(id != null, SysUser::getId, id)
                .exists();
    }

    /**
     * hashEmail是否存在
     *
     * @param hashEmail 邮箱
     * @return true 存在
     */
    public boolean hasEmailExists(String hashEmail) {
        return lambdaQuery().eq(SysUser::getHashEmail, hashEmail).exists();
    }

    /**
     * hashEmail是否存在
     *
     * @param hashEmail 邮箱
     * @param id        需要排除的ID
     * @return true 存在
     */
    public boolean hashEmailExists(String hashEmail, String id) {
        return lambdaQuery()
                .eq(SysUser::getHashEmail, hashEmail)
                .ne(id != null, SysUser::getId, id)
                .exists();
    }


    /**
     * 手机号是否存在
     *
     * @param phone 手机号
     * @return true 存在
     */
    public boolean phoneExists(String phone) {
        return phoneExists(phone, null);
    }

    /**
     * 手机号是否存在
     *
     * @param phone 手机号
     * @param id    需要排除的ID
     * @return true 存在
     */
    public boolean phoneExists(String phone, String id) {
        return lambdaQuery()
                .eq(SysUser::getPhone, phone)
                .ne(id != null, SysUser::getId, id)
                .exists();
    }

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @return true 存在
     */
    public boolean usernameExists(String username) {
        return usernameExists(username, null);
    }

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @param id       需要排除的ID
     * @return true 存在
     */
    public boolean usernameExists(String username, String id) {
        return lambdaQuery()
                .eq(SysUser::getUsername, username)
                .ne(id != null, SysUser::getId, id)
                .exists();
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    public SysUser findByUsername(String username) {
        return baseMapper.of(
                query -> query.eq(SysUser::getUsername, username)
        ).one();
    }

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return 用户信息
     */
    public SysUser findByPhone(String phone) {
        return baseMapper.of(
                query -> query.eq(SysUser::getPhone, phone)
        ).one();
    }

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户信息
     */
    public SysUser findByEmail(String email) {
        return baseMapper.of(
                query -> query.eq(SysUser::getEmail, email)
        ).one();
    }

    /**
     * 根据userId查询已绑定的角色
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    public List<SysUserRoleVO> findRolesByUserId(String userId) {
        return baseMapper.findRoles(null, userId);
    }
}
