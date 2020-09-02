package com.hb0730.boot.admin.project.system.dept.service.impl;

import com.hb0730.boot.admin.commons.enums.SortTypeEnum;
import com.hb0730.boot.admin.project.system.dept.model.dto.DeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.dto.TreeDeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.entity.DeptEntity;
import com.hb0730.boot.admin.project.system.dept.model.query.DeptParams;
import com.hb0730.boot.admin.project.system.dept.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev" )
@Slf4j
public class DeptServiceImplTest {
    @Autowired
    private IDeptService service;

    @Test
    public void buildTest() {
        DeptParams params = new DeptParams();
        params.setSortType(SortTypeEnum.ASC.getValue());
        params.setSortColumn(Collections.singletonList(DeptEntity.SORT));
        List<DeptDTO> list = service.list(params);
        Set<TreeDeptDTO> result = service.buildTree(list);
        Assert.assertNotNull(result);
    }
}
