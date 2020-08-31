package com.hb0730.boot.admin.project.system.menu.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.menu.mapper.IMenuMapper;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.MenuMetaVO;
import com.hb0730.boot.admin.project.system.menu.model.dto.MenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.vo.VueMenuVO;
import com.hb0730.boot.admin.project.system.menu.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
            if (treeMenu.getParentId() == null) {
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
    public List<VueMenuVO> buildMenus(List<TreeMenuDTO> treeMenu) {
        List<VueMenuVO> list = new LinkedList<>();
        treeMenu.forEach(menu -> {
                    if (menu != null) {
                        List<TreeMenuDTO> menuDtoList = menu.getChildren();
                        VueMenuVO menuVo = new VueMenuVO();
                        menuVo.setName(menu.getEnname());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menu.getParentId() == null ? "/" + menu.getPath() : menu.getPath());

//                        menuVo.setHidden(menu.getHidden());
                        // 如果不是外链
//                        if (!menu.getIFrame()) {
//                            if (menu.getPid() == null) {
//                                menuVo.setComponent(StrUtil.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
//                            } else if (!StrUtil.isEmpty(menu.getComponent())) {
//                                menuVo.setComponent(menu.getComponent());
//                            }
//                        }
                        menuVo.setMeta(new MenuMetaVO(menu.getName(), menu.getIcon(), false));
                        if (menuDtoList != null && menuDtoList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menu.getParentId() == null) {
                            VueMenuVO menuVo1 = new VueMenuVO();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
//                            if (!menu.getIFrame()) {
//                                menuVo1.setPath("index");
//                                menuVo1.setName(menuVo.getName());
//                                menuVo1.setComponent(menuVo.getComponent());
//                            } else {
//                                menuVo1.setPath(menu.getPath());
//                            }
                            menuVo1.setPath(menu.getPath());
                            menuVo.setName(null);
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
