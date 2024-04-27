package com.hb0730.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.common.util.PageUtil;
import com.hb0730.jpa.specification.SpecificationUtil;
import com.hb0730.rpc.sys.system.domain.query.UserQuery;
import com.hb0730.sys.system.domain.SysRole;
import com.hb0730.sys.system.domain.SysUser;
import com.hb0730.sys.system.repository.RoleRepository;
import com.hb0730.sys.system.repository.UserRepository;
import com.hb0730.sys.system.service.IUserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public SysUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsernameAndIdNot(String username, @Nullable Long id) {
        if (null == id) {
            return userRepository.existsByUsername(username);
        }
        return userRepository.existsByUsernameAndIdNot(username, id);
    }

    @Override
    public Page<SysUser> page(UserQuery query) {
        Specification<SysUser> specification = SpecificationUtil.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return userRepository.findAll(specification, page);
    }

    @Override
    public List<SysUser> list(UserQuery query) {
        Specification<SysUser> specification = SpecificationUtil.ofBean(query);
        Optional<List<Sort.Order>> sorts = query.getSorts();
        return sorts.map(orders -> userRepository.findAll(specification, Sort.by(orders))).orElseGet(() -> userRepository.findAll(specification));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeLastLoginTimeByUsername(String username) {
        userRepository.updateLastLoginTimeByUsername(username, new Date());
    }

    /**
     * 根据权限ID获取用户
     *
     * @param menuId .
     * @return .
     */
    @Override
    public List<SysUser> findByMenuId(Long menuId) {
        return userRepository.findByMenuId(menuId);
    }

    /**
     * 根据角色ID查询用户
     *
     * @param roleId 角色ID .
     * @return .
     */
    @Override
    public List<SysUser> findByRoleId(Long roleId) {
        return userRepository.findByRolesId(roleId);
    }

    @Override
    public void save(SysUser user) {
        // 填充数据，完整性
        userFill(user);
        userRepository.save(user);
    }

    @Override
    public void updateById(SysUser user) {
        if (null == user.getId()) {
            throw new IllegalArgumentException("id不能为空");
        }
        SysUser _user = userRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("用户不存在")
        );
        // 账号不能修改，密码单独修改
        _user.setNickname(user.getNickname());
        _user.setPhone(user.getPhone());
        _user.setEmail(user.getEmail());
        _user.setRoles(user.getRoles());
        _user.setAvatar(user.getAvatar());
        _user.setGender(user.getGender());
        _user.setEnabled(user.getEnabled());
        _user.setModified(user.getModified());
        _user.setModifiedBy(user.getModifiedBy());
        userFill(_user);

        userRepository.save(_user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restPassword(Long id, String password, String operator) {
        userRepository.resetPwd(id, password, operator);
    }

    private void userFill(SysUser user) {
        List<SysRole> roles = user.getRoles();
        if (CollectionUtil.isNotEmpty(roles)) {
            Set<Long> roleIds = roles.stream().map(SysRole::getId).collect(Collectors.toSet());
            List<SysRole> role = roleRepository.findAllById(roleIds);
            user.setRoles(role);
        } else {
            user.setRoles(null);
        }
    }
}
