package com.hb0730.boot.admin.modules.sys.system.controller;

import cn.hutool.core.util.StrUtil;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.data.enums.MenuTypeEnums;
import com.hb0730.boot.admin.data.enums.ValueEnum;
import com.hb0730.boot.admin.modules.sys.system.cache.RouteCache;
import com.hb0730.boot.admin.modules.sys.system.model.dto.PermissionTree;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysPermission;
import com.hb0730.boot.admin.modules.sys.system.model.query.PermissionTreeQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.PermissionVO;
import com.hb0730.boot.admin.modules.sys.system.model.vo.VueMenuRouteVO;
import com.hb0730.boot.admin.modules.sys.system.service.SysPermissionService;
import com.hb0730.boot.admin.security.model.UserInfo;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/6
 */

@RestController
@RequestMapping("/sys/permission")
@Tag(name = "系统：菜单与权限")
@RequiredArgsConstructor
public class SysPermissionController {
    private final SysPermissionService sysPermissionService;
    private final RouteCache routeCache;

    /**
     * 获取当前用户路由
     *
     * @param request .
     * @return .
     */
    @GetMapping("/routes")
    @Operation(summary = "获取当前用户路由")
    public R<List<VueMenuRouteVO>> getCurrentRoute(HttpServletRequest request) {
        UserInfo user = SecurityUtil.getCurrentUser();
        Optional<List<VueMenuRouteVO>> routeCacheOptional = routeCache.getCache(user.getUserid());
        if (routeCacheOptional.isPresent()) {
            return R.OK(routeCacheOptional.get());
        }
        List<VueMenuRouteVO> menuRoute = null;
        if (user.isManger()) {
            Set<String> permissionIds = sysPermissionService.allPermissionIds();
            menuRoute = sysPermissionService.queryRouteByIds(List.copyOf(permissionIds));
        } else {
            menuRoute = sysPermissionService.queryRouteByUserid(user.getUserid());
        }
        routeCache.putCache(user.getUserid(), menuRoute);
        return R.OK(menuRoute);
    }

    /**
     * 获取菜单与权限
     *
     * @return .
     */
    @GetMapping("/tree")
    @Operation(summary = "获取菜单与权限树形列表")
    @PreAuthorize("@permission.hashPermission('menu:query')")
    public R<List<PermissionTree>> treesPermission(@ParameterObject PermissionTreeQuery query) {
        Optional<List<SysPermission>> permissionOpt = sysPermissionService.listPermission(query);
        Optional<List<PermissionTree>> treeOpt = permissionOpt
                .flatMap(e -> Optional.ofNullable(sysPermissionService.buildTree(e)));
        List<PermissionTree> tree = treeOpt.orElse(Collections.emptyList());
        return R.OK(tree);
    }

    @GetMapping("/tree/menu")
    @Operation(summary = "获取菜单树形列表")
    public R<List<PermissionTree>> tressMenus(@ParameterObject PermissionTreeQuery query) {
        Optional<List<SysPermission>> permissionOpt = sysPermissionService.listMenu(query);
        Optional<List<PermissionTree>> treeOpt = permissionOpt
                .flatMap(e -> Optional.ofNullable(sysPermissionService.buildTree(e)));
        List<PermissionTree> tree = treeOpt.orElse(Collections.emptyList());
        return R.OK(tree);
    }

    @PostMapping("/save")
    @Operation(summary = "保存菜单与权限")
    @PreAuthorize("@permission.hashPermission('menu:save')")
    public R<PermissionTree> save(@RequestBody PermissionVO vo) {
        MenuTypeEnums menuType = ValueEnum.valueToEnum(MenuTypeEnums.class, vo.getMenuType());
        R<PermissionTree> res = null;
        if (MenuTypeEnums.dir == menuType) {
            res = checkDirMenuType(vo);
        } else if (MenuTypeEnums.menu == menuType) {
            res = checkMenuType(vo);
        } else {
            res = checkPermissionType(vo);
        }
        if (null != res) {
            return res;
        }
        String username = SecurityUtil.getCurrentUsername();
        vo.setCreated(LocalDateTime.now());
        vo.setCreatedBy(username);
        Optional<PermissionTree> optional = sysPermissionService.save(vo);
        return optional.map(R::OK).orElseGet(() -> R.NG("保存失败"));
    }

    @PutMapping("/update")
    @Operation(summary = "更新菜单与权限")
    @PreAuthorize("@permission.hashPermission('menu:update')")
    public R<PermissionTree> update(@Parameter(name = "id", description = "当前ID", required = true) @RequestParam("id") String id,
                                    @Valid @RequestBody PermissionVO vo) {
        MenuTypeEnums menuType = ValueEnum.valueToEnum(MenuTypeEnums.class, vo.getMenuType());
        R<PermissionTree> res = null;
        if (MenuTypeEnums.dir == menuType) {
            res = checkDirMenuType(vo);
        } else if (MenuTypeEnums.menu == menuType) {
            res = checkMenuType(vo);
        } else {
            res = checkPermissionType(vo);
        }
        if (null != res) {
            return res;
        }
        String username = SecurityUtil.getCurrentUsername();
        vo.setModified(LocalDateTime.now());
        vo.setModifiedBy(username);
        Optional<PermissionTree> optional = sysPermissionService.updateById(vo, id
        );
        return optional.map(R::OK).orElseGet(() -> R.NG("更新失败"));
    }

    private R<PermissionTree> checkDirMenuType(PermissionVO vo) {
        String title = vo.getTitle();
        if (StrUtil.isBlank(title)) {
            return R.NG("菜单名称为空");
        }
        String path = vo.getPath();
        if (StrUtil.isBlank(path)) {
            return R.NG("路由地址为空");
        }
        Integer rank = vo.getRank();
        if (null == rank) {
            return R.NG("显示顺序为空");
        }
        return null;
    }

    private R<PermissionTree> checkMenuType(PermissionVO vo) {
        String title = vo.getTitle();
        if (StrUtil.isBlank(title)) {
            return R.NG("菜单名称为空");
        }
        String path = vo.getPath();
        if (StrUtil.isBlank(path)) {
            return R.NG("路由地址为空");
        }
        Integer rank = vo.getRank();
        if (null == rank) {
            return R.NG("显示顺序为空");
        }
        return null;
    }

    private R<PermissionTree> checkPermissionType(PermissionVO vo) {
        String title = vo.getTitle();
        if (StrUtil.isBlank(title)) {
            return R.NG("权限名称为空");
        }
        String perms = vo.getPerms();
        if (StrUtil.isBlank(perms)) {
            return R.NG("权限标识为空");
        }
        return null;
    }
}
