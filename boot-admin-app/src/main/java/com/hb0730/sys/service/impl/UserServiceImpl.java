package com.hb0730.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.utils.PageUtil;
import com.hb0730.biz.entity.system.Role;
import com.hb0730.biz.entity.system.User;
import com.hb0730.sys.bean.UserQuery;
import com.hb0730.sys.repository.DeptRepository;
import com.hb0730.sys.repository.RoleRepository;
import com.hb0730.sys.repository.UserRepository;
import com.hb0730.sys.service.IUserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    @Lazy
    @Resource
    private RoleRepository roleRepository;
    @Lazy
    @Resource
    private DeptRepository deptRepository;

    @Override
    public Boolean existsByUsernameAndIdNot(String username, @Nullable Integer id) {
        if (null == id) {
            return userRepository.existsByUsername(username);
        }
        return userRepository.existsByUsernameAndIdNot(username, id);
    }

    @Override
    public Page<User> page(UserQuery query) {
        Specification<User> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return userRepository.findAll(specification, page);
    }

    @Override
    public List<User> list(UserQuery query) {
        Specification<User> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sorts = query.getSorts();
        return sorts.map(orders -> userRepository.findAll(specification, Sort.by(orders))).orElseGet(() -> userRepository.findAll(specification));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
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
    public List<User> findByMenuId(Integer menuId) {
        return userRepository.findByMenuId(menuId);
    }

    /**
     * 根据角色ID查询用户
     *
     * @param roleId 角色ID .
     * @return .
     */
    @Override
    public List<User> findByRoleId(Integer roleId) {
        return userRepository.findByRolesId(roleId);
    }

    @Override
    public void save(User user) {
        // 后添加一律不是管理员
        user.setAdmin(false);
        // 填充数据，完整性
        userFill(user);
        userRepository.save(user);
    }

    @Override
    public void updateById(User user) {
        if (null == user.getId()) {
            throw new IllegalArgumentException("id不能为空");
        }
        User _user = userRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("用户不存在")
        );
        // 账号不能修改，密码单独修改
        _user.setNickname(user.getNickname());
        _user.setPhone(user.getPhone());
        _user.setEmail(user.getEmail());
        _user.setDept(user.getDept());
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
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restPassword(Integer id, String password, String operator) {
        userRepository.resetPwd(id, password, operator);
    }

    private void userFill(User user) {
        List<Role> roles = user.getRoles();
        if (CollectionUtil.isNotEmpty(roles)) {
            Set<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
            List<Role> role = roleRepository.findAllById(roleIds);
            user.setRoles(role);
        } else {
            user.setRoles(null);
        }

        if (null != user.getDept()) {
            deptRepository.findById(user.getDept().getId()).ifPresent(user::setDept);
        } else {
            user.setDept(null);
        }
    }
}
