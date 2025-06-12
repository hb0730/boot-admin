package com.hb0730.sys.system.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.hb0730.base.R;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.base.utils.DesensitizeUtil;
import com.hb0730.base.utils.PasswdUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.core.service.BaseService;
import com.hb0730.sys.system.model.dto.RestPasswordDTO;
import com.hb0730.sys.system.model.entity.SysUser;
import com.hb0730.sys.system.model.entity.SysUserRole;
import com.hb0730.sys.system.model.request.user.SysUserCreateRequest;
import com.hb0730.sys.system.model.request.user.SysUserQueryRequest;
import com.hb0730.sys.system.model.request.user.SysUserRoleUpdateRequest;
import com.hb0730.sys.system.model.request.user.SysUserUpdateRequest;
import com.hb0730.sys.system.model.vo.SysUserRoleVO;
import com.hb0730.sys.system.model.vo.SysUserVO;
import com.hb0730.sys.system.repository.SysUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserService extends BaseService<String, SysUserQueryRequest, SysUserVO, SysUser,
        SysUserCreateRequest, SysUserUpdateRequest, SysUserRepository> {
    private final SysUserRoleService userRoleService;


    /**
     * 邮箱是否存在
     *
     * @param email 邮箱
     * @return 是否存在
     */
    public boolean existEmail(String email) {
        return repository.emailExists(email);
    }

    /**
     * 手机号是否存在
     *
     * @param phone 手机号
     * @return 是否存在
     */
    public boolean existPhone(String phone) {
        return repository.phoneExists(phone);
    }

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户
     */
    public SysUser findByUsername(String username) {
        return repository.findByUsername(username);
    }

    /**
     * 根据手机号查询
     *
     * @param phone 手机号
     * @return 用户
     */
    public SysUser findByPhone(String phone) {
        return repository.findByPhone(phone);
    }

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return 用户
     */
    public SysUser findByEmail(String email) {
        return repository.findByEmail(email);
    }

    /**
     * 更新最后登录时间
     *
     * @param id 用户ID
     */
    public void updateLastLoginTime(String id) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setLastLoginTime(new Date());
        repository.updateById(user);
    }

    /**
     * 更新邮箱
     *
     * @param id    用户ID
     * @param email 邮箱
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String id, String email) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setEmail(email);
        repository.updateById(user);
    }

    /**
     * 更新手机号
     *
     * @param id    用户ID
     * @param phone 手机号
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePhone(String id, String phone) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setPhone(phone);
        repository.updateById(user);
    }

    /**
     * 重置密码
     *
     * @param dto 重置密码
     * @return 是否成功
     */
    public R<String> resetPassword(RestPasswordDTO dto) {
        SysUser user = null;
        String userId = dto.getUserId();
        if (StrUtil.isNotBlank(userId)) {
            user = getById(userId);
        }
        if (user == null && StrUtil.isNotBlank(dto.getUsername())) {
            user = findByUsername(dto.getUsername());
        }
        if (user == null) {
            return R.NG("用户不存在");
        }
        // 校验旧密码
        if (!PasswdUtil.matches(dto.getOldPassword(), user.getSalt(), user.getPassword())) {
            return R.NG("旧密码错误");
        }
        // 校验新密码
        if (!PasswdUtil.checkPwd(dto.getNewPassword())) {
            return R.NG("新密码不满足强密码要求");
        }
        // 重新生成盐
        String salt = PasswdUtil.generateSalt();
        // 生成新密码
        String password = PasswdUtil.encrypt(dto.getNewPassword(), salt);
        user.setPassword(password);
        user.setSalt(salt);
        repository.updateById(user);
        // 更新密码
        return R.OK("重置密码成功");
    }

    /**
     * 重置密码
     *
     * @param id       用户ID
     * @param password 密码
     * @return 是否成功
     */
    public R<String> resetPassword(String id, String password) {
        SysUser user = getById(id);
        if (user == null) {
            return R.NG("用户不存在");
        }
        boolean checkPwd = PasswdUtil.checkPwd(password);
        if (!checkPwd) {
            return R.NG("新密码不满足强密码要求");
        }
        // 重新生成盐
        String salt = PasswdUtil.generateSalt();
        // 生成新密码
        String newPassword = PasswdUtil.encrypt(password, salt);
        user.setPassword(newPassword);
        user.setSalt(salt);
        repository.updateById(user);
        // 更新密码
        return R.OK("重置密码成功");
    }

    /**
     * 校验是否存在
     *
     * @param type  类型
     * @param value 值
     * @param id    id
     * @return 是否存在
     */
    public R<String> hasExists(String type, String value, String id) {
        return switch (type) {
            case "user_name" -> hasExistUsername(value, id);
            case "phone" -> hasExistHashPhone(value, id);
            case "email" -> hasExistHashEmail(value, id);
            default -> R.NG("校验类型错误");
        };
    }

    /**
     * 校验用户名是否存在
     *
     * @param username 用户名
     * @param id       id
     * @return 是否存在
     */
    public R<String> hasExistUsername(String username, String id) {
        boolean present = repository.usernameExists(username, id);
        return present ? R.NG("用户名已存在") : R.OK("用户名可用");
    }

    /**
     * 校验手机号是否存在
     *
     * @param phone 手机号
     * @param id    id
     * @return 是否存在
     */
    public R<String> hasExistHashPhone(String phone, String id) {
        boolean present = repository.phoneExists(phone, id);
        return present ? R.NG("手机号已存在") : R.OK("手机号可用");
    }

    /**
     * 校验邮箱是否存在
     *
     * @param email 邮箱
     * @param id    id
     * @return 是否存在
     */
    public R<String> hasExistHashEmail(String email, String id) {
        boolean present = repository.hashEmailExists(email, id);
        return present ? R.NG("邮箱已存在") : R.OK("邮箱可用");
    }

    @Override
    public boolean create(SysUserCreateRequest sysUserCreateRequest) {
        //校验用户名是否存在
        R<String> r = hasExistUsername(sysUserCreateRequest.getUsername(), null);
        if (!r.isSuccess()) {
            throw new BasicException(r.getMessage());
        }

        // 重新计算hash256Hex
        if (StrUtil.isNotBlank(sysUserCreateRequest.getPhone())) {
            String phoneHex = DigestUtil.sha256Hex(sysUserCreateRequest.getPhone());
            sysUserCreateRequest.setHashPhone(phoneHex);
            //校验手机号是否存在
            r = hasExistHashPhone(sysUserCreateRequest.getHashPhone(), null);
            if (!r.isSuccess()) {
                throw new BasicException(r.getMessage());
            }
        } else {
            sysUserCreateRequest.setHashPhone(null);
            sysUserCreateRequest.setPhone(null);
        }
        if (StrUtil.isNotBlank(sysUserCreateRequest.getEmail())) {
            String emailHex = DigestUtil.sha256Hex(sysUserCreateRequest.getEmail());
            sysUserCreateRequest.setHashEmail(emailHex);

            //校验邮箱是否存在
            r = hasExistHashEmail(sysUserCreateRequest.getHashEmail(), null);
            if (!r.isSuccess()) {
                throw new BasicException(r.getMessage());
            }

        } else {
            sysUserCreateRequest.setHashEmail(null);
            sysUserCreateRequest.setEmail(null);
        }
        //密码
        boolean checkPwd = PasswdUtil.checkPwd(sysUserCreateRequest.getPassword());
        if (!checkPwd) {
            throw new BasicException("密码不满足强密码要求");
        }
        String salt = PasswdUtil.generateSalt();
        String password = PasswdUtil.encrypt(sysUserCreateRequest.getPassword(), salt);
        sysUserCreateRequest.setPassword(password);
        sysUserCreateRequest.setSalt(salt);

        return super.create(sysUserCreateRequest);
    }

    @Override
    public boolean updateById(String id, SysUserUpdateRequest request) {
        SysUser user = getById(id);
        if (user == null) {
            return false;
        }
        // 重新计算hash256Hex
        if (StrUtil.isNotBlank(request.getPhone())) {
            String phoneHex = DigestUtil.sha256Hex(request.getPhone());
            request.setHashPhone(phoneHex);
        } else {
            request.setHashPhone(null);
            request.setPhone(null);
        }
        if (StrUtil.isNotBlank(request.getEmail())) {
            String emailHex = DigestUtil.sha256Hex(request.getEmail());
            request.setHashEmail(emailHex);
        } else {
            request.setHashEmail(null);
            request.setEmail(null);
        }
        user = repository.getMapstruct().updateEntity(request, user);
        return updateById(user);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        SysUser user = getById(id);
        if (user == null) {
            return false;
        }
        //TODO 禁用相关

        return repository.deleteById(id);
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色
     */
    public List<SysUserRoleVO> findRoles(String userId) {
        return repository.findRolesByUserId(userId);
    }


    /**
     * 保存用户角色
     *
     * @param id        用户ID
     * @param userRoles 用户角色
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoles(String id, List<SysUserRoleUpdateRequest> userRoles) {
        userRoleService.deleteByUserId(id);
        if (CollectionUtil.isEmpty(userRoles)) {
            return true;
        }
        List<SysUserRole> roles = new ArrayList<>();
        for (SysUserRoleUpdateRequest userRole : userRoles) {
            SysUserRole role = new SysUserRole();
            role.setUserId(id);
            role.setRoleId(userRole.getRoleId());
            role.setEndTime(userRole.getEndTime());
            roles.add(role);
        }
        return userRoleService.saveBatch(roles);
    }

    /**
     * 获取有效的角色
     *
     * @param userId 用户ID
     * @return 角色
     */
    public List<SysUserRole> findEffectiveRoles(String userId) {
        return userRoleService.findEffectiveRolesByUserId(userId);
    }


    /**
     * 根据邮箱创建用户
     *
     * @param email 邮箱
     * @return 用户
     */
    @Transactional(rollbackFor = Exception.class)
    public R<SysUser> createByEmail(String email, String password) {
        // 1. 校验邮箱是否存在
        if (existEmail(email)) {
            return R.NG("邮箱已存在");
        }
        //2. 检查密码
        if (!PasswdUtil.checkPwd(password)) {
            return R.NG("密码不满足强密码要求");
        }
        SysUser user = new SysUser();
        // 3. 用户名
        String username = email.substring(0, email.indexOf("@"));
        user.setUsername(username);
        // 4. 密码
        String salt = PasswdUtil.generateSalt();
        String encryptPassword = PasswdUtil.encrypt(password, salt);
        user.setPassword(encryptPassword);
        user.setSalt(salt);
        // 5. 邮箱
        String mailHex = DigestUtil.sha256Hex(email);
        user.setHashEmail(mailHex);
        user.setEmail(email);
        // 6. nickname
        String nickname = DesensitizeUtil.email(email);
        user.setNickname(nickname);
        // 7. 性别
        user.setGender(0);
        // 8.是否系统用户
        user.setIsSystem(false);
        // 9. 状态
        user.setStatus(true);
        // 10. 创建用户
        save(user);
        // 分配角色
        List<SysUserRoleUpdateRequest> userRoles = new ArrayList<>();
        SysUserRoleUpdateRequest userRole = new SysUserRoleUpdateRequest();
        userRole.setRoleId("1894392845919936513");
        userRole.setEndTime(null);
        userRoles.add(userRole);
        saveRoles(user.getId(), userRoles);

        return R.OK(user);
    }
}
