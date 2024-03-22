package com.hb0730.sys.rpc;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.biz.dto.sys.system.UserDto;
import com.hb0730.biz.dto.sys.system.UserLoginDto;
import com.hb0730.biz.dto.sys.system.UserRestPwdDto;
import com.hb0730.biz.entity.system.User;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.conf.rpc.rpc.BaseRpcService;
import com.hb0730.sys.bean.UserQuery;
import com.hb0730.sys.rpc.mapstruct.UserLoginMapper;
import com.hb0730.sys.rpc.mapstruct.UserMapper;
import com.hb0730.sys.service.IUserService;
import com.hb0730.sys.service.UserRpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户 RPC 服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Component
@RequiredArgsConstructor
public class UserRpcServiceImpl extends BaseRpcService<UserRpcService> implements UserRpcService {
    private final IUserService userService;
    private final UserLoginMapper userLoginMapper;
    private final UserMapper userMapper;

    @Override
    public JR<UserDto> findByUsername(String username) {
        User user = userService.findByUsername(username);
        UserDto dto = userMapper.toDto(user);
        return JR.okData(dto);
    }

    @Override
    public JR<UserLoginDto> loginByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return JR.fail("用户不存在");
        }
        UserLoginDto dto = userLoginMapper.toDto(user);
        return JR.okData(dto);
    }

    @Override
    public JR<String> changeLastLoginTime(String username) {
        userService.changeLastLoginTimeByUsername(username);
        return JR.ok();
    }

    @Override
    public JR<List<UserDto>> findByMenuId(Integer menuId) {
        List<User> users = userService.findByMenuId(menuId);
        List<UserDto> res = userMapper.toDtoList(users);
        return JR.okData(res);
    }

    @Override
    public JR<List<UserDto>> findByRoleId(Integer roleId) {
        List<User> users = userService.findByRoleId(roleId);
        List<UserDto> res = userMapper.toDtoList(users);
        return JR.okData(res);
    }

    @Override
    public JR<JsfPage<UserDto>> page(UserQuery query) {
        Page<User> page = userService.page(query);
        List<UserDto> dataList = userMapper.toDtoList(page.getContent());
        JsfPage<UserDto> res = JsfPage.of(page, dataList);
        return JR.okData(res);

    }

    @Override
    public JR<Boolean> existsByUsernameAndIdNot(String username, Integer id) {
        return JR.okData(userService.existsByUsernameAndIdNot(username, id));
    }

    @Override
    public JR<List<UserDto>> list(UserQuery query) {
        List<User> list = userService.list(query);
        List<UserDto> res = userMapper.toDtoList(list);
        return JR.okData(res);
    }


    @Override
    public JR<String> save(UserDto dto) {
        User entity = userMapper.toEntity(dto);
        userService.save(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(UserDto dto) {
        if (dto.getId() == null) {
            return JR.fail("id不能为空");
        }
        User entity = userMapper.toEntity(dto);
        userService.updateById(entity);
        return JR.ok();
    }

    @Override
    public JR<String> deleteById(Integer id) {
        userService.deleteById(id);
        return JR.ok();
    }

    @Override
    public JR<String> resetPassword(UserRestPwdDto dto) {
        if (dto.getId() == null) {
            return JR.fail("id不能为空");
        }
        if (StrUtil.isBlank(dto.getPassword())) {
            return JR.fail("密码不能为空");
        }
        userService.restPassword(dto.getId(), dto.getPassword(), dto.getOperator());
        return JR.ok();
    }
}
