package com.hb0730.boot.admin.project.system.menu.service.impl;

import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.vo.VueMenuVO;
import com.hb0730.boot.admin.project.system.menu.service.IMenuService;
import com.hb0730.commons.spring.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Slf4j
public class MenuServiceImplTest {

    @Autowired
    private IMenuService menuService;

    @Test
    public void buildTreeTest() {
        List<MenuEntity> entities = menuService.list();
        List<TreeMenuDTO> treeMenu = BeanUtils.transformFromInBatch(entities, TreeMenuDTO.class);
        treeMenu = menuService.buildTree(treeMenu);
        Assert.assertNotNull(treeMenu);
        log.info(treeMenu.toString());
    }

    @Test
    public void buildVueMenusTest() {
        List<MenuEntity> entities = menuService.list();
        List<TreeMenuDTO> treeMenu = BeanUtils.transformFromInBatch(entities, TreeMenuDTO.class);
        treeMenu = menuService.buildTree(treeMenu);
        List<VueMenuVO> vueMenu = menuService.buildVueMenus(treeMenu);
        Assert.assertNotNull(vueMenu);
    }
}
