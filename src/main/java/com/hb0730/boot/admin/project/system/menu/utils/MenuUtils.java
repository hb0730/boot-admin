package com.hb0730.boot.admin.project.system.menu.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.spring.SecurityUtils;
import com.hb0730.boot.admin.commons.utils.spring.SpringUtils;
import com.hb0730.boot.admin.project.system.menu.mapper.ISystemMenuMapper;
import com.hb0730.boot.admin.project.system.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.vo.TreeMenuVO;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class MenuUtils {
    /**
     * 获取mapper
     *
     * @return mapper
     */
    private static ISystemMenuMapper getMapper() {
        return SpringUtils.getBean(ISystemMenuMapper.class);
    }

    /**
     * <p>
     * 通过父节点id获取子节点(树形菜单)
     * </p>
     *
     * @param id 父节点id
     * @return 树形菜单
     */
    public static List<TreeMenuVO> getMenusTreeByParentId(@NonNull Long id) {
        List<TreeMenuVO> menus = Lists.newArrayList();
        List<SystemMenuEntity> menuEntities = getMenusByParentId(id);
        if (CollectionUtils.isEmpty(menuEntities)) {
            return menus;
        }
        List<TreeMenuVO> voList = BeanUtils.transformFromInBatch(menuEntities, TreeMenuVO.class);
        voList.forEach(menu -> {
            List<TreeMenuVO> childrens = Lists.newArrayList();
            TreeMenuVO childes = getChildes(menu, childrens);
            menus.add(childes);
        });
        return menus;
    }

    /**
     * 根据父节点获取子节点信息(不包含子子级)
     *
     * @param parentId 父节点id
     * @return 子节点信息
     */
    private static List<SystemMenuEntity> getMenusByParentId(Long parentId) {
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemMenuEntity.PARENT_ID, parentId);
        if (!Objects.requireNonNull(SecurityUtils.getLoginUser()).isAdmin()) {
            queryWrapper.eq(SystemMenuEntity.IS_ENABLED, SystemConstants.ENABLED);
        }

        return getMapper().selectList(queryWrapper);
    }

    /**
     * <p>
     * 获取子菜单
     * </p>
     *
     * @param vo    父菜单
     * @param menus 子集
     * @return 菜单
     */
    private static TreeMenuVO getChildes(TreeMenuVO vo, List<TreeMenuVO> menus) {
        List<SystemMenuEntity> systemMenuEntityList = getMenusByParentId(vo.getId());
        vo.setChildren(menus);
        systemMenuEntityList.forEach(systemMenu -> {
            TreeMenuVO menuVO = BeanUtils.transformFrom(systemMenu, TreeMenuVO.class);
            if (!Objects.isNull(menuVO)) {
                List<TreeMenuVO> voArrayList = Lists.newArrayList();
                TreeMenuVO childes = getChildes(menuVO, voArrayList);
                menus.add(childes);
            }

        });
        return vo;
    }

    /**
     * <p>
     * 根据节点id获取顶级菜单信息
     * </p>
     *
     * @param entity  子节点信息
     * @param allMenu 全部已启用的菜单
     * @param ids     节点id(用于装所有父节点id)
     * @return 顶级菜单信息
     */
    public static SystemMenuEntity getParentNodeInfoByChildrenNode(SystemMenuEntity entity, List<SystemMenuEntity> allMenu, Set<Long> ids) {
        ids.add(entity.getId());
        if (entity.getParentId() == -1 || entity.getParentId() == 0) {
            return entity;
        }
        SystemMenuEntity parentEntity = allMenu.parallelStream().filter(e1 -> Objects.equals(e1.getId(), entity.getParentId())).findFirst().get();
        return getParentNodeInfoByChildrenNode(parentEntity, allMenu, ids);
    }

    /**
     * <p>
     * 根据节点id过滤树形
     * </p>
     *
     * @param menus 树形菜单
     * @param ids   节点id(完整的节点信息(a>b>c>d))
     * @return 过滤后的树形
     */
    public static List<TreeMenuVO> getTreeByNodeId(List<TreeMenuVO> menus, Set<Long> ids) {
        List<TreeMenuVO> collect = menus.stream().filter(e1 -> ids.contains(e1.getId())).collect(Collectors.toList());
        collect.forEach(t -> getChildren(t, ids));
        return collect;
    }

    /**
     * 根据id过滤树形
     *
     * @param vo  树形菜单
     * @param ids 节点id
     */
    private static void getChildren(TreeMenuVO vo, Set<Long> ids) {
        List<TreeMenuVO> childrens = vo.getChildren();
        if (CollectionUtils.isEmpty(childrens) || SystemConstants.UN_ENABLED == vo.getIsEnabled()) {
            return;
        }
        List<TreeMenuVO> collect = childrens.stream().filter(e1 -> ids.contains(e1.getId())).collect(Collectors.toList());
        vo.setChildren(getTreeByNodeId(collect, ids));
    }

    /**
     * <p>
     * 将树形vo转换vue 菜单所需格式<br>
     * {@code [
     * {"title":"",
     * "path":"",
     * "icon":"",
     * "children":[]
     * },
     * {"title":"",
     * "path":"",
     * "icon":"",
     * }
     * ]
     * }
     * </p>
     *
     * @param menus 树形菜单
     * @param maps  空参数
     * @return 树形结构map格式
     */
    public static List<Map<String, Object>> getVueModel(List<TreeMenuVO> menus, List<Map<String, Object>> maps) {
        if (CollectionUtils.isEmpty(menus)) {
            return Lists.newArrayList();
        }
        menus.forEach(menu -> {
            Map<String, Object> map = Maps.newHashMap();
            map.put("title", menu.getName());
            map.put("path", menu.getUrl());
            map.put("icon", menu.getIcon());
            List<Map<String, Object>> mapsList = Lists.newArrayList();
            List<Map<String, Object>> vueModel = getVueModel(menu.getChildren(), mapsList);
            if (!CollectionUtils.isEmpty(vueModel)) {
                map.put("children", vueModel);
            }
            maps.add(map);
        });
        return maps;
    }
}
