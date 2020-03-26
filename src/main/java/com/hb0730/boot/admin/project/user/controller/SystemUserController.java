package com.hb0730.boot.admin.project.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.commons.utils.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.commons.web.utils.SecurityUtils;
import com.hb0730.boot.admin.project.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.project.user.model.vo.SystemUserVO;
import com.hb0730.boot.admin.project.user.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 系统用户  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-24
 */
@RestController
@RequestMapping(RequestMappingNameConstants.REQUEST_USER)
public class SystemUserController extends BaseController {
    @Autowired
    private ISystemUserService systemUserService;

    /**
     * <p>
     * 用户新增
     * </p>
     *
     * @param vo 用户信息
     * @return 是否成功
     */
    @RequestMapping("/save")
    public Result save(SystemUserVO vo) {
        if (Objects.isNull(vo)) {
            return ResponseResult.resultFall("新增用户失败，用户账号为空");
        }
        SystemUserEntity entity = BeanUtils.transformFrom(vo, SystemUserEntity.class);
        systemUserService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    /**
     * <p>
     * 根据用户账号查找用户信息
     * </p>
     *
     * @param username 用户账号
     * @return 用户信息
     */
    public Result<SystemUserVO> loadUserByUsername(String username) {
        SystemUserEntity entity = new SystemUserEntity();
        entity.setUsername(username);
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>(entity);
        entity = systemUserService.getOne(queryWrapper);
        SystemUserVO userVO = BeanUtils.transformFrom(entity, SystemUserVO.class);
        return ResponseResult.resultSuccess(userVO);
    }

    /**
     * <p>
     * 根据用户id获取用户信息
     * </p>
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/info/{id}")
    public Result getUserInfoById(@PathVariable Long id) {
        SystemUserEntity entity = systemUserService.getById(id);
        SystemUserVO vo = BeanUtils.transformFrom(entity, SystemUserVO.class);
        return ResponseResult.resultSuccess(vo);
    }

    /**
     * <p>
     * 根据用户id修改用户信息
     * </p>
     *
     * @param vo 需要被修改的信息(不包含用户密码)
     * @param id 用户id
     * @return 用户信息
     */
    @PostMapping("/update/info/{id}")
    public Result updateInfoByUserId(@RequestBody SystemUserVO vo, @PathVariable Long id) {
        SystemUserEntity entity = systemUserService.getById(id);
        vo.setPassword(null);
        BeanUtils.updateProperties(vo, entity);
        systemUserService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * 根据用户id修改用户密码
     *
     * @param oldPassword  原密码
     * @param newPassword  新密码
     * @param newPassword2 二次验证
     * @param id           用户id
     * @return 是否成功
     */
    @PostMapping("/update/password/{id}")
    public Result updatePasswordByUserId(String oldPassword, String newPassword, String newPassword2, @PathVariable Long id) {
        if (StringUtils.isBlank(oldPassword)) {
            return ResponseResult.resultFall("原密码为空");
        }
        SystemUserEntity entity = systemUserService.getById(id);
        if (!SecurityUtils.matchesPassword(oldPassword, entity.getPassword())) {
            return ResponseResult.resultFall("原密码不正确");
        }
        if (StringUtils.isBlank(newPassword)) {
            return ResponseResult.resultFall("新密码为空");
        }
        if (!newPassword.equals(newPassword2)) {
            return ResponseResult.resultFall("两次输入密码不一致");
        }
        String encryptNewPassword = SecurityUtils.encryptPassword(newPassword2);
        entity = new SystemUserEntity();
        entity.setPassword(encryptNewPassword);
        entity.setId(id);
        systemUserService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }
}

