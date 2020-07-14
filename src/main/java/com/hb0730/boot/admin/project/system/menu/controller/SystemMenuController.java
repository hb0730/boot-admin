package com.hb0730.boot.admin.project.system.menu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.spring.SecurityUtils;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.system.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.vo.SystemMenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.SystemMenuVO;
import com.hb0730.boot.admin.project.system.menu.model.vo.TreeMenuVO;
import com.hb0730.boot.admin.project.system.menu.permission.model.entity.SystemMenuPermissionEntity;
import com.hb0730.boot.admin.project.system.menu.permission.model.vo.PermissionParams;
import com.hb0730.boot.admin.project.system.menu.permission.service.ISystemMenuPermissionService;
import com.hb0730.boot.admin.project.system.menu.service.ISystemMenuService;
import com.hb0730.boot.admin.project.system.permission.model.dto.SystemPermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.vo.SystemPermissionVO;
import com.hb0730.boot.admin.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@RestController
@RequestMapping(RequestMappingNameConstants.REQUEST_MENU)
public class SystemMenuController extends BaseController<SystemMenuParams, SystemMenuVO, Long> {
    @Autowired
    private ISystemMenuService systemMenuService;
    @Autowired
    private ISystemMenuPermissionService permissionService;

    /**
     * <p>
     * 获取所有菜单(树形)
     * </p>
     *
     * @param isAll 是否查询全部
     * @return 树形菜单
     */
    @GetMapping("/tree/{isAll}")
    public Result<List<TreeMenuVO>> getAllTree(@PathVariable Integer isAll) {
        List<TreeMenuVO> treeMenus = systemMenuService.getTreeMenuAll(isAll);
        return ResponseResult.resultSuccess(treeMenus);
    }

    /**
     * 新增
     *
     * @param vo 菜单信息
     * @return 是否成功
     */
    @Override
//    @PostMapping("/save")
    @Log(paramsName = "vo", module = ModuleName.MENU, title = "新增", businessType = BusinessTypeEnum.INSERT)
    @PreAuthorize("hasAnyAuthority('menu:save','ROLE_ADMINISTRATOR','ROLE_ADMIN','ROLE_MENU_ADMIN')")
    public Result<String> save(SystemMenuVO vo) {
        SystemMenuEntity entity = BeanUtils.transformFrom(vo, SystemMenuEntity.class);
        systemMenuService.save(entity);
        return ResponseResult.resultSuccess("新增成功");
    }

    /**
     * 根据id更新
     *
     * @param id 菜单id
     * @param vo 菜单信息
     * @return 是否成功
     */
    @Override
//    @PostMapping("/update/{id}")
    @Log(paramsName = "vo", module = ModuleName.MENU, title = "修改", businessType = BusinessTypeEnum.UPDATE)
    @PreAuthorize("hasAnyAuthority('menu:update','ROLE_ADMINISTRATOR','ROLE_MENU_ADMIN')")
    public Result<String> updateById(Long id, SystemMenuVO vo) {
        SystemMenuEntity entity = BeanUtils.transformFrom(vo, SystemMenuEntity.class);
        assert entity != null;
        entity.setId(id);
        systemMenuService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 根据id删除
     * </p>
     *
     * @param id id
     * @return 是否成功
     */
    @Override
//    @GetMapping("/delete/{id}")
    @Log(module = ModuleName.MENU, title = "删除", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('menu:delete','ROLE_ADMINISTRATOR','ROLE_MENU_ADMIN')")
    public Result<String> deleteById(Long id) {
        systemMenuService.removeById(id);
        return ResponseResult.resultSuccess("删除成功");
    }

    /**
     * <p>
     * 根据菜单获取权限(非分页)
     * </p>
     *
     * @param menuId 菜单id
     * @return 权限信息
     */
    @GetMapping("/permission/{menuId}")
    public Result<List<SystemPermissionVO>> getPermissionByMenuId(@PathVariable Long menuId) {
        List<SystemPermissionVO> pageInfo = permissionService.getPermissionByMenuId(menuId);
        return ResponseResult.resultSuccess(pageInfo);
    }

    /**
     * 根据菜单id获取权限(分页)
     *
     * @param menuId 菜单id
     * @param params 过滤条件
     * @return 权限信息
     */
    @PostMapping("/permission/{menuId}")
    @PreAuthorize("hasAnyAuthority('menu:permission:query','ROLE_ADMINISTRATOR','ROLE_MENU_ADMIN')")
    public Result<Page<SystemPermissionVO>> getPermissionPageByMenuId(@PathVariable Long menuId, @RequestBody PermissionParams params) {
        Page<SystemPermissionVO> page = permissionService.getPermissionByMenuId(menuId, params);
        return ResponseResult.resultSuccess(page);
    }

    /**
     * <p>
     * 新增权限
     * </p>
     *
     * @param menuId       菜单id
     * @param permissionVO 权限信息
     * @return 是否成功
     */
    @PostMapping("/permission/save/{menuId}")
    @Log(paramsName = "permissionVO", module = ModuleName.MENU, title = "菜单权限新增", businessType = BusinessTypeEnum.INSERT)
    @PreAuthorize("hasAnyAuthority('menu:permission:save','ROLE_ADMINISTRATOR','ROLE_MENU_ADMIN')")
    public Result<String> savePermissionByMenuId(@PathVariable Long menuId, @RequestBody SystemPermissionVO permissionVO) {
        permissionService.save(menuId, permissionVO);
        return ResponseResult.resultSuccess("新增成功");
    }

    /**
     * <p>
     * 根据权限id修改权限信息
     * </p>
     *
     * @param permissionId 权限id
     * @param permissionVO 权限信息
     * @return 是否成功
     */
    @PostMapping("/permission/update/{permissionId}")
    @Log(paramsName = "permissionVO", module = ModuleName.MENU, title = "菜单权限修改", businessType = BusinessTypeEnum.UPDATE)
    @PreAuthorize("hasAnyAuthority('menu:permission:update','ROLE_ADMINISTRATOR','ROLE_MENU_ADMIN')")
    public Result<String> updatePermissionById(@PathVariable Long permissionId, @RequestBody SystemPermissionVO permissionVO) {
        permissionService.updatePermissionById(permissionId, permissionVO);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 根据权限id删除
     * </p>
     *
     * @param permissionId 权限id
     * @return 是否成功
     */
    @GetMapping("/permission/delete/{permissionId}")
    @Log(module = ModuleName.MENU, title = "菜单权限删除", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('menu:permission:delete','ROLE_ADMINISTRATOR','ROLE_MENU_ADMIN')")
    public Result<String> deleteByPermissionId(@PathVariable Long permissionId) {
        permissionService.deleteByPermissionId(permissionId);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 获取权限id(结合前端使用)
     * </p>
     *
     * @return Map<Long, Set < Long>>
     */
    @GetMapping("/permission/menu/all")
    public Result<Map<Long, Set<Long>>> getPermissionIdsAll() {
        Map<Long, Set<Long>> permissionIds = permissionService.getPermissionIdByMenuId();
        return ResponseResult.resultSuccess(permissionIds);
    }


    /**
     * 获取当前角色菜单(配合前端动态展示)
     *
     * @return 树形菜单
     */
    @GetMapping("/current")
    public Result<List<Map<String, Object>>> getCurrentUserMenu() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<SystemPermissionDTO> permissions = loginUser.getPermissions();
        if (CollectionUtils.isEmpty(permissions)) {
            return ResponseResult.resultSuccess(Lists.newArrayList());
        }
        Set<Long> permissionIds = permissions.parallelStream().map(SystemPermissionDTO::getId).collect(Collectors.toSet());
        List<SystemMenuPermissionEntity> menuPermission = permissionService.getMenuPermissionByPermissionIds(permissionIds);
        if (CollectionUtils.isEmpty(menuPermission)) {
            return ResponseResult.resultSuccess(Lists.newArrayList());
        }
        Set<Long> menuIds = menuPermission.parallelStream().map(SystemMenuPermissionEntity::getMenuId).collect(Collectors.toSet());
        List<Map<String, Object>> menu = systemMenuService.getVueTreeByMenuId(menuIds);
        return ResponseResult.resultSuccess(menu);
    }

}

