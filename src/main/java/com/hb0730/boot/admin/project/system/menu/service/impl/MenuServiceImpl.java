package com.hb0730.boot.admin.project.system.menu.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.menu.mapper.IMenuMapper;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.MenuVO;
import com.hb0730.boot.admin.project.system.menu.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class MenuServiceImpl extends SuperBaseServiceImpl<Long, MenuParams, MenuVO, MenuEntity, IMenuMapper> implements IMenuService {

}
