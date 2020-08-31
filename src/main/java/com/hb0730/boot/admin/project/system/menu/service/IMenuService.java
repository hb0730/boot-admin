package com.hb0730.boot.admin.project.system.menu.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.menu.model.dto.MenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.VueMenuVO;

import java.util.List;

/**
 * 菜单  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IMenuService extends IBaseService<Long, MenuParams, MenuDTO, MenuEntity> {

    /**
     * 构建菜单树
     *
     * @param treeMenu 原始数据,无序数据
     * @return 菜单树
     */
    List<TreeMenuDTO> buildTree(List<TreeMenuDTO> treeMenu);

    /**
     * 构建成前端所需菜单
     *
     * @param treeMenu 菜单树,有序的系统树
     * @return 前端所需的菜单
     */
    List<VueMenuVO> buildVueMenus(List<TreeMenuDTO> treeMenu);
}
