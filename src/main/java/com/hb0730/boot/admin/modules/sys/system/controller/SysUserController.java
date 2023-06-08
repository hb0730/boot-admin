package com.hb0730.boot.admin.modules.sys.system.controller;

import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.model.query.UserQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.UserVO;
import com.hb0730.boot.admin.modules.sys.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
