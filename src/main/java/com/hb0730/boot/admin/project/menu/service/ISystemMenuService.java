package com.hb0730.boot.admin.project.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.menu.model.vo.SystemMenuVO;
import com.hb0730.boot.admin.project.menu.model.vo.TreeMenuVO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 系统菜单  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
public interface ISystemMenuService extends IService<SystemMenuEntity> {
    /**
     * <p>
     * 获取全部树形菜单
     * </p>
     *
     * @return 树形菜单
     */
    List<TreeMenuVO> getTreeMenuAll();

    /**
     * <p>
     * 根据父id获取菜单(非树形)
     * </p>
     *
     * @param id 菜单id
     * @return 菜单信息
     */
    List<SystemMenuVO> getMenuByParentId(@NonNull Long id);
}
