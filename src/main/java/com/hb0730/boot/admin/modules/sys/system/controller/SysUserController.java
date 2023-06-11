package com.hb0730.boot.admin.modules.sys.system.controller;

import cn.hutool.core.util.StrUtil;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.model.query.UserQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.RestPasswdVO;
import com.hb0730.boot.admin.modules.sys.system.model.vo.UserVO;
import com.hb0730.boot.admin.modules.sys.system.service.SysUserService;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户管理
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@RestController
@RequestMapping("/sys/user")
@Tag(name = "系统：用户管理")
@RequiredArgsConstructor
public class SysUserController {
    private final SysUserService sysUserService;


    @GetMapping("/query/page")
    @Operation(summary = "分页查询")
    @PreAuthorize("@permission.hashPermission('user:query:page')")
    public R<BasePage<UserVO>> queryPage(HttpServletRequest request, @ParameterObject UserQuery query) {
        BasePage<UserVO> res = this.sysUserService.queryPage(query);
        return R.OK(res);
    }

    @GetMapping("/query/list")
    @Operation(summary = "列表查询")
    public R<List<UserVO>> queryList(HttpServletRequest request, @ParameterObject UserQuery query) {
        List<UserVO> res = this.sysUserService.queryList(query);
        return R.OK(res);
    }

    @GetMapping("/check/username")
    @Operation(summary = "检查用户名是否存在")
    public R<Boolean> checkUsername(HttpServletRequest request, @RequestParam String username) {
        return this.sysUserService.hashUsername(username);
    }

    @GetMapping("/")
    @Operation(summary = "详情")
    public R<UserVO> detail(HttpServletRequest request, @Parameter(name = "id", description = "用户ID",
            required = true) @RequestParam String id) {
        return this.sysUserService.detail(id);
    }

    @GetMapping("/username")
    @Operation(summary = "详情")
    public R<UserVO> detailByUsername(HttpServletRequest request, @Parameter(name = "username", description = "用户帐号",
            required = true) @RequestParam String username) {
        return this.sysUserService.detailByUsername(username);
    }

    @GetMapping("/query/role/ids")
    @Operation(summary = "查询用户角色")
    public R<List<String>> queryRoleIds(HttpServletRequest request,
                                        @Parameter(name = "id", description = "用户ID", required = true) @RequestParam String id) {
        return this.sysUserService.getRoleIdsByUserId(id);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @PreAuthorize("@permission.hashPermission('user:save')")
    public R<UserVO> save(HttpServletRequest request, @Validated @RequestBody UserVO userVO) {
        userVO.setCreated(LocalDateTime.now());
        userVO.setCreatedBy(SecurityUtil.getCurrentUsername());
        userVO.setModified(LocalDateTime.now());
        userVO.setModifiedBy(SecurityUtil.getCurrentUsername());
        return this.sysUserService.save(userVO);
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "修改")
    @PreAuthorize("@permission.hashPermission('user:update')")
    public R<UserVO> update(HttpServletRequest request, @PathVariable String id,
                            @Validated @RequestBody UserVO userVO) {
        userVO.setPassword(null);
        userVO.setOrgId(null);
        userVO.setUsername(null);
        userVO.setModified(LocalDateTime.now());
        userVO.setModifiedBy(SecurityUtil.getCurrentUsername());
        return this.sysUserService.updateById(id, userVO);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除")
    @PreAuthorize("@permission.hashPermission('user:del')")
    public R<String> delete(HttpServletRequest request, @Validated @RequestBody List<String> ids) {
        if (null == ids || ids.isEmpty()) {
            return R.NG("删除失败，缺少参数");
        }
        return this.sysUserService.deleteByIds(ids);
    }

    @PutMapping("/reset/passwd/{username}")
    @Operation(summary = "重置密码")
    @PreAuthorize("@permission.hashPermission('user:reset:passwd')")
    public R<String> resetPasswd(HttpServletRequest request, @PathVariable(value = "username") String username,
                                 @Validated @RequestBody RestPasswdVO vo) {
        if (StrUtil.isBlank(vo.getNewPassword())) {
            return R.NG("新密码不能为空");
        }
        if (vo.getNewPassword().equals(vo.getConfirmPassword())) {
            return R.NG("两次密码不一致");
        }
        return this.sysUserService.resetPassword(username, vo.getConfirmPassword());
    }

    @PutMapping("/change/passwd/{username}")
    @Operation(summary = "修改密码")
    public R<String> changePasswd(HttpServletRequest request,
                                  @PathVariable("username") String username, @Validated @RequestBody RestPasswdVO vo) {
        if (StrUtil.isBlank(vo.getNewPassword())) {
            return R.NG("新密码不能为空");
        }
        if (vo.getNewPassword().equals(vo.getConfirmPassword())) {
            return R.NG("两次密码不一致");
        }
        if (vo.getOldPassword().equals(vo.getNewPassword())) {
            return R.NG("新密码不能与旧密码相同");
        }
        return this.sysUserService.changePasswd(username, vo.getOldPassword(), vo.getNewPassword());
    }
}
