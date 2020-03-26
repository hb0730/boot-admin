package com.hb0730.boot.admin.project.menu.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.commons.utils.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.menu.model.vo.SystemMenuVO;
import com.hb0730.boot.admin.project.menu.model.vo.TreeMenuVO;
import com.hb0730.boot.admin.project.menu.service.ISystemMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}

