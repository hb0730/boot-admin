package com.hb0730.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.blinkfox.fenix.specification.FenixSpecification;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.basic.domain.BasUser;
import com.hb0730.basic.repository.BasUserRepository;
import com.hb0730.basic.service.IBasUserService;
import com.hb0730.common.util.PageUtil;
import com.hb0730.rpc.basic.domain.query.BasUserQuery;
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

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasUserServiceImpl implements IBasUserService {
    private final BasUserRepository basUserRepository;

    @Override
    public String getSysCodeByUsername(String username) {
        return basUserRepository.getSysCodeByUsername(username);
    }

    @Override
    public BasUser findByUsername(String username) {
        return basUserRepository.findByUsername(username);
    }

    @Override
    public BasUser findById(String id) {
        return basUserRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLastLoginTime(String username) {
        basUserRepository.updateLastLoginTimeByUsername(new Date(), username);
    }

    @Override
    public Boolean existsByUsername(String username, String sysCode, String id) {
        String _username = username;
        if (!_username.contains(sysCode)) {
            _username = username + "@" + sysCode;
        }
        if (StrUtil.isNotBlank(id)) {
            return basUserRepository.existsByUsernameAndIdNot(_username, id);
        } else {
            return basUserRepository.existsByUsername(_username);
        }
    }

    @Override
    public Page<BasUser> page(BasUserQuery query) {
        Specification<BasUser> specification = FenixSpecification.ofBean(query);
        Pageable page = PageUtil.toPage(query);
        return basUserRepository.findAll(specification, page);
    }

    @Override
    public List<BasUser> list(BasUserQuery query) {
        Specification<BasUser> specification = FenixSpecification.ofBean(query);
        Optional<List<Sort.Order>> sorts = query.getSorts();
        return sorts.map(sort -> basUserRepository.findAll(specification, Sort.by(sort)))
                .orElseGet(() -> basUserRepository.findAll(specification));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BasUser basUser) {
        basUser.setUsername(
                basUser.getUsername() + "@" + basUser.getSysCode()
        );
        basUserRepository.save(basUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(BasUser basUser) {
        if (basUser.getId() == null) {
            throw new RuntimeException("id不能为空");
        }
        BasUser user = basUserRepository.findById(basUser.getId()).orElseThrow(
                () -> new RuntimeException("用户不存在"));
        if (Boolean.TRUE.equals(user.getSystem())) {
            throw new RuntimeException("系统用户不能修改");
        }
        basUser.setUsername(null);
        basUser.setPassword(null);
        basUser.setCreated(null);
        basUser.setModifiedBy(null);

        BeanUtil.copyProperties(
                basUser, user, CopyOptions.create().ignoreNullValue()
        );
        basUserRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restPassword(String id, String password, String operator) {
        basUserRepository.resetPassword(id, password, operator);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        BasUser basUser = findById(id);
        if (null != basUser) {
            // 删除关联信息
            basUserRepository.delete(basUser);
        }

    }
}
