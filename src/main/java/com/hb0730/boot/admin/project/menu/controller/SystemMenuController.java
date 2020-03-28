package com.hb0730.boot.admin.project.menu.controller;


import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.menu.model.vo.SystemMenuVO;
import com.hb0730.boot.admin.project.menu.model.vo.TreeMenuVO;
import com.hb0730.boot.admin.project.menu.permission.service.ISystemMenuPermissionService;
import com.hb0730.boot.admin.project.menu.service.ISystemMenuService;
import com.hb0730.boot.admin.project.permission.model.vo.SystemPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class SystemMenuController extends BaseController {
    @Autowired
    private ISystemMenuService systemMenuService;
    @Autowired
    private ISystemMenuPermissionService permissionService;

    /**
     * <p>
     * 获取所有菜单(树形)
     * </p>
     *
     * @return 树形菜单
     */
    @GetMapping("/tree")
    public Result getAllTree() {
        List<TreeMenuVO> treeMenus = systemMenuService.getTreeMenuAll();
        return ResponseResult.resultSuccess(treeMenus);
    }

    /**
     * 新增
     *
     * @param vo 菜单信息
     * @return 是否成功
     */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody SystemMenuVO vo) {
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
    @PostMapping("/update/{id}")
    public Result updateById(@PathVariable Long id, @Validated @RequestBody SystemMenuVO vo) {
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
    @GetMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
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
    public Result getPermissionByMenuId(@PathVariable Long menuId) {
        List<SystemPermissionVO> pageInfo = permissionService.getPermissionByMenuId(menuId);
        return ResponseResult.resultSuccess(pageInfo);
    }

    /**
     * <p>
     * 根据菜单id获取权限(分页)
     * </p>
     *
     * @param menuId   菜单id
     * @param page     页数
     * @param pageSize 数量
     * @return 权限信息
     */
    @GetMapping("/permission/{menuId}/{page}/{pageSize}")
    public Result getPermissionPageByMenuId(@PathVariable Long menuId, @PathVariable Integer page, @PathVariable Integer pageSize) {
        PageInfo<SystemPermissionVO> pageInfo = permissionService.getPermissionByMenuId(menuId, page, pageSize);
        return ResponseResult.resultSuccess(pageInfo);
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
    public Result savePermissionByMenuId(@PathVariable Long menuId, @RequestBody SystemPermissionVO permissionVO) {
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
    public Result updatePermissionById(@PathVariable Long permissionId, @RequestBody SystemPermissionVO permissionVO) {
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
    public Result deleteByPermissionId(@PathVariable Long permissionId) {
        permissionService.deleteByPermissionId(permissionId);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 获取权限id(结合前端使用)
     * </p>
     *
     * @return Map<Long,Set<Long>>
     */
    @GetMapping("/permission/menu/all")
    public Result getPermissionIdsAll() {
        Map<Long, Set<Long>> permissionIds = permissionService.getPermissionIdByMenuId();
        return ResponseResult.resultSuccess(permissionIds);
    }
}

