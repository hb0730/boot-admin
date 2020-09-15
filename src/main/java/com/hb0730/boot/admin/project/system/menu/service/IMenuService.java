package com.hb0730.boot.admin.project.system.menu.service;

import com.hb0730.boot.admin.domain.service.ISuperBaseService;
import com.hb0730.boot.admin.project.system.menu.model.dto.MenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.MenuPermissionVO;
import com.hb0730.boot.admin.project.system.menu.model.vo.VueMenuVO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * 菜单  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IMenuService extends ISuperBaseService<Long, MenuParams, MenuDTO, MenuEntity> {


    /**
     * 根据父id获取子集(无限集，禁用与启用)
     *
     * @param id 父id
     * @return 子集(无限集)
     */
    @Nullable
    List<MenuDTO> getChildrenByParenId(@Nonnull Long id);

    /**
     * 获取当前用户菜单
     *
     * @return 当前用户
     */
    List<TreeMenuDTO> getCurrentMenu();

    /**
     * 刷新当前用户菜单
     *
     * @return 当前用户
     */
    boolean updateCurrentMenu();

    /**
     * 树形菜单
     *
     * @return 菜单树
     */
    List<TreeMenuDTO> queryTree();

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

    /**
     * 获取自己级上级菜单
     *
     * @param id       当前菜单id
     * @param entities 容器
     * @return 自己及子集菜单
     */
    List<MenuEntity> getSuperior(@Nonnull Long id, List<MenuEntity> entities);

    /**
     * 属性菜单权限树
     *
     * @return 菜单权限树
     */
    List<MenuPermissionVO> queryMenuPermissionTree();

}
