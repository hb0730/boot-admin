package com.hb0730.basic.rpc;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.base.utils.PasswordUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.basic.domain.BasOrg;
import com.hb0730.basic.domain.BasRole;
import com.hb0730.basic.domain.BasUser;
import com.hb0730.basic.rpc.mapstruct.BasUserMapper;
import com.hb0730.basic.service.IBasOrgService;
import com.hb0730.basic.service.IBasRoleService;
import com.hb0730.basic.service.IBasUserService;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.basic.domain.BasUserDto;
import com.hb0730.rpc.basic.domain.BasUserRestPasswordDto;
import com.hb0730.rpc.basic.domain.BasUserSaveDto;
import com.hb0730.rpc.basic.domain.query.BasUserQuery;
import com.hb0730.rpc.basic.service.BasUserRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasUserRpcServiceImpl extends BaseServerRpcService<BasUserRpcService> implements BasUserRpcService {
    private final BasUserMapper userMapper;
    private final IBasUserService userService;
    private final IBasOrgService orgService;
    private final IBasRoleService roleService;

    @Override
    public JR<String> getSysCodeByUsername(String username) {
        return JR.okData(userService.getSysCodeByUsername(username));
    }

    @Override
    public JR<BasUserDto> findByUsername(String username) {
        BasUser user = userService.findByUsername(username);
        BasUserDto res = userMapper.toDto(user);
        return JR.okData(res);
    }

    @Override
    public JR<String> updateLastLoginTime(String username) {
        userService.updateLastLoginTime(username);
        return JR.ok();
    }

    @Override
    public JR<Boolean> existsByUsername(String username, String sysCode, String id) {
        return JR.okData(userService.existsByUsername(username, sysCode, id));
    }

    @Override
    public JR<JsfPage<BasUserDto>> page(BasUserQuery query) {
        Page<BasUser> page = userService.page(query);
        List<BasUserDto> dtoList = userMapper.toDtoList(page.getContent());
        return JR.okData(JsfPage.of(page, dtoList));
    }

    @Override
    public JR<List<BasUserDto>> list(BasUserQuery query) {
        List<BasUser> list = userService.list(query);
        List<BasUserDto> dtoList = userMapper.toDtoList(list);
        return JR.okData(dtoList);
    }

    @Override
    public JR<String> save(BasUserSaveDto dto) {
        //商户对应的license
        String username = dto.getUsername();
        if (userService.existsByUsername(username, dto.getSysCode(), null)) {
            return JR.fail("用户名已存在");
        }
        String password = dto.getPassword();
        if (StrUtil.isBlank(password)) {
            return JR.fail("密码不能为空");
        }
        String orgId = dto.getOrgId();
        if (StrUtil.isBlank(orgId)) {
            return JR.fail("机构不能为空");
        }
        BasOrg basOrg = orgService.findByOrgId(orgId);
        boolean checkedAccount = orgService.checkAccountNum(orgId);
        if (!checkedAccount) {
            return JR.fail("账号数量已经上线");
        }
        List<BasRole> roles = null;
        if (CollectionUtil.isNotEmpty(dto.getRoleIds())) {
            roles = roleService.findByIds(dto.getRoleIds());
        }
        BasUser basUser = userMapper.saveDtoToEntity(dto);
        //密码加密
        basUser.setPassword(PasswordUtil.encoder(password));
        //设置机构
        basUser.setOrg(basOrg);
        //设置角色
        basUser.setRoles(roles);
        basUser.setSystem(false);
        userService.save(basUser);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(BasUserSaveDto dto) {
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            return JR.fail("id不能为空");
        }
        String username = dto.getUsername();
        if (userService.existsByUsername(username, dto.getSysCode(), id)) {
            return JR.fail("用户名已存在");
        }

        String orgId = dto.getOrgId();
        if (StrUtil.isBlank(orgId)) {
            return JR.fail("机构不能为空");
        }
        BasOrg basOrg = orgService.findByOrgId(orgId);
        List<BasRole> roles = null;
        if (CollectionUtil.isNotEmpty(dto.getRoleIds())) {
            roles = roleService.findByIds(dto.getRoleIds());
        }
        BasUser basUser = userMapper.saveDtoToEntity(dto);
        //密码加密
        basUser.setPassword(null);
        //设置机构
        basUser.setOrg(basOrg);
        //设置角色
        basUser.setRoles(roles);
        basUser.setSystem(false);
        userService.updateById(basUser);
        return JR.ok();
    }

    @Override
    public JR<String> restPassword(BasUserRestPasswordDto dto) {
        String password = dto.getPassword();
        PasswordUtil.encoder(password);
        userService.restPassword(dto.getId(), password, dto.getOperator());
        return JR.ok();
    }
}
