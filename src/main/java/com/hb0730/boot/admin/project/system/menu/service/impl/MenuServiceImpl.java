package com.hb0730.boot.admin.project.system.menu.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.menu.mapper.IMenuMapper;
import com.hb0730.boot.admin.project.system.menu.model.dto.MenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.MenuMetaVO;
import com.hb0730.boot.admin.project.system.menu.model.vo.VueMenuVO;
import com.hb0730.boot.admin.project.system.menu.service.IMenuService;
import com.hb0730.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.hb0730.commons.lang.constants.PathConst.ROOT_PATH;

/**
 * 菜单  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class MenuServiceImpl extends SuperBaseServiceImpl<Long, MenuParams, MenuDTO, MenuEntity, IMenuMapper> implements IMenuService {

    @Override
    public List<TreeMenuDTO> buildTree(List<TreeMenuDTO> menu) {
        List<TreeMenuDTO> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (TreeMenuDTO treeMenu : menu) {
            // -1或者null
            if (treeMenu.getParentId() == null || treeMenu.getParentId() == -1) {
                trees.add(treeMenu);
            }
            for (TreeMenuDTO it : menu) {
                if (treeMenu.getId().equals(it.getParentId())) {
                    if (treeMenu.getChildren() == null) {
                        treeMenu.setChildren(new ArrayList<>());
                    }
                    treeMenu.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = menu.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public List<VueMenuVO> buildVueMenus(List<TreeMenuDTO> treeMenu) {
        List<VueMenuVO> list = new LinkedList<>();
        treeMenu.forEach(menu -> {
                    if (menu != null) {
                        List<TreeMenuDTO> menuDtoList = menu.getChildren();
                        // 前端路由
                        VueMenuVO menuVo = new VueMenuVO();
                        // 一级目录需要加斜杠，不然会报警告 path
                        if (!menu.getPath().startsWith(ROOT_PATH)
                                &&
                                (menu.getParentId() == null || menu.getParentId() == -1)) {
                            menuVo.setPath(ROOT_PATH + menu.getPath());
                        }
                        // 组件名称 name
                        menuVo.setTitle(menu.getEnname());
                        //是否隐藏
                        menuVo.setHidden(false);
                        // component
                        if (menu.getParentId() == null || menu.getParentId() == -1) {
                            // 设置展开类型 默认 侧边栏
                            menuVo.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
                        } else if (!StringUtils.isEmpty(menu.getComponent())) {
                            menuVo.setComponent(menu.getComponent());
                        }
                        // vue router meta
                        menuVo.setMeta(new MenuMetaVO(menu.getTitle(), menu.getIcon(), false, true));

                        if (menuDtoList != null && menuDtoList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildVueMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menu.getParentId() == null || menu.getParentId() == -1) {
                            VueMenuVO menuVo1 = new VueMenuVO();
                            menuVo1.setMeta(menuVo.getMeta());
                            menuVo1.setTitle(menuVo.getTitle());
                            menuVo1.setComponent(menuVo.getComponent());
                            menuVo1.setPath(menu.getPath());

                            menuVo.setTitle(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<VueMenuVO> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }
}
