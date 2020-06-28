package com.hb0730.boot.admin.project.system.user.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.excel.ExcelConstant;
import com.hb0730.boot.admin.commons.utils.excel.ExcelUtils;
import com.hb0730.boot.admin.commons.utils.excel.UploadDataListener;
import com.hb0730.boot.admin.commons.utils.spring.SecurityUtils;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.exception.export.ExportException;
import com.hb0730.boot.admin.project.system.user.model.dto.UserExcelDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.project.system.user.model.vo.SystemUserVO;
import com.hb0730.boot.admin.project.system.user.model.vo.UserParams;
import com.hb0730.boot.admin.project.system.user.model.vo.UserVO;
import com.hb0730.boot.admin.project.system.user.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    @Deprecated
    public Result<String> save(SystemUserVO vo) {
        if (Objects.isNull(vo)) {
            return ResponseResult.resultFall("新增用户失败，用户账号为空");
        }
        SystemUserEntity entity = BeanUtils.transformFrom(vo, SystemUserEntity.class);
        systemUserService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    /**
     * 用户新增
     *
     * @param vo 用户信息
     * @return 是否成功
     */
    @PostMapping("/save")
    @Log(paramsName = {"vo"}, module = ModuleName.USER, title = "用户保存", businessType = BusinessTypeEnum.INSERT)
    @PreAuthorize("hasAnyAuthority('user:save','ROLE_ADMINISTRATOR','ROLE_USER_ADMIN')")
    public Result<String> save(@Validated @RequestBody UserVO vo) {
        if (Objects.isNull(vo)) {
            return ResponseResult.resultFall("新增用户失败，用户账号为空");
        }
        systemUserService.save(vo);
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
    public Result<SystemUserVO> getUserInfoById(@PathVariable Long id) {
        SystemUserEntity entity = systemUserService.getById(id);
        SystemUserVO vo = BeanUtils.transformFrom(entity, SystemUserVO.class);
        return ResponseResult.resultSuccess(vo);
    }

    /**
     * <p>
     * 根据用户id修改用户信息
     * </p>
     *
     * @param vo 需要被修改的信息(不包含用户密码,用户组织,用户岗位和用户角色)
     * @param id 用户id
     * @return 用户信息
     */
    @PostMapping("/update/info/{id}")
    @Deprecated
    public Result<String> updateInfoByUserId(@RequestBody SystemUserVO vo, @PathVariable Long id) {
        SystemUserEntity entity = systemUserService.getById(id);
        vo.setPassword(null);
        vo.setDeptId(null);
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
    @Log(paramsName = {"oldPassword", "newPassword", "newPassword2"}, module = ModuleName.USER, title = "用户修改密码", businessType = BusinessTypeEnum.UPDATE)
    public Result<String> updatePasswordByUserId(String oldPassword, String newPassword, String newPassword2, @PathVariable Long id) {
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

    /**
     * <p>
     * 分页用户
     * </p>
     *
     * @param vo 过滤条件
     * @return 分页后的用户信息
     */
    @PostMapping("/all")
    @PreAuthorize("hasAnyAuthority('user:query','ROLE_ADMINISTRATOR','ROLE_USER_ADMIN')")
    public Result<Page<SystemUserVO>> getUserPage(@RequestBody UserParams vo) {
        Page<SystemUserVO> page = systemUserService.page(vo);
        return ResponseResult.resultSuccess(page);
    }


    /**
     * <p>
     * 获取用户详情(包含用户角色岗位)
     * </p>
     *
     * @param userId 用户id
     * @return 用户详情
     */
    @GetMapping("/user/info/{userId}")
    public Result<UserVO> getUserInfo(@PathVariable Long userId) {
        UserVO info = systemUserService.getUserInfo(userId);
        return ResponseResult.resultSuccess(info);
    }

    /**
     * 更新用户信息
     *
     * @param user   用户信息(用户角色与用户岗位)
     * @param userId 用户id
     * @return 是否成功
     */
    @PostMapping("/update/user/{userId}")
    @Log(paramsName = {"user"}, module = ModuleName.USER, title = "更新用户信息", businessType = BusinessTypeEnum.UPDATE)
    @PreAuthorize("hasAnyAuthority('user:update','ROLE_ADMINISTRATOR','ROLE_USER_ADMIN')")
    public Result<String> updateUserById(@RequestBody UserVO user, @PathVariable Long userId) {
        if (Objects.isNull(user)) {
            return ResponseResult.resultSuccess("修改成功");
        }
        systemUserService.updateUser(user, userId);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 重置密码
     * </p>
     *
     * @param id 用户id
     * @return 是否成功
     */
    @GetMapping("/update/reset/password/{id}")
    @Log(module = ModuleName.USER, title = "重置密码", businessType = BusinessTypeEnum.UPDATE)
    @PreAuthorize("hasAnyAuthority('user:rest:password','ROLE_ADMINISTRATOR','ROLE_USER_ADMIN')")
    public Result<String> resetPassword(@PathVariable Long id) {
        systemUserService.resetPassword(id);
        return ResponseResult.resultSuccess("重置成功");
    }


    /**
     * <p>
     * 删除
     * </p>
     *
     * @param id 是否成功
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    @Log(module = ModuleName.USER, title = "删除用户", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('user:delete','ROLE_ADMINISTRATOR','ROLE_USER_ADMIN')")
    public Result<String> deleteById(@PathVariable Long id) {
        systemUserService.removeById(id);
        return ResponseResult.resultSuccess("删除成功");
    }

    /**
     * <p>
     * 删除用户
     * </p>
     *
     * @param ids id
     * @return 是否成功
     */
    @PostMapping("/delete")
    @Log(module = ModuleName.USER, title = "删除用户", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('user:delete','ROLE_ADMINISTRATOR','ROLE_USER_ADMIN')")
    public Result<String> deleteByIds(@RequestBody List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            systemUserService.removeByIds(ids);
            return ResponseResult.resultSuccess("删除成功");
        }
        return ResponseResult.resultFall("请选择");
    }

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param response 响应
     * @param params   过滤参数
     */
    @PostMapping("/export")
    @Log(paramsName = "params", module = ModuleName.USER, title = "用户导出", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("hasAnyAuthority('user:export','ROLE_ADMINISTRATOR','ROLE_USER_ADMIN')")
    public void export(HttpServletResponse response, UserParams params) {
        List<UserExcelDTO> export = systemUserService.export(params);
        try {
            Map<String, Object> maps = Maps.newHashMap();
            maps.put(ExcelConstant.FILE_NAME, "user_export");
            maps.put(ExcelConstant.DATA_LIST, export);
            ExcelUtils.writeWeb(response, maps, ExcelTypeEnum.XLS, UserExcelDTO.class);
        } catch (Exception e) {
            e.getStackTrace();
            throw new ExportException("用户导出失败", e);
        }
    }

    /**
     * <p>
     * 用户导入
     * </p>
     *
     * @param file 文件
     * @return 是否成功
     */
    @PostMapping("/upload")
    @Log(module = ModuleName.USER, title = "用户导入", businessType = BusinessTypeEnum.IMPORT)
    @PreAuthorize("hasAnyAuthority('user:upload','ROLE_ADMINISTRATOR','ROLE_USER_ADMIN')")
    public Result<String> upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), UserExcelDTO.class, new UploadDataListener(systemUserService)).sheet().doRead();
        return ResponseResult.resultSuccess("导入成功");
    }
}

