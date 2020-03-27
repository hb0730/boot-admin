package com.hb0730.boot.admin.project.role.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.role.model.entity.SystemRoleEntity;
import com.hb0730.boot.admin.project.role.model.vo.RoleParams;
import com.hb0730.boot.admin.project.role.model.vo.SystemRoleVO;
import com.hb0730.boot.admin.project.role.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 获取全部角色
     *
     * @param roleParams 过滤条件
     * @return 角色信息
     */
    @PostMapping("/all")
    public Result getRoleAll(@RequestBody RoleParams roleParams) {
        List<SystemRoleEntity> entities = systemRoleService.list();
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
    public Result deleteById(@PathVariable Long id) {
        systemRoleService.removeById(id);
        return ResponseResult.resultSuccess("修改成功");
    }

}

