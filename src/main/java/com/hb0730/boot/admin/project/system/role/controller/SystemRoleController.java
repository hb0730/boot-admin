package com.hb0730.boot.admin.project.system.role.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.system.role.model.entity.SystemRoleEntity;
import com.hb0730.boot.admin.project.system.role.model.vo.RoleParams;
import com.hb0730.boot.admin.project.system.role.model.vo.SystemRoleVO;
import com.hb0730.boot.admin.project.system.role.org.model.entity.SystemRoleOrgEntity;
import com.hb0730.boot.admin.project.system.role.org.service.ISystemRoleOrgService;
import com.hb0730.boot.admin.project.system.role.permission.model.entity.SystemRolePermissionEntity;
import com.hb0730.boot.admin.project.system.role.permission.service.ISystemRolePermissionService;
import com.hb0730.boot.admin.project.system.role.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
@RestController
@RequestMapping(RequestMappingNameConstants.REQUEST_ROLE)
public class SystemRoleController extends BaseController {
    @Autowired
    private ISystemRoleService systemRoleService;
    @Autowired
    private ISystemRolePermissionService systemRolePermissionService;
    @Autowired
    private ISystemRoleOrgService systemRoleOrgService;

    /**
     * 获取全部角色
     *
     * @param roleParams 过滤条件
     * @return 角色信息
     */
    @PostMapping("/all")
    public Result getRoleAll(@RequestBody RoleParams roleParams) {
        QueryWrapper<SystemRoleEntity> queryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(roleParams)) {
            if (!Objects.isNull(roleParams.getIsAll()) && !Objects.equals(roleParams.getIsAll(), SystemConstants.IS_ALL)) {
                queryWrapper.eq(SystemRoleEntity.IS_ENABLED, SystemConstants.USE);
            }
        }
        List<SystemRoleEntity> entities = systemRoleService.list(queryWrapper);
        List<SystemRoleVO> voList = BeanUtils.transformFromInBatch(entities, SystemRoleVO.class);
        return ResponseResult.resultSuccess(voList);
    }

    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param roleParams 过滤条件
     * @param page       页数
     * @param pageSize   数量
     * @return 分页后的角色信息
     */
    @PostMapping("/all/page/{page}/{pageSize}")
    public Result getRolePage(@PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody RoleParams roleParams) {
        PageHelper.startPage(page, pageSize);
        PageInfo<SystemRoleEntity> pageInfo = new PageInfo<>(systemRoleService.list());
        PageInfo<SystemRoleVO> voPageInfo = PageInfoUtil.toBean(pageInfo, SystemRoleVO.class);
        return ResponseResult.resultSuccess(voPageInfo);
    }

    /**
     * <p>
     * 保存
     * </p>
     *
     * @param vo 角色信息
     * @return 是否成功
     */
    @PostMapping("/save")
    @Log(paramsName = {"vo"}, module = ModuleName.ROLE, title = "角色保存", businessType = BusinessTypeEnum.INSERT)
    public Result save(@RequestBody SystemRoleVO vo) {
        systemRoleService.save(BeanUtils.transformFrom(vo, SystemRoleEntity.class));
        return ResponseResult.resultSuccess("保存成功");
    }

    /**
     * 根据id修改角色
     *
     * @param id id
     * @param vo 角色信息
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    @Log(paramsName = {"vo"}, module = ModuleName.ROLE, title = "角色修改", businessType = BusinessTypeEnum.UPDATE)
    public Result updateById(@PathVariable Long id, @RequestBody SystemRoleVO vo) {
        vo.setId(id);
        systemRoleService.updateById(BeanUtils.transformFrom(vo, SystemRoleEntity.class));
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * 根据id删除角色
     *
     * @param id id
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    @Log(module = ModuleName.ROLE, title = "角色删除", businessType = BusinessTypeEnum.DELETE)
    public Result deleteById(@PathVariable Long id) {
        systemRoleService.removeById(id);
        return ResponseResult.resultSuccess("修改成功");
    }
    /*******权限***************/
    /**
     * <p>
     * 根据角色获取权限id
     * </p>
     *
     * @param id 角色id
     * @return 权限id
     */
    @GetMapping("/permission/id/{id}")
    public Result getPermissionByRoleId(@PathVariable Long id) {
        QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemRolePermissionEntity.IS_ENABLED, SystemConstants.USE);
        queryWrapper.eq(SystemRolePermissionEntity.ROLE_ID, id);
        List<SystemRolePermissionEntity> entity = systemRolePermissionService.list(queryWrapper);
        Set<Long> permissionIds = Sets.newConcurrentHashSet();
        if (!CollectionUtils.isEmpty(entity)) {
            permissionIds = entity.parallelStream().map(SystemRolePermissionEntity::getPermissionId).collect(Collectors.toSet());
        }
        return ResponseResult.resultSuccess(permissionIds);
    }

    /**
     * <p>
     * 获取菜单对应的权限id key-value方式
     * </p>
     *
     * @param id 角色id
     * @return {@code Map<menuId,Set<permissionIds>>}
     */
    @GetMapping("/permission/map/{id}")
    public Result getMenuPermissionMapByRoleId(@PathVariable Long id) {
        Map<Long, Set<Long>> menuPermissionIds = systemRolePermissionService.getPermissionIdsByRoleId(id);
        return ResponseResult.resultSuccess(menuPermissionIds);
    }

    /**
     * <p>
     * 保存(修改)菜单对应的权限id
     * </p>
     *
     * @param id            菜单id
     * @param permissionIds 权限id
     * @return 是否成功
     */
    @PostMapping("/permission/save/{id}")
    @Log(paramsName = {"permissionIds"}, module = ModuleName.ROLE, title = "角色权限更新", businessType = BusinessTypeEnum.UPDATE)
    public Result savePermissionByRoleId(@PathVariable Long id, @RequestBody List<Long> permissionIds) {
        systemRolePermissionService.savePermissionByRoleId(id, permissionIds);
        return ResponseResult.resultSuccess("保存成功");
    }
    /*******数据范围***********/
    /**
     * 根据角色id获取数据范围id
     *
     * @param roleId 角色id
     * @return 数据范围
     */
    @GetMapping("/org/role/{roleId}")
    public Result getOrgIdByRoleId(@PathVariable Long roleId) {
        QueryWrapper<SystemRoleOrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemRoleOrgEntity.IS_ENABLED, SystemConstants.USE);
        queryWrapper.eq(SystemRoleOrgEntity.ROLE_ID, roleId);
        List<SystemRoleOrgEntity> list = systemRoleOrgService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.resultSuccess(Lists.newArrayList());
        }
        List<Long> orgIds = list.parallelStream().map(SystemRoleOrgEntity::getOrgId).collect(Collectors.toList());
        return ResponseResult.resultSuccess(orgIds);
    }

    /**
     * <p>
     * 保存数据范围
     * </p>
     *
     * @param roleId 角色id
     * @param orgIds 数据范围id
     * @return 是否成功
     */
    @PostMapping("/org/role/save/{roleId}")
    @Log(paramsName = {"orgIds"}, module = ModuleName.ROLE, title = "角色数据范围更新", businessType = BusinessTypeEnum.UPDATE)
    public Result saveOrgIdByRoleId(@PathVariable Long roleId, @RequestBody List<Long> orgIds) {
        systemRoleOrgService.saveOrgIdsByRoleId(roleId, orgIds);
        return ResponseResult.resultSuccess("保存成功");
    }
}

