package com.hb0730.boot.admin.project.system.menu.service;

import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.vo.SystemMenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.SystemMenuVO;
import com.hb0730.boot.admin.project.system.menu.model.vo.TreeMenuVO;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
public interface ISystemMenuService extends IBaseService<SystemMenuParams, SystemMenuVO, SystemMenuEntity> {
    /**
     * <p>
     * 获取全部树形菜单
     * </p>
     *
     * @param isAll 是否查询全部
     * @return 树形菜单
     */
    List<TreeMenuVO> getTreeMenuAll(Integer isAll);

    /**
     * <p>
     * 根据父id获取菜单(非树形)
     * </p>
     *
     * @param id    菜单id
     * @param isAll 是否查询全部
     * @return 菜单信息
     */
    List<SystemMenuVO> getMenuByParentId(@NonNull Long id, Integer isAll);

    /**
     * 根据菜单id获取vue所需树形
     *
     * @param menuIds 菜单id
     * @return 树形菜单
     */
    List<Map<String, Object>> getVueTreeByMenuId(Collection<Long> menuIds);
}
