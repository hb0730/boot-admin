package com.hb0730.boot.admin.project.system.menu.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.MenuVO;

/**
 * 菜单  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IMenuService extends IBaseService<Long, MenuParams, MenuVO, MenuEntity> {

}
